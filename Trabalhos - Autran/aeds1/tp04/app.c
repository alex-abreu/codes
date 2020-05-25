/*
11711BSI200  		Vitor Magalhães de Souza
11811BSI307  		Alex Júlio Silva de Abreu
11511BSI251			Júlia Caroline Aragão de Almeida
*/

#include "2018-2-gsi006-set-11811BSI307.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int 
compare(const void *ea, const void *eb)
{
	return memcmp(ea, eb, sizeof(*ea));
}

int loadSet(Set s, FILE *f)
{
	int num = 0;
	int t = 0;
	while(fscanf(f,"%d",&num) != EOF){
		t = setIns(&num,sizeof(int *),s,compare);
		if(t == 23)
			printf("o Set está cheio,%d nao inserido!\n",num);
		if(t == 25)
			printf("%d já existe no Set!\n", num);
		if(t == 27)
			printf("O Set não existe!\n");
		if(t == 0)
			printf("%d inserido com sucesso\n", num);
	}
	return t;
}

int main(int argc, char const *argv[])
{
	void * ele;
	Set t = NULL;
	Set h = NULL;
	Set dif = NULL;
	Set dif2 = NULL;
	Set uni =NULL;
	Set n = NULL;
	int cad1 = 0, cad2 = 0,cad3 = 0;
	int menu1 = 0, menu2 = 0;
	int e = 0,ec,ed;
	FILE *f1 = NULL;
	FILE *f2 = NULL;

	f1 = fopen(argv[1],"r");
	f2 = fopen(argv[2],"r");

	t = setCreate();
	h = setCreate();
	dif = setCreate();
	dif2 = setCreate();
	uni = setCreate();
	printf("Inserindo elementos no Set %p:\n",t);
	ec=loadSet(t,f1);
	fclose(f1);
	printf("-----------------------------------------------------------------------\n");
	printf("Inserindo elementos no Set %p:\n",h);
	ed=loadSet(h,f2);
	fclose(f2);
	cad1 = setCard(t);
	cad2 = setCard(h);
	if(ec == 27 || ed == 27){
		printf("ERRO\n");
		return 22;
	}

	cad1 = setCard(t);
	cad2 = setCard(h);
	printf("-----------------------------------------------------------------------\n");
	printf("numero de elementos no Set %p = %d\n",t,cad1);
	printf("numero de elementos no Set %p = %d\n",h,cad2);
	printf("-----------------------------------------------------------------------\n");
	printf("SET %p\n",t);
	for (int i = 1; i <= cad1; ++i)
	{
		ele = setGetE(i,t);
		printf("%d\n", *((int *) ele));
	}
	printf("-----------------------------------------------------------------------\n");
	printf("SET %p\n",h);
	for (int i = 1; i <= cad2; ++i)
	{
		ele = setGetE(i,h);
		printf("%d\n", *((int *) ele));
	}
	printf("-----------------------------------------------------------------------\n");
	printf("Digite o elemento a ser removido de %p:\n",h);
	scanf("%d",&e);
	int c = setRem(&e,h,compare);
	printf("SET %p after removing %d\n",h, e);
	cad3 = setCard(h);
	
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,h);
		printf("%d\n", *((int *) ele));
	}
	printf("-----------------------------------------------------------------------\n");
	printf("Set  inter %p :\n",dif);
	dif = setInter(h,t,compare);
	cad3 = setCard(dif);
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,dif);
		printf("%d\n", *((int *) ele));
	}
	printf("numero de elementos no Set inter %p = %d\n",dif,cad3 );
	printf("-----------------------------------------------------------------------\n");
	dif2 = setDiff(h,t,compare);
	cad3 = setCard(dif2);
	printf("numero de elementos na dif dos set %p e set %p :\n%d\n",h,t,cad3 );
	printf("set diff elements (b-a) %p\n",dif2);
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,dif2);
		printf("%d\n", *((int *) ele));
	}
	uni = setUnion(t,h,compare);
	cad3 = setCard(uni);
	printf("-----------------------------------------------------------------------\n");
	printf("numero de elementos na união dos set %p %d\n",uni,cad3 );
	printf("set uniao elements %p\n",uni);
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,uni);
		printf("%d\n", *((int *) ele));
	}
/*	printf("-----------------------------------------------------------------------\n");
	printf("Digite o elemento a ser removido:\n");
	scanf("%d",&e);
	int c = setRem(&e,t,compare);
	printf("SET %p after removing %d\n",t, e);
	cad3 = setCard(t);
	
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,t);
		printf("%d\n", *((int *) ele));
	}
*/
	setErase(t);
	setErase(h);
	setErase(dif);
	setErase(dif2);
	if( t == NULL)
		printf("true\n");	
	return 0;
}