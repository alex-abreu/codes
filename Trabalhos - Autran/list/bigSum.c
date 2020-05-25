/*
This program sums 2 numbers, regardless of how many digits those number have
by:Alex Julio Silva de Abreu


*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "list.h"

/*
This function reads digits of a number, one by one, and add them to a list,
numbers are read until enter or space is pressed,
if a non digit character is entered the funcion returns 1, otherwise
the function returns 0
*/

int readN(List l);

/*
Receives 2 list, each list countaining the digits of a number (A and B),
creates a third list and add the digits of the resulting sum of number A and B.
Returns the resulting list.
*/

List sumBig(List a, List b);


int
main(int argc, char const *argv[])
{

    List n1 = listCreate();//list to store the first number
    List n2 = listCreate();//list to store the second number
    List R; // list to store the sum of n1+n2
    void * item;// used to store the number's ditig and print them.
    
    int t = 0;//control variable to check the return value of readN
   
    if(n1 == NULL || n2 == NULL)
    {
    	printf("ERRO ao criar a lista, saindo!\n");
    	return 0;
    }

    t = readN(n1);//in case a non digit character was informed exit
    if(t == 1){
        return 0 ;
    }

    t= readN(n2);//in case a non digit character was informed exit
    if(t == 1){
        return 0;
    }

    //note to self: should have determined which of the numbers is the biggest and smallest
    //before using them in the funcition below, it would've been easier to implement the sum
    
    R = sumBig(n1,n2);

    if(R == NULL)
    	return 0;

    for (int i = listNOI(R); i >= 1; i--)//prints the sum of the numbers from the last to the first digit
    {
        item = listGetI(R,i);
        printf("%d",*((int*)item) );
    }
    printf("\n");
    listDestroy(R);
    listDestroy(n1);
    listDestroy(n2);

    

return 0;
}

int 
readN(List l)
{
    int i = 1;
    char n;
    int num;

    while(n = getchar())
    {   
        if(n == '\n'||n == '\0')//case enter or space are pressed stops reading
            break;
        else
        {
        num = atoi(&n);//converts the character read to int
        if(!isdigit(n)){//checks if the last read character was a digit
            printf("Caractere invalido digitado, saindo\n");
            return 1;
        }

        listIns(&num,1,l);//inserts the last digit read in the beginning the the list
                        
        }

    }
    return 0;
}

List
sumBig(List a, List b)
{
    int carry = 0,r = 0 ,a1,b1;
    int maior, menor;//to store the size of the biggest and smallest number informed
    int j = 1;//used set where the numbers will be inserted in the new list

    List R = listCreate();//to store the result of the sum
        if(R == NULL)
        	{
        		printf("Erro ao criar a lista, saindo:\n");
        		return NULL;
        	}

    //finds which of the informed numbers is the biggest and smallest
    if(listNOI(a)> listNOI(b))
    {
        maior = listNOI(a);
        menor = listNOI(b);
    }
    else
    {
        maior = listNOI(b);
        menor = listNOI(a);
    }

    for (int i = 1; i <= maior; i++)//
    {
        r = 0;
       
        if(j <= menor)
        {//operation is the same until we reach the end of the smallest number
            
            a1 = *((int *) listGetI(a,i));//gets the i-th digit of the number
            b1 = *((int *) listGetI(b,i));//gets the i-th digit of the number
            r = a1+b1;//sums the digits
            
            if((r + carry )>9)
            {//if the sum of the digit is bigger than 9
                
                r = r + carry -10;//takes the last digit of the resulting sum
                listIns(&r,j,R);//add the last digit to the list R
                carry = 1;//sets carry to 1
            }
            else
            {
                    r = r + carry; //if the sum of the digits + carry is lower than 10
                    listIns(&r,j,R);//insert the sum in the list R
                    carry = 0;//sets carry to 0
                
            }
        }
        else
        {

            if(listNOI(a) > listNOI(b))
            {//in case number a has more digits than number b
                
                a1 = *((int *) listGetI(a,i));
                r = a1 + carry;//adds the digit with the last value of the carry
                
                if(r <= 9)
                {//if carry + digit is lower than 10
                   
                    listIns(&r,j,R);//inserts the sum in the list R
                    carry = 0;//sets carry to 0

                }
                else
                {

                    r = r-10;//if the carry + digit is greater than 9
                    listIns(&r,j,R);//insert the last digit of the sum in the R
                    carry = 1;//sets carry to 1

                }
            }
            else
            {//in case number b has more digits than number a
               
                b1 = *((int *) listGetI(b,i));
                r = b1 + carry;
                
                if(r <= 9)
                {//if carry + digit is lower than 10
                   
                    listIns(&r,j,R);//adds the sum to R
                    carry = 0;//sets carry to 1

                }
                else
                {
                    
                    r = r-10;//case the sum is higher than 9
                    listIns(&r,j,R);//inserts last digit of the sum to R
                    carry = 1;//sets carry to 1


                }
            }
        }

        j++;

        if(i == maior && carry == 1)//in case the last sum of digits is greater than 10
            listIns(&carry,j,R);  //inserts the carry at the end of R
    }

    return R;//returns the list with the digits of the sum of a and b
}