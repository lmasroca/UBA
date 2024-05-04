CC := g++ -I ./Eigen/

.PHONY: all
all: tp2

tp2: tp2.o
	$(CC) -O3 $^ -o $@

tp2.o: tp2.cpp
	$(CC) -O3 -c $< -o $@

.PHONY: clean
clean:
	rm -f *.o
	rm -f tp2