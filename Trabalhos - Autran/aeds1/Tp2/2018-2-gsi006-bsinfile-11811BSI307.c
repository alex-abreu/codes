/*
Este programa realiza uma busca em um arquivo, essa busca é feita diretamente em disco.
Os dados do arquivo a ser pesquisado são registros do mesmo tamanho. O programa recebe,
em tempo de lançamento, a tag -f seguida do nome do arquivo a ser pesquisado, -d seguido 
da posição no registro que deve ser comparada com a chave de pesquisa, -cp seguido da chave
de pesquisa, e, opcionalmente, a tag -t que define se será retornado somente a primeira
ocorrencia da chave no arquivo ou todas as ocorrencias.O programa aceita as tags em quaisquer
ordem que forem informadas. O programa lê um arquivo .cfg, de mesmo nome que o arquivo a ser
pesquisado, e nesse arquivo estão as informações sobre os registros: quantos campos cada
registro tem, quantidade de bytes por campo, nome do .txt.

Alex Julio Silva de Abreu          / 11811BSI307
Júlia Caroline Aragão de Almeida   / 11511BSI251
Guilherme Assakura Daisuke         / 11811BSI296

Estruturas de Dados 1 - 2018/2

*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define ERROR11 11      //Quantidade de argumentos passados inválida
#define ERROR22 22		//parametros passados fora do lugar ou ausentes
#define ERROR33 33		//Erro ao abrir o arquivo.cfg
#define ERROR44 44		//Nem todos os dados referentes as configurações do .txt são numericos
#define ERROR55 55		//erro ao abrir o arquivo txt
#define ERROR66 66		//Existem dados faltando no arquivo .cfg
#define ERROR77 77		//arquivo .cfg em vazio
#define ERROR88 88		//nome do .txt ausente ou fora do local especificado
#define	ERROR99 99 		//caso apos -d não seja infromado um numero
#define ERROR19 19    //caso não tenha registro com a tag informada
#define MAX 30			//tamanho maximo das strings

int checkArg(int arg, char const *argument[], char * name, char * config, int * pos, char *key, int *sizeKey);
int checkFormat(int arg,char const *argument[], char * config, int * pos, char * key, char * nome, int *sizeKey);
void pError(int code);
int checkCFG(FILE * file, char * arq, int ** size, int * t);
int bSearch(FILE * file, int bytes, char *key, int arg, int t, int q, int *space, int tam);
FILE * openFile(char * name);
int alldigit(char *s);
int byts(int * array, int size);
void instan(int * array, int lim,int *BI);
char * getName(char *s, char *d);

int
main(int argc, char const *argv[])
{
	char nome[MAX]; 		//armazena o nome do arquivo .txt
	char config[MAX];		//armazena o nome do arquivo .cfg
	int pos = 0;			//armazena a posição do dado a ser comparado
	char key[MAX];			//armazena a chave de pesquisa
	int *size;				//armazena dos dados numericos contidos no arquivo .cfg
	int b = 0;				//quantidade de bytes por linha do arquivo de entrada
	FILE * F1 = NULL;		//ponteiro pro arquivo .txt
	FILE * F2 = NULL;		//ponteiro pro arquivo .cfg
	int t = 0;              //quantidade de dados numericos no arquivo .cfg
	int temp = 0;			//variavel pra controle de erro
	int sizeKey = 0;		//tamanho da string contento a chave de pesquisa
	int BI = 0;				//quantidade de bytes até a o local a ser comparado com a key

	temp = checkArg(argc, argv, nome, config, &pos, key, &sizeKey);

	if(temp != 0) //caso haja erro com os argumentos passados finaliza o programa
		return 0;
	
	F1 = openFile(nome);
	if(F1 == NULL){ //caso tenha acontecido erro ao abrir o arquivo .txt
		pError(ERROR55);
		return 0;
	}
	
	F2 = openFile(config);
	if(F2 == NULL){ //caso tenha acontecido erro ao abrir o arquivo .cfg
		pError(ERROR33);
		return 0;
	}
	
	temp = checkCFG(F2,nome,&size,&t);
	if(temp != 0)  //caso haja erro com o arquivo . cfg finaliza o programa
		return 0;
	
	b = byts(size,t);	
	instan(size,pos,&BI);
	
	temp = bSearch(F1,b,key,sizeKey,BI,argc,size,t);
	
	if(temp == 0)
		pError(ERROR19);
	
	fclose(F1);
	fclose(F2);
	
	return 0;
}


/*Esta funcao recebe um codigo, e imprime a respectiva mensagem de erro
*/
void
pError(int code)
{
	if(code == ERROR11)
	{
		fprintf(stderr,"Quantidade de argumentos informados inválida\n");
	}
	if(code == ERROR22)
	{
		fprintf(stderr,"Informe os parametros como solicitado\n");
	}
	if(code == ERROR33)
	{
		fprintf(stderr,"Erro ao abrir o arquivo .cfg\n");
	}
	if(code == ERROR44)
	{
		fprintf(stderr,"Nem todos os dados referentes as configurações do .txt são numericos\n");
	}
	if(code == ERROR55)
	{
		fprintf(stderr,"Erro ao abrir o arquivo .txt\n");
	}
	if(code == ERROR66)
	{
		fprintf(stderr,"Existem dados faltando no arquivo .cfg\n");
	}
	if(code == ERROR77)
	{
		fprintf(stderr,"Arquivo .cfg vazio\n");
	}
	if(code == ERROR88)
	{
		fprintf(stderr,"Nome do .txt ausente ou fora de posição\n");
	}
	if(code == ERROR99)
	{
		fprintf(stderr,"Após a tag -d deve ser informado um numero maior 0\n");
	}
	if(code == ERROR19)
	{
		fprintf(stderr,"Nenhuma ocorrencia da Chave de pesquisa foi encontrada no arquivo\n");
	}


}

