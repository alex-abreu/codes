#include <stdio.h>
#include <stdlib.h>

typedef void* Set;

#define MAXS 50

struct set
{
	void **s;
	int n;
	
};
typedef struct set* SET;


Set
setCreate()
{
	SET new = (SET) malloc(sizeof(SET));
	
	if(new == NULL)
		return NULL;

	new->s = (void* )malloc(MAXS*sizeof(void));
	if(new->s == NULL)
		return NULL;

	new->n = 0;

	return new;
}

int
setCard (Set s)
{
	SET t = setCreate();
	t = s;
	return t->n;
}

int main(int argc, char const *argv[])
{
	Set teste = NULL;
	teste = setCreate();

	int t = 10;
	t=setCard(teste);
	printf("%d\n", t);
	return 0;
}