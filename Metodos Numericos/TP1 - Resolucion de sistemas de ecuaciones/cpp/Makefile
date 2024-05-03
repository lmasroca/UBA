CC := g++

.PHONY: all
all: tp1

tp1: tp1.o
	$(CC) -O3 $^ -o $@

tp1.o: tp1.cpp
	$(CC) -O3 -c $< -o $@

.PHONY: clean
clean:
	rm -f *.o
	rm -f tp1
