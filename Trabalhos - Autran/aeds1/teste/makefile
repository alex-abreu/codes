app.exe: app.o set.o
	gcc app.o set.o -o app.exe

app.o: app.c
	gcc -c app.c

set.o: set.c
	gcc -c set.c

clean:
	rm *.o