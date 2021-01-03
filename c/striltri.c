// https://www.hackerrank.com/challenges/small-triangles-large-triangles/problem
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct Triangle {
	int a, b, c;
	double s;
};

int cmpTriangle(const void * p1, const void * p2) {
	struct Triangle t1 = *((struct Triangle *)p1);
	struct Triangle t2 = *((struct Triangle *)p2);
	if (t1.s > t2.s) {
		return 1;
	}
	if (t1.s < t2.s) {
		return -1;
	}
	return 0;
}

int main() {
	int n;
	scanf("%d\n", &n);
	struct Triangle *trs;
	const int trSize = sizeof(struct Triangle); 
	trs = calloc(n, trSize);
	for (int i = 0; i < n; i++) {
		struct Triangle tr = *(trs+i);
		scanf("%d %d %d", &tr.a, &tr.b, &tr.c);
		double p = (tr.a+tr.b+tr.c)/2;
		tr.s = sqrt(p*(p-tr.a)*(p-tr.b)*(p-tr.c));
		*(trs+i) = tr;
	}
	qsort(trs, n, trSize, cmpTriangle);
	for (int i = 0; i < n; i++) {
		struct Triangle tr = *(trs+i);
		printf("%d %d %d\n", tr.a, tr.b, tr.c);
	}
	return 0;
}