/*
Esta função recebe a quantidade de argumentos e quais argumentos
foram passados em tempo de execução e verifica se a ordem passada é
valida. Caso seja valida a ordem retorna 0 e armazena em Key a chave de
pesquisa e em pos a posição do dado a ser comparado ,caso contrario retorna 1
*/
int
checkFormat(int arg,char const *argument[], char * config, int * pos, char * key, char * nome, int *sizeKey)
{
	int count1 = 0; //contador para quantas vezes -f foi informado
	int count2 = 0; //contador para quantas vezes -d foi informado
	int count3 = 0; //contador para quantas vezes -cp foi informado
	int i = 0;
	int j = 0;
	char temp[MAX];

	char str1[3] = "-f"; // variavel pra conferir se -f foi passado
	char str2[3] = "-d"; //variavel pra conferir se -d foi passado
	char str3[4] = "-cp"; //variavel pra conferir se -cp foi passado
	char str4[3] = "-t"; //variavel pra conferir se -t foi passado

	for (i = 0; i < arg; i++)
	{
		if(strcmp(str1,argument[i]) == 0){
			count1++;
			i++;
			strcpy(nome,argument[i]);
			config = getName(config,nome);
		}
		if(strcmp(str2,argument[i]) == 0){
			count2++;
			i++;
			strcpy(temp,argument[i]);
			if(alldigit(temp))
			{
				*pos = atoi(temp);
				if(*pos <= 0)
				{
					pError(ERROR99);
					return(ERROR99);
				}
			}
			else{
				pError(ERROR99);
				return(ERROR99);
			}
		}
		if(strcmp(str3,argument[i]) == 0){
			count3++;
				i++;
				strcpy(key,argument[i]);
				*sizeKey = strlen(key);
		}

	}
	if((count1 == 1 && count2 == 1 && count3 == 1))//caso os argumentos passados sejam válidos
		return 0;

	else return ERROR22; //caso caso exista argumento faltando ou em posição não valida
}

