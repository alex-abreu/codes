#include "set.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXS 50

struct set
{
	void **s;
	int n;
	
};
typedef struct set* SET;

int setBelongs (void *e, SET a, int (*cmp)(const void *ea, const void *eb));

/*cria um Set(aloca memoria para o Set), aloca memoria para MAXS elementos e seta o 
numero de elementos no conjunto para 0(s->n = 0), indicando um conjunto vazio e retorna
um ponteiro para o Set criado
*/
Set
setCreate()
{
	SET new = (SET) malloc(sizeof(SET));// aloca memoria para o Set
	
	if(new == NULL)//caso ocorra algum erro na alocação retorna NULL
		return NULL;

	new->s = (void* )malloc(MAXS*sizeof(void));//aloca memoria para o array de items do Set
	if(new->s == NULL)//caso haja erro na alocação de memoria para o array
		return NULL;

	new->n = 0;//seta o numero de itens inicial do conjunto para 0(conjunto vazio)

	return new;//retorna o ponteiro para o Set criado
}


//recebe um conjunto e retorna a cardinalidade do mesmo, retorna 0 caso seja passado um
//ponteiro NULL para a funcao

int
setCard (Set s)
{
	if(s == NULL)
		return 0;
	return ((SET)s)->n;
}

/*
Recebe um item, o tamanho do item, um conjunto e uma função de comparação.
Retorna 27 caso seja passado um ponteiro NULL ao inves de um ponteiro de Set
Retorna 25 caso o elemento que se deseja inserir já pertença ao conjunto
Retorna 23 caso o conjunto esteja cheio
Retorna 0 caso a inserção seja bem sucedida
*/

int
setIns (void *e, size_t t, Set s,  int (*cmp)(const void *ea, const void *eb))
{
	if(s == NULL)
		return 27;
	if(setBelongs(e,((SET)s),cmp))
		return 25;
	if(((SET)s)->n >= MAXS)
		return 23;

	((SET)s)->s[((SET)s)->n]= (void*) malloc(t*sizeof(void));//aloca memoria para o item no array do Set
	
	memcpy(((SET)s)->s[((SET)s)->n], e , t);//insere o elemento "e" no Set "s"
	
	((SET)s)->n++;//aumenta o contador de elementos no Set "s" em 1

	return 0;
}

/*
Recebe um item, um SET e uma função de comparação. Caso o item fornecido
pertença ao conjunto informado retorna 1. Caso seja informado um conjunto vazio
ou o elemento não pertença ao conjunto retorna 0
*/

int 
setBelongs(void *e, SET a, int (*cmp)(const void *ea, const void *eb))
{
    int i;
    if(e == NULL || a == NULL || a->n < 1) return 0;

    for(i = 0; i < (a->n); i++)
    {
        if(cmp(e, a->s[i]) == 0)
        { 
            return 1;
    	}
    }

    return 0;
}

/*
Recebe um numero p e um Set s, retorna o elemento n p-ésima posição do Set.
Caso a posição p informada seja maior o numero de elementos do set, caso o Set
informado seja invalido ou o valor p informado seja < 1 retorna NULL
*/

void * 
setGetE (int p, Set s)
{
	int card = 0;//armazena a cardinalidade do Set informado

	if(s == NULL )
		return NULL;
	card = setCard(s);
	
	if(p > card)
		return NULL;
	
	if(p < 1)
		return NULL;

	return ((SET)s)->s[p-1];
}

/*
Recebe dois Sets, a e b, e uma função de comparação.
Caso os Sets informados sejam validos retorna um ponteiro para o Set inter, 
que é a interseção entre os Set a e b. Caso haja problema com os sets informados,
ou na criação do set Inter retorna NULL.
*/

Set 
setInter (Set a, Set b,  int (*cmp)(const void *ea, const void *eb))
{
	SET inter = setCreate();//cria o set que vai ser a interseção de A e B

	if(a == NULL || b == NULL || inter == NULL)
		return NULL;

	for (int i = 0; i < ((SET)a)->n; ++i)
	{
		if(setBelongs(((SET)a)->s[i],((SET)b),cmp))
			setIns(((SET)a)->s[i],sizeof(((SET)a)->s[i]),inter,cmp);
	
	}

	return inter;
}

/*
Recebe dois Sets A e B, e uma função de comparação. Caso A e B sejam validos Criam um 
Set dif, e armazena em Dif os elementos de A que não pertencem a B e retorna um ponteiro
para o Set Dif. Caso haja algum problema com set A, B ou dif, retorna NULL
*/

Set 
setDiff (Set a, Set b, int (*cmp)(const void *ea, const void *eb))
{
	SET dif = setCreate();
	
	if(dif == NULL || a == NULL || b == NULL)
		return NULL;

	for (int i = 0; i < ((SET)a)->n; ++i)
	{
		if(!setBelongs(((SET)a)->s[i],((SET)b),cmp))
			setIns(((SET)a)->s[i],sizeof(((SET)a)->s[i]),dif,cmp);
	}

	return dif;
}

/*
Recebe dois Sets A e B e uma função de comparação. Caso haja problema com A e B
retorna NULL, caso contrario cria um Set uni no qual armazena a unição dos elementos
de A e B(Todos os elementos de A e B sem repetição de elementos) e retorna uni
*/

Set 
setUnion (Set a, Set b,  int (*cmp)(const void *ea, const void *eb))
{
	int ins = 0;
	SET uni = setCreate();
	if(a == NULL || b == NULL  || uni == NULL)
		return NULL;
	uni = a;

	for (int i = 0; i < ((SET)b)->n; ++i)
	{
		if(!setBelongs(((SET)b)->s[i],((SET)a),cmp))
			ins = setIns(((SET)b)->s[i],sizeof(((SET)b)->s[i]),uni,cmp);
			if(ins == 23)//caso a união dos dois sets resulte num set maior que MAXS
				return NULL;
	}
	return uni;
}

void
setErase (Set s)
{
	free(((SET)s)->s);
	free(s);
}

/*
Recebe um item, um Set, e uma função de comaparação
Caso o set informado seja NULL retorna 17
caso o item nao exista no set retorna 15
Caso o Set esteja vazio retorna 13
Se nenhum dos anteriores ocorrer, remove o elemento "e" do Set "s",
libera a memoria do item, diminui a cardinalidade do Set "s" em 1
e retorna 0 em caso de sucesso
*/

int 
setRem (void *e, Set s,  int (*cmp)(const void *ea, const void *eb))
{
	int i = 0;
	int j = 0;
    
    if(s == NULL) return 17;
    if(((SET)s)->n < 1) return 13;
    if(!setBelongs(e ,((SET)s) ,cmp)) return 15;

	for (i = 0; i < (((SET)s)->n); i++)
	{		
		if(cmp(e, (((SET)s)->s[i])) == 0)//acha a posição do elemento no Set
		{	
			for (j = i; j <= (((SET)s)->n - 1); j++){
				((SET)s)->s[j] = ((SET)s)->s[j+1];//sobrescreve o valor em j com j+1
			}
			free(((SET)s)->s[j+1]);//libera a ultima casa do conjunto
			((SET)s)->n--;//diminui o numero de elementos no set em 1
			break;
		}
	}
	return 0;
}
