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

void die(char *s) {
  perror(s);
  exit(1);
}

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

int udp_open_socket() {
  int fd;
  if ((fd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {
    die("udp socket");
  }
  struct timeval tv;
  tv.tv_sec = TIMEOUT_SEC;
  tv.tv_usec = 0;
  if (setsockopt(fd, SOL_SOCKET, SO_RCVTIMEO, &tv, sizeof(tv)) < 0) {
    die("udp setsockopt");
  }

  struct sockaddr_in si_other;
  int s, i, slen = sizeof(si_other);
  memset((char *) &si_other, 0, sizeof(si_other));
  si_other.sin_family = AF_INET;
  si_other.sin_port = htons(UDP_PORT);

  if (inet_aton(SERVER, &si_other.sin_addr) == 0) {
    fprintf(stderr, "inet_aton() failed\n");
    exit(1);
  }
  return fd;
}

void udp_send(int udp_fd, char[] message) {
  // Opens UDP port, connects to the server
  // Reads lines from stdin
  // Sends lines to Server
  // Receives ACKs
  int cur_msg = 0;
  char message[MSG_MAX_LEN];
  char buf_packet[PACKET_SIZE];
  char buf_reply[HEADER_SIZE];
  char message_acked = 0;

  memset(buf_packet,'\0', PACKET_SIZE);
  int net_cur_msg = htonl(cur_msg);
  for (i = 0; i < sizeof(int); i++) {
    buf_packet[i] = ((char *)&net_cur_msg)[i];
  }
  for (i = 0; i < strlen(message); i++) {
    buf_packet[HEADER_SIZE + i] = message[i];
  }

  message_acked = 0;
  while (message_acked == 0) {
    if (sendto(udp_fd, buf_packet, strlen(message) + HEADER_SIZE, 0,
        (struct sockaddr *) &si_other, slen)==-1) {
      die("sendto");
    }
    if (recvfrom(udp_fd, buf_reply, HEADER_SIZE, 0, (struct sockaddr *) &si_other, &slen) != -1) {
      if ((int)*buf_reply == cur_msg) {
        printf("ack for message %d recieved\n", cur_msg);
        message_acked = 1;
      }
    } else {
      if (errno == 11 /* EAGAIN */) {
        printf("Timeout reached, retrying\n");
        fflush(stdout);
      } else {
        die("recvfrom");
      }
    }
  }
  cur_msg++;
}
 
int main(void) {
  udp_fd = udp_open_socket();
  // tcp_in = open_tcp();
  while(1) {
    memset(message, '\0', MSG_MAX_LEN);
    read(STDIN_FILENO, message, MSG_MAX_LEN);
    udp_send(udp_fd, message);
  }
  close(udp_fd);
}
