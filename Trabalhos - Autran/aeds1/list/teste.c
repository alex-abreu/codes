#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int compara(const void *a, const void *b){
	int a1 = *((*(int **)a));
	int b1 = *((*(int **)b));
	return (a1-b1);
}
int main(int argc, char const *argv[])
{
	int **v;
	int *a;
	int *b;
	int *c;
	int x = 5,y =8, z=3;
	a = &x;
	b = &y;
	c = &z;

	v = (int *) malloc (3*sizeof(int*));
	v[0] = a;
	v[1] = b;
	v[2] = c; 
	qsort(v,3,sizeof(int*),compara);
	for (int i = 0; i < 3; ++i)
	{
		
		printf("%p = %d\n", v[i] ,*((int*) v[i]) );
	}
	return 0;
}