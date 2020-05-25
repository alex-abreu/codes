app.exe:list.o app.o
	gcc list.o app.o -o app.exe

list.o:list.c
	gcc -c list.c

app.o:app.c
	gcc -c app.c

clean:
	rm *.o