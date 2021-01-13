// Cracking 1.1
#include <stdio.h>

typedef enum {true, false} bool;

int main() {
	char *line = NULL;
	size_t allocated = 0;
	int numChars = 1 << 8*sizeof(char);
	bool chars[numChars];
	for (int j = 0; j < numChars; j++) {
		chars[j] = false;
	}
	ssize_t len = getline(&line, &allocated, stdin);
	unsigned int unique = 0;
	for (int i = 0; i < len; i++) {
		char ch = *(line+i);
		if (ch == '\n') {
			continue;
		}
		if (chars[(int)ch] == true) {
			continue;
		}
		chars[(int)ch] = true;
		unique++;
	}
	printf("%d\n", unique);
	return 0;
}
