#include<arpa/inet.h>
#include<stdio.h> //printf
#include<stdlib.h> //exit(0);
#include<string.h> //memset
#include<sys/socket.h>
#include<unistd.h> //close

#define BUFLEN 8  //Max length of buffer
#define PORT 8888   //The port on which to listen for incoming data

void die(char *s) {
  perror(s);
  exit(1);
}

int get_msg_id(char* buf) {
  char int_bytes[sizeof(int)];
  for (int i = 0; i < sizeof(int); i++) {
    int_bytes[i] = buf[i];
  }
  return ntohl(*((int *)int_bytes));
}

int main(void) {
  struct sockaddr_in si_me, si_other;
  int s, i, slen = sizeof(si_other), recv_len;
  int cur_msg = 0;
  char buf[BUFLEN];
  //create a UDP socket
  if ((s = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {
    die("socket");
  }
  memset((char *) &si_me, 0, sizeof(si_me));
  si_me.sin_family = AF_INET;
  si_me.sin_port = htons(PORT);
  si_me.sin_addr.s_addr = htonl(INADDR_ANY);
  if (bind(s, (struct sockaddr*)&si_me, sizeof(si_me)) == -1) {
    die("bind");
  }
  while(1) {
    if ((recv_len = recvfrom(s, buf, BUFLEN, 0,
        (struct sockaddr *) &si_other, &slen)) == -1) {
      die("recvfrom()");
    }
    cur_msg = get_msg_id(buf);
    printf("Received message %d from %s:%d\n",
           cur_msg,
           inet_ntoa(si_other.sin_addr),
           ntohs(si_other.sin_port));
    printf("Data:");
    fflush(stdout);
    write(STDOUT_FILENO, (char *)&buf[sizeof(int)], recv_len - sizeof(int));
    printf("\n");
    fflush(stdout);
    sleep(3); // lattency causer :)
    if (sendto(s, &cur_msg, sizeof(int), 0,
        (struct sockaddr*) &si_other, slen) == -1) {
      die("sendto()");
    }
    cur_msg++;
  }
  close(s);
  return 0;
}
