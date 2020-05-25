/*
Universidade Federal de Uberlândia / Faculdade de Computação
Curso de Sistemas de Informação

GSI006 - Estrutura de Dados 1; 2018/1

Trabalho: Junção de Arquivos
============================
Este programa coloca em um único arquivo S o conteúdo de vários arquivos,
cujos nomes estão em um arquivo T.

Alex Julio Silva de Abreu
Guilherme Assakura
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


#define ERROR11 11
#define ERROR22 22
#define ERROR33 33
#define ERROR44 44
#define ERROR55 55

int checkArg(int arg, char const *argument[]);
int checkFormat(int arg, char const *argument[]);
void pError(int code);
void copyF(FILE *f1, FILE *f2);
int readF(FILE *f1, FILE *f2);

int
main(int argc, char const *argv[])
{
	
	int cError = 0; // variavel pra controle de mensagem de erro
	int i = 0;
	FILE * file1 = NULL; //ponteiro pro arquivo de entrada
	FILE * file2 = NULL; //ponteiro pro arquivo de saida
	char str1[3] = "-l"; // variavel pra conferir se -l foi passado
	char str2[3] = "-o"; //variavel pra conferir se -o foi passado

	cError = checkArg(argc , argv);
	if(cError != 0)
		return cError;

	for( i = 1 ; i < argc; i++)
	{
		if(strcmp(str1,argv[i]) == 0)
		{
			file1 = fopen(argv[i+1],"r");

			if(file1 == NULL)
			{
				pError(ERROR33);
				return ERROR33;
			}
		}
		if(strcmp(str2,argv[i]) == 0)
		{
			file2 = fopen(argv[i+1],"a");
			if(file2 == NULL)
			{
				pError(ERROR44);
				return ERROR44;
			}
		}
	}
	cError = readF(file1 , file2);
	fclose(file1);
	fclose(file2);
	return cError;
}
/*Esta funcao recebe um codigo, e imprime a respectiva mensagem de erro
*/
void
pError(int code)
{
	if(code == ERROR11)
	{
		fprintf(stderr,"Quantidade insuficiente de argumentos passados\n");
	}
	if(code == ERROR22)
	{
		fprintf(stderr,"Informe os parametros como solicitado\n");
	}
	if(code == ERROR33)
	{
		fprintf(stderr,"Erro ao abrir o arquivo de entrada\n");
	}
	if(code == ERROR44)
	{
		fprintf(stderr,"Erro ao abrir o arquivo de saida\n");
	}
	if(code == ERROR55)
	{
		fprintf(stderr,"Arquivo a ser copiado nao encontrado:");
	}

}
/*
Esta função recebe a quantidade de argumentos e quais argumentos
foram passados em tempo de execução e verifica se a ordem passada é
valida. Caso seja valida a ordem retorna 0 caso contrario retorna 1
*/
int
checkFormat(int arg,char const *argument[])
{
	int count = 0;	//contador para verificar se -o e -l foram informados em argv
	char str1[3] = "-l"; // variavel pra conferir se -l foi passado
	char str2[3] = "-o"; //variavel pra conferir se -o foi passado

	if(strcmp(str1,argument[1]) == 0 || strcmp(str1,argument[3]) == 0)
		count++;
	if(strcmp(str2,argument[1]) == 0 || strcmp(str2,argument[3]) == 0)
		count++;
	if(count == 2)//caso '-o' e '-l' tenham sido passados no local correto
		return 0;
	else return ERROR22; //caso -o ou -l esteja(m) ausentes ou fora de posição
}

/*
Esta função recebe a quantidade de argumentos e quais argumentos
foram passados em tempo de execução e verifica se a quantidade de
argumentos passadas é valida e chama a função 
checkFormat(int arg,char const *argument[]).
A função retorna valor diferente de 0 em caso de erro, e 0 caso
os argumentos passados sejam validos
*/
int
checkArg(int arg, char const *argument[])
{
	int control = 0;

	if(arg < 5)
	{
		pError(ERROR11);
		return ERROR11;
	}
	
	control = checkFormat(arg, argument);
	if(control == ERROR22)
	{
		pError(ERROR22);
		return ERROR22;
	}

	else return 0;


}

/*
Esta função recebe dois ponteiros de arquivo e lê todos os nomes de arquivos contidos
no arquivo de entrada, um por vez, e chama a função de copiar, com o ponteiro
do arquivo a ser copiado e do arquivo de saida 
*/
int
readF(FILE *f1, FILE *f2)
{
	FILE * temp;//variavel temporaria para os arquivos dentro do arquivo de entrada
	char nome[30];//variavel pra armazenar os nomes dos arquivos

	while(fscanf(f1, " %s" ,nome) != EOF)
	{
		temp = fopen(nome,"r");
		if(temp == NULL)
		{
			pError(ERROR55);
			printf(" %s\n",nome);;

		}
		else
		{
			copyF(temp,f2);
			fclose(temp);
		}
	
	}
	return 0;
}

/*
Esta função recebe dois ponteiros de arquivo e copia o conteudo do primeiro
arquivo no segundo arquivo
*/
void
copyF(FILE * f1, FILE * f2)
{
	char c;
	while(fscanf(f1,"%c",&c) != EOF)
	{
		fprintf(f2,"%c", c);
	}

}