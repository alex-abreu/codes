/*
11711BSI200  		Vitor Magalhães de Souza
11811BSI307  		Alex Júlio Silva de Abreu
11511BSI251			Júlia Caroline Aragão de Almeida

Esse trabalho implementa em C o TAD Set e as funções de criação,
inserção, remoção, união ,diferença e intersecção, cadinalidade e
pesquisa(que caso o elemento seja encontrado no conjunto retorna o endereço do elemento)
*/
#include "2018-2-gsi006-set-11811BSI307.h"
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
void setCopy(SET a, SET b, int (*cmp)(const void *ea, const void *eb));


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
	{
		free(new);
		return NULL;
	}

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
que é a intersecção entre os Set a e b. Caso A ou B seja NULL retorna um conjunto vazio.
caso inter seja NULL, retorna NUll;
*/

Set 
setInter (Set a, Set b,  int (*cmp)(const void *ea, const void *eb))
{
	SET inter = setCreate();//cria o set que vai ser a interseção de A e B

	if(inter == NULL)
		return NULL;
	if(a == NULL || b == NULL)
		return inter;

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
para o Set Dif. Caso (A e B) ou dif seja invalido retorna NULL. Se somente a for invalido
retorna um conjunto vazio, se B for invalido retorna um conjunto dif que é a copia de A
*/

Set 
setDiff (Set a, Set b, int (*cmp)(const void *ea, const void *eb))
{
	SET dif = setCreate();
	
	if(a == NULL && b == NULL)
		return NULL;
	
	if(dif ==NULL)
		return NULL;

	if(a == NULL)
		return dif;

	if(b == NULL){
		setCopy(dif,a,cmp);
		return dif;
	}

	for (int i = 0; i < ((SET)a)->n; ++i)
	{
		if(!setBelongs(((SET)a)->s[i],((SET)b),cmp))
			setIns(((SET)a)->s[i],sizeof(((SET)a)->s[i]),dif,cmp);
	}

	return dif;
}

/*
Recebe dois Sets A e B e uma função de comparação. Caso (a e b) seja NULL
ou caso o set UNI seja NULL, retorna NULL.Se somente a for NULL, retorna um set
UNI que é a copia de B, se B for NULL, retorna um Set uni que é a copia de A,
caso contrario cria um Set uni no qual armazena a unição dos elementos
de A e B(Todos os elementos de A e B sem repetição de elementos) e retorna uni
*/

Set 
setUnion (Set a, Set b,  int (*cmp)(const void *ea, const void *eb))
{
	int ins = 0;
	SET uni = setCreate();
	
	if(a == NULL && b == NULL)
		return NULL;
	if(uni == NULL)
		return NULL;
	
	if(a == NULL)
	{
		setCopy(uni,((SET)b),cmp);
		return uni;
	}

	setCopy(uni,((SET)a),cmp);

	if(b == NULL)
		return uni;

	for (int i = 0; i < ((SET)b)->n; ++i)
	{
		if(setBelongs(((SET)b)->s[i],((SET)a),cmp) == 0)
		{
			ins = setIns(((SET)b)->s[i],sizeof(((SET)b)->s[i]),uni,cmp);
			if(ins == 23)//caso a união dos dois sets resulte num set maior que MAXS
				return NULL;
		}
	}
	return uni;
}
/*
A função recebe um Set S, caso esse set seja não vazio
ela libera toda memoria alocada para o Set S;
*/
void
setErase (Set s)
{
	if(s != NULL)
	{
		for (int i = 0; i < ((SET)s)->n; ++i)
		{
			free(((SET)s)->s[i]);

		}
		free(((SET)s)->s);
		free(s);
	}
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

//Recebe dois Sets A e B uma função de comparação. Copia os elementos de B para A;
void
setCopy(SET a, SET b, int (*cmp)(const void *ea, const void *eb))
{
	int t = 0;

	for (int i = 0; i < b->n; i++)
	{
		t = setIns(b->s[i],sizeof(b->s[i]),a,cmp);
	}
}