/*
Esta função recebe um ponteiro de arquivo, um array de char e um endereço 
de ponteiro para um array de int armazena o nome do .txt contigo no .cfg 
no array de char e os numeros relativos aos dados no arquivo .txt no array
de int (apos alocar dinamicamente o array).Caso a leitura ocorra sem erros
armazena em * arq o nome do arquivo e no vetor size o restante dos dados eretorna 0,
caso contrario retorna o codigo de erro e printa a mensagem de erro correspondente
*/

int 
checkCFG(FILE * file, char * arq, int ** size, int * t)
{

	int i = 0;//contador de dados lidos no arquivo config
	int tam = 0;//tamanho da string fornecida/ ou não no arquivo de config
	int j = 0;//iterador pra verificar os numeros passados no arquivo config
	char temp[MAX];//armazena temporariamente os dados contidos no .cfg
	int digit = 0; //variavel pra receber o resultado da verificação de alldigit(char *s)
	int s = 0; //quantidade de dados referentes ao arquivo .txt contido no .cfg

	if(fscanf(file,"%s",arq) == EOF){//caso o arquivo esteja vazio
		pError(ERROR77);
		return ERROR77;
	}
	
	tam = strlen(arq);
	
	if(strlen(arq) < 5)//caso o primeiro dado passado não seja o nome do .txt
	{
		pError(ERROR88);
		return ERROR88;
	}

	//caso a primeira string passada nao termine em .txt
	else if(arq[tam-1] != 't' || arq[tam-2] != 'x' || arq[tam-3] != 't' || arq[tam-4] != '.')
	{
		pError(ERROR88);
		return ERROR88;
	}
	else{
		
		while(fscanf(file,"%s",temp)!= EOF){
			
			i++;
			digit = alldigit(temp);
			if(!digit)//caso leia algo que nao seja numero
			{
				pError(ERROR44);
				return ERROR44;
			}
			if(i == 1)//caso tenha lido o numero de dados que serão a apresentados a seguir
			{
				s = atoi(temp);
				*t = s;
				*size = (int *)malloc(s *(sizeof(int)));
			}
			if(i > 1){ //le a quantidade de dados que foi informada anteriormente e faz as op
				(*size)[j] = atoi(temp);
				j++;
				if(j == s)
					break;
			}
		}
		if(j < s)//caso o numero de dados numericos nao correspondam com o que foi dito no arq
		{
			pError(ERROR66);
			return ERROR66;
		}
		
	}
	return 0;
}

/*
Esta função recebe a quantidade de argumentos e quais argumentos
foram passados em tempo de execução e verifica se a quantidade de
argumentos passadas é valida e chama a função 
checkFormat(int arg,char const *argument[], char * config, int * pos, char * key)
A função retorna valor diferente de 0 em caso de erro, e 0 caso
os argumentos passados sejam validos alem de armazenar a posição do dado a ser comparado
a key, o nome do arquivo .cfg e do arquivo .txt atraves da função checkFormat
*/

int
checkArg(int arg, char const *argument[], char * name, char * config, int * pos, char *key,int *sizeKey)
{
	int indice = 0;

	if(arg < 7 || arg > 8)//checa a quantidade de argumentos informados
	{
		pError(ERROR11);
		return ERROR11;
	}

	indice = checkFormat(arg, argument, config, pos, key, name,sizeKey);//checa os argumentos informados
	if(indice == ERROR22)
	{
		pError(ERROR22);
		return ERROR22;
	}
	if(indice == ERROR99)
		return ERROR99;
	return 0;


}

/*
Esta função recebe uma string com o nome do arquivo e abre dito arquivo.
Caso haja erro retorna NULL, caso contrario retorna o ponteiro
com as informações do arquivo aberto.
*/

