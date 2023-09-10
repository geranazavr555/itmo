#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <string.h>

#ifdef __linux__ 
    void __attribute__((__cdecl__)) _print(char* out_buf, char const* format, char const* hex_number);
    #define print _print
    #define EOL "\n"
#elif _WIN32
    void __attribute__((__cdecl__)) print(char* out_buf, char const* format, char const* hex_number);
    #define EOL "\r\n"
#else
#endif


char* formats[] = {
    "+ 50",
    "+050",
    "+50",
    "- 50",
    "-050",
    "-50",
    "+ ",
    "+0",
    "+",
    "- ",
    "-0",
    "-"
};

char* formats_lib[] = {
    "%+ 50i",
    "%+050i",
    "%+50i",
    "%- 50i",
    "%-050i",
    "%-50i",
    "%+ i",
    "%+0i",
    "%+i",
    "%- i",
    "%-0i",
    "%-i"
};

static const int F_TESTS = sizeof(formats) / sizeof(formats[0]);

int test(char* str);

int test_wrap() {
    char* inputs[] = {
        "0x10",
        "0x0",
        "0x100",
        "0xFAFAFA",
        "0xDADADA",
        //"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF",
    };
    static const int I_TESTS = 5;
    for (int i = 0; i < I_TESTS; i++) {
        if (test(inputs[i])) {
            break;
        }
    }
    return 0;
    // char inp[128];
    // char buf[128];
    // // memset(buf, 0, sizeof(buf));
    // // scanf("%s", inp);

    // // sprintf(buf, "%-10i", 123);
    // _print(buf, "+ 5", inp);
    
    // int sz = strlen(buf);
    // buf[sz] = '~';
    // buf[sz+1] = 0;
    // putc('~', stdout);
    // puts(buf);
}

int test(char* str) {
    char buf_my[128];
    char buf_lib[128];
    for (int i = 0; i < F_TESTS; i++) {
        printf("input:{%s} ", str);
        printf("format:{%s}"EOL, formats_lib[i]);
        sprintf(buf_lib, formats_lib[i], strtoul(str, NULL, 0));
        print(buf_my, formats[i], str + 2);
        printf("my:\t %s"EOL, buf_my);
        printf("lib:\t %s"EOL, buf_lib);
        if (strcmp(buf_lib, buf_my)) {
            int j = 0;
            while (j < 10 && buf_lib[j] != 0) {
                printf("%u{%c} ", (int)buf_lib[j], buf_lib[j]);
                j++;
            }
            printf(EOL);
            j = 0;
            while (j < 10 && buf_my[j] != 0) {
                printf("%u{%c} ", (int)buf_my[j], buf_my[j]);
                j++;
            }
            printf(EOL);
            printf("ERROR!!!"EOL);
            return 1;
        }
    }
    return 0;
}

int main() {
     //test_wrap();
    char buf[256];
     print(buf, "+ 38", "-ffffffffffffffffffffffffffffffff");
    //print(buf, "", "1123");
    printf("%s", buf);
}