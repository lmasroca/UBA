CC := g++ -O3

.PHONY: all
all: ej1 ej2 ej3

ej1: ej1.o
	$(CC) $^ -o $@

ej1.o: ej1.cpp
	$(CC) -c $< -o $@

ej2: ej2.o
	$(CC) $^ -o $@

ej2.o: ej2.cpp
	$(CC) -c $< -o $@

ej3: ej3.o
	$(CC) $^ -o $@

ej3.o: ej3.cpp
	$(CC) -c $< -o $@

.PHONY: clean
clean:
	rm -f *.o
	rm -f ej1
	rm -f ej2
	rm -f ej3