FILE * openFile(char * name)
{
	FILE * file = NULL;
	file = fopen(name,"r");
	
	if(file == NULL){
		return NULL;
	}

	else return file;

}

/*
a função recebe uma string e retorna 1 caso a string só contenha numeros,
caso contrario retorna 0
*/
int 
alldigit(char *s)
{
	int i = 0 ;
	for (i = 0; s[i] != '\0'; i++)
	{
		if(!isdigit(s[i]))
			return 0;
	}
	return 1;
}


/*
Esta função recebe um array e seu tamanho e retorna a soma dos elementos
do array +1
*/
int
byts(int * array, int size)
{
	int sum = 0;
	int i = 0;
	for (i = 0; i < size; i++)
	{
		sum = sum +array[i];
	}
	return sum +1;
}

/*
Esta função recebe um ponteiro de arquivo, a chave de pesquisa, o tamanho da chave
a quantidade de argumentos passados em tempo de execução (argc), quantidade de bytes
por linha e quantidade de bytes até a posição a ser comparada com a chave. A função
retorna 0 caso não encontre a ocorrencia da key no arquivo, caso encontre imprime a 
(as) ocorrencias, dependendo se -t foi ou não informado em tempo de lançamento.
*/
int 
bSearch(FILE * file, int bytes, char *key, int sizeKey, int t, int q, int *space, int tam)
{
	int found = 0;			//variavel para controle, se existe ocorrencia da tag no arquivo
	long i = 0; 			//variavel pra controle de loop
	int j = 0;				//variavel pra controle de loop
	int k = 0;				//variavel pra controle de loop
	int l = 0;				//variavel pra controle de loop
	char * temp;			//variavel para armazenar a tag a ser comparada com a chave de pesquisa
	char * line;			//string pra armazenar a linha do arquivo a ser impressa
	long size;				//quantidade total de bytes do arquivo
	long BT = 0;			//quantidade de bytes a ser andado a partir do inicio do arquivo
	int sum = space[0];		//variavel pra print de espaço

	temp = (char *)malloc((sizeKey)*(sizeof(char)));//string para armazenar o dado a ser comparado
													//com  a chave de pesquisa
	
	line = (char *)malloc((bytes)*(sizeof(char)));	//string para armazenar a linha a ser impressa
	
	fseek(file,0,SEEK_END);
	size = ftell(file);
	fseek(file,0,SEEK_SET);

	BT = t;
	while(i < (size/bytes)){
		
		fseek(file,BT,SEEK_SET);
		fscanf(file,"%[^\n]s",temp);
		temp[sizeKey] = '\0';

		if(strcmp(temp,key) == 0)//caso encontre ocorrencia da chave de pesquisa
		{
			found++;
			BT = i*bytes;
			fseek(file,BT,SEEK_SET);
			fscanf(file," %[^\n]s",line);
			//printf("%s",line);
			for (k = 0; k < strlen(line); ++k)
			{
				if(sum == k){
					printf(" ");
					l++;
					sum+=space[l];
					if(l == tam)
						break; 
				}
				printf("%c",line[k]);
			}
			printf("\n");
			l = 0;
			sum = space[0]; 
			if(q == 7)//caso -t não tenha sido informado
				return 1;
		}

		i++;
		BT = (bytes * i) + t;
	}


	return found;

}
/*
esta função recebe o array e a posição do dado a ser comparado com a chave de pesquisa,
soma a quantidade de bytes ATÉ a posição da chave e retorna esse valor.
*/
void
instan(int * array, int lim,int *BI)
{
	int sum = 0;
	for(int i = 0; i < lim-1; i++){
		sum = sum + array[i];
	}
	*BI = sum;
}

//a função recebe duas strings e retorna a string com o nome do arquivo .cfg
char * 
getName(char *s, char * d)
{
	strcpy(s,d);
	int j = 0;
	j = strlen(s);
	s[j-1] = 'g';
	s[j-2] = 'f';
	s[j-3] = 'c';
	return s;
}