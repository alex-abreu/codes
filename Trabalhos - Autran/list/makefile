app.exe:list.o app.o
	gcc list.o app.o -o app.exe

big:list.o bigSum.o
	gcc list.o bigSum.o -o app.exe


list.o:list.c
	gcc -c list.c

app.o:app.c
	gcc -c app.c

bigSum.o:bigSum.c
	gcc -c bigSum.c

clean:
	rm *.o