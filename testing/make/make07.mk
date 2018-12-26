foo.o: foo.c foo.h
        aaaaa

foo: foo.o
	gcc -o foo foo.o

foo.c: foo.y
	yacc -o foo.c foo.y
