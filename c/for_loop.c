// https://www.hackerrank.com/challenges/for-loop-in-c/problem
#include <stdio.h>

int main() {
	int a, b;
	const char *c[9];
	c[0] = "one";
	c[1] = "two";
	c[2] = "three";
	c[3] = "four";
	c[4] = "five";
	c[5] = "six";
	c[6] = "seven";
	c[7] = "eight";
	c[8] = "nine";
	scanf("%d\n%d", &a, &b);
	if (a < 1) {
		printf("1st number should be more than 0, got %d", a);
		return 1;
	}
	for (int i = a; i <= b; i++) {
		if (i < 10) {
			printf("%s\n", c[i-1]);
			continue;
		}
		if (i%2 == 0) {
			printf("even\n");
		} else {
			printf("odd\n");
		}
	}

	return 0;
}
