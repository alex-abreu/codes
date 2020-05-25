#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"

struct stu
{
   char *id;    // núm.matrícula
   char *n;   // nome do aluno
};
typedef struct stu Stu;

int compara(const void *a, const void *b){
   int a1 = *((*(int **)a));
   int b1 = *((*(int **)b));
   return (a1-b1);
}

int comp(void *a, void *b){
   return memcmp(a,b,sizeof(*a));
}

int
main (int argc, char *argv[])
{

   int i =1;
   int n = 1;
   void* item;
   int num;
   List o = listCreate();
   List l = listCreate (); // cria a lista l
   n = listNOI(l);
   printf("numero de itens na lista = %d\n",n );
   printf("------------------------------------\n");

   while(1)
   {
      scanf("%d",&n);
      if(n == 0)
         break;
      listIns (&n, i, l);
      i++;
   }

   n = listNOI(l);

   printf("numero de itens na lista = %d\n",n );
   printf("------------------------------------\n");
   
   for (int j = 1; j < i; j++)
   {
      item = listGetI(l,j);
      printf("%d\n",*((int*)item) );
   }
   
   listRmv(item,l);
   n = listNOI(l);
   
   printf("------------------------------------remove \n");
   printf("numero de itens na lista = %d\n",n );
   printf("------------------------------------\n");
   
   for (int j = 1; j <= n; j++)
   {
      item = listGetI(l,j);
      printf("%d\n",*((int*)item) );
   }
   
   printf("------------------------------ordem\n");
   
   o = listSort(l,compara);
   
   for (int j = 1; j <= n; j++)
   {
      item = listGetI(o,j);
      printf("%d\n",*((int*)item) );
   }
   printf("---------------------------pesquisa\n");
   printf("digite o numero a ser pesquisado:\n");
   scanf("%d",&num);

   item = listSearch(&num,l,comp);
   printf("%p\n",item );
   
   if(item != NULL)
      printf("found = %d\n", *((int*) item) );

   listDestroy (l);


   return 0;
}