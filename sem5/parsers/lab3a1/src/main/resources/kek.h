#ifndef KEK
#define KEK

#include <stdio.h>

long long readint() {
    long long x;
    scanf("%ld", &x);
    return x;
}

bool readbool() {
    long long x = readint();
    return x != 0;
}

bool print(long long x) {
    printf("%ld", x);
    return true;
}

bool printspace() {
    printf(" ");
    return true;
}


bool println() {
    printf("\n");
    return true;
}

#endif