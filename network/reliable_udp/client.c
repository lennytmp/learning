#include<arpa/inet.h>
#include<errno.h>
#include<stdio.h> //printf
#include<stdlib.h> //exit(0);
#include<string.h> //memset
#include<sys/socket.h>
#include<unistd.h> //close

#define SERVER "127.0.0.1"
#define MSG_MAX_LEN 4  //Max length of buffer
#define HEADER_SIZE sizeof(int)
#define PACKET_SIZE (MSG_MAX_LEN + HEADER_SIZE)
#define UDP_PORT 8888   //The port on which to send data
#define TCP_PORT 2222   //The port on which to send data
#define TIMEOUT_SEC 2

// Structure to keep the connection properties in.
typedef struct {
  struct sockaddr_in connection;
  int fd;
  int cur_msg;
} Connection;

void die(char *s) {
  perror(s);
  exit(1);
}

// This function will start listening on TCP port, not working yet.
int open_tcp_socket() {
  int fd;
  if ((fd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) == -1) {
    die("tcp socket");
  }
  struct sockaddr_in si_me, si_other;
  memset((char *) &si_me, 0, sizeof(si_me));
  si_me.sin_family = AF_INET;
  si_me.sin_port = htons(TCP_PORT);
  si_me.sin_addr.s_addr = htonl(INADDR_ANY);
  if (bind(fd, (struct sockaddr*)&si_me, sizeof(si_me)) == -1) {
    die("bind");
  }
  return fd;
}

// Opens UDP connection with the server.
Connection udp_open() {
  Connection conn;
  if ((conn.fd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {
    die("socket");
  }
  struct timeval tv;
  tv.tv_sec = TIMEOUT_SEC;
  tv.tv_usec = 0;
  if (setsockopt(conn.fd, SOL_SOCKET, SO_RCVTIMEO, &tv, sizeof(tv)) < 0) {
    die("setsockopt");
  }

  int s, i, slen = sizeof(conn.connection);
  memset((char *) &conn.connection, 0, sizeof(conn.connection));
  conn.connection.sin_family = AF_INET;
  conn.connection.sin_port = htons(UDP_PORT);

  if (inet_aton(SERVER, &conn.connection.sin_addr) == 0) {
    die("inet_aton");
  }
  conn.cur_msg = 0;
  return conn;
}

// Reliably sends the message over UDP connection by by requiring ACK for
// each message.
void udp_send(Connection* conn, char message[], int msg_len) {
  // TOOD(lennytmp): this should accept any message length and iterate over it.
  char buf_packet[PACKET_SIZE];
  memset(buf_packet,'\0', PACKET_SIZE);
  int net_cur_msg = htonl(conn->cur_msg);

  int i;
  for (i = 0; i < sizeof(int); i++) {
    buf_packet[i] = ((char *)&net_cur_msg)[i];
  }
  for (i = 0; i < msg_len; i++) {
    buf_packet[HEADER_SIZE + i] = message[i];
  }

  char message_acked = 0;
  int slen = sizeof(conn->connection); 
  char buf_reply[HEADER_SIZE];
  while (message_acked == 0) {
    if (sendto(conn->fd, buf_packet, msg_len + HEADER_SIZE, 0,
          (struct sockaddr *) &conn->connection, slen)==-1) {
      die("sendto");
    }
    if (recvfrom(conn->fd, buf_reply, HEADER_SIZE, 0,
          (struct sockaddr *) &conn->connection, &slen) != -1) {
      if ((int)*buf_reply == conn->cur_msg) {
        printf("CLIENT: Ack for message %d recieved\n", conn->cur_msg);
        fflush(stdout);
        message_acked = 1;
      }
    } else {
      if (errno == 11 /* EAGAIN */) {
        printf("CLIENT: Timeout reached, retrying\n");
        fflush(stdout);
      } else {
        die("recvfrom");
      }
    }
  }
  conn->cur_msg++;
}
 
int main(void) {
  Connection udp = udp_open();
  // tcp_in = open_tcp();
  char message[MSG_MAX_LEN];
  while(1) {
    memset(message, '\0', MSG_MAX_LEN);
    int msg_len = read(STDIN_FILENO, message, MSG_MAX_LEN);
    if (msg_len < 0) {
      die("Read error");
    } else {
      udp_send(&udp, message, msg_len);
    }
  }
  close(udp.fd);
}

