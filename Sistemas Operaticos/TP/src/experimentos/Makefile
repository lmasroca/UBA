CC := g++ -O3 -pthread

.PHONY: all
all: bandera

bandera: bandera.o
	$(CC) $^ -o $@

bandera.o: bandera.cpp
	$(CC) -c $< -o $@

.PHONY: clean
clean:
	rm -f *.o
	rm -f bandera