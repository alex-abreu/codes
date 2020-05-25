#include "set.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int 
compare(const void *ea, const void *eb)
{
	return memcmp(ea, eb, sizeof(*ea));
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
	int ea = 10, eb = 15 ,ec = 13, ed = 13, eF = 21, eG = 35;

	t = setCreate();
	h = setCreate();
	dif = setCreate();
	dif2 = setCreate();
	uni = setCreate();

	cad1 = setCard(t);
	cad2 = setCard(h);

	menu1 = setIns(&ea,sizeof(int),t,&compare);//insere 10 em T
	menu1 = setIns(&ec,sizeof(int),t,&compare);//insere 13 em T
	menu1 = setIns(&ed,sizeof(int),t,&compare);//insere 13 em T
	menu1 = setIns(&eF,sizeof(int),t,&compare);//insere 21 em T

	menu2 = setIns(&ea,sizeof(ea),h,compare);
	menu2 = setIns(&eb,sizeof(eb),h,compare);
	menu2 = setIns(&eF,sizeof(eF),h,compare);
	menu2 = setIns(&eF,sizeof(eF),h,compare);

	cad1 = setCard(t);
	cad2 = setCard(h);

	printf("%d\n",cad1);
	printf("%d\n",cad2);
	
	printf("SET 1\n");
	for (int i = 1; i <= cad1; ++i)
	{
		ele = setGetE(i,t);
		printf("%d\n", *((int *) ele));
	}
	printf("SET 2\n");
	for (int i = 1; i <= cad2; ++i)
	{
		ele = setGetE(i,h);
		printf("%d\n", *((int *) ele));
	}

	printf("Set inter\n");
	dif = setInter(h,t,compare);
	cad3 = setCard(dif);
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,dif);
		printf("%d\n", *((int *) ele));
	}
	printf("numero de elementos na inter %d\n",cad3 );

	dif2 = setDiff(h,t,compare);
	cad3 = setCard(dif2);
	printf("numero de elementos na dif dos set %d\n",cad3 );
	printf("set diff elements\n");
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,dif2);
		printf("%d\n", *((int *) ele));
	}
	uni = setUnion(h,t,compare);
	cad3 = setCard(uni);
	printf("numero de elementos na união dos set %d\n",cad3 );
	printf("set uniao elements\n");
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,uni);
		printf("%d\n", *((int *) ele));
	}
	
	int c = setRem(&ea,uni,compare);
	printf("c = %d\n",c );
		printf("SET uni after removing %d\n", ea);
	cad3 = setCard(uni);
	
	for (int i = 1; i <= cad3; ++i)
	{
		ele = setGetE(i,uni);
		printf("%d\n", *((int *) ele));
	}

	setErase(t);
	setErase(h);
	setErase(dif);
	setErase(dif2);
	if( t == NULL)
		printf("true\n");
	return 0;
}