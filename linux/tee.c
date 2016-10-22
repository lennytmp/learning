#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

void errExit(char* str) {
  printf("%s\n", str);
  exit(-1);
}

int main(int argc, char* argv[]) {
  const int BUFFER_SIZE = 1024;
  int rw_flag = O_TRUNC;
  int opt;
  extern int optind;
  while ((opt = getopt(argc, argv, ":a")) != -1) {
    if (opt == 'a') {
      rw_flag = O_APPEND;
    }
  }
  if (optind >= argc) {
    errExit("Please specify the filename for output\n");
  }
  char* filename = argv[optind];

  int fd = open(filename, O_WRONLY | O_CREAT | rw_flag, 0666);
  if (fd == -1) {
    errExit("Error openning file");
  }

  char *buf = malloc(BUFFER_SIZE);
  int bytes_read;
  while (1) {
    if ((bytes_read = read(STDIN_FILENO, buf, BUFFER_SIZE)) == -1) {
      errExit("Error reading stdin");
    }
    if (write(STDOUT_FILENO, buf, bytes_read) == -1) {
      errExit("Error writing to STDOUT");
    }
    if (write(fd, buf, bytes_read) == -1) {
      errExit("Error writing to the file");
    }
  }
  if (close(fd) == -1) {
    errExit("Error closing the file");
  }
}

