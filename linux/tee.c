#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void errExit(char* str) {
  extern int errno;
  if (errno) {
    perror(str);
  } else {
    fprintf(stderr, "%s\n", str);
  }
  exit(-1);
}


char* concat(char* str1, char* str2) {
  char* result = malloc((sizeof(str1) + sizeof(str2)));
  sprintf(result, "%s%s", str1, str2);
  printf("%s", result);
  return result;
}

int main(int argc, char* argv[]) {
  int rw_flag = O_TRUNC;
  int opt;
  while ((opt = getopt(argc, argv, ":a")) != -1) {
    if (opt == 'a') {
      rw_flag = O_APPEND;
    }
  }
  // Open the files if needed
  extern int optind;
  int num_files = argc - optind;
  struct File {
    char* filename;
    int fd;
  };
  struct File files[num_files];
  for (int i = optind; i < argc; i++) {
    files[i - optind].filename = argv[i];
    files[i - optind].fd = open(files[i - optind].filename, O_WRONLY | O_CREAT | rw_flag, 0664);
    if (files[i - optind].fd == -1) {
      errExit(concat("Eror openning file ", files[i - optind].filename));
    }
  }

  const long BUFFER_SIZE = 1024*1024; 
  char *buf = malloc(BUFFER_SIZE);
  if (!buf) {
    errExit("Could not allocate memory");
  }
  // All ready to read&write now
  int bytes_read = 1;
  while (bytes_read) {
    if ((bytes_read = read(STDIN_FILENO, buf, BUFFER_SIZE)) == -1) {
      errExit("Error reading stdin");
    }
    if (write(STDOUT_FILENO, buf, bytes_read) == -1) {
      errExit("Error writing to STDOUT");
    }
    for (int i = 0; i < num_files; i++) {
      if (write(files[i].fd, buf, bytes_read) == -1) {
        errExit(concat("Error writing to the file ", files[i].filename));
      }
    }
  }
  for (int i = 0; i < num_files; i++) {
    if (close(files[i].fd) == -1) {
      errExit(concat("Error closing the file", files[i].filename));
    }
  }
}

