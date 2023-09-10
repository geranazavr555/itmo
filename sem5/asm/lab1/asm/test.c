#include <stdio.h>
#include <string.h>

extern void print(char *out_buf, const char *format, const char *hex_number);

int cnt = 0;

void single_test(const char* hex, const char* fmt)
{
    cnt++;
    printf("===== == %i == =====\n", cnt);

    int x;
    sscanf(hex, "%x", &x);

    char real_fmt[257] = "%";
    strcat(real_fmt, fmt);
    strcat(real_fmt, "i");

    printf(real_fmt, x);
    printf("<END>\n", x);

    char out[257];
    print(out, fmt, hex);
    printf("%s<END>\n", out);
}

int main()
{

//input:{0x0} format:{%+ 50i}
    single_test("0", "+ 50");

//   single_test("abacba", " 20");
//   single_test("abacba", "20");
//   single_test("abacba", "+20");
//   single_test("abacba", "-20");
//   single_test("abacba", "-+20");
//   single_test("abacba", "+-20");
//   single_test("abacba", "+-");
//   single_test("abacba", "-");
//   single_test("abacba", "+");
//   single_test("abacba", " ");
//   single_test("abacba", "0");
//   single_test("abacba", "0+0");
//   single_test("abacba", "000");
//   single_test("abacba", "50");
//   single_test("abacba", "-50");
//   single_test("abacba", "050");
//   single_test("abacba", " 5");
//   single_test("abacba", "5");
//   single_test("abacba", "+5");
//   single_test("abacba", "-5");
//   single_test("abacba", "-+5");

//   single_test("-1", " 20");
//   single_test("-1", "20");
//   single_test("-1", "+20");
//   single_test("-1", "-20");
//   single_test("-1", "-+20");
//   single_test("-1", "+-20");
//   single_test("-1", "+-");
//   single_test("-1", "-");
//   single_test("-1", "+");
//   single_test("-1", " ");
//   single_test("-1", "0");
//   single_test("-1", "0+0");
//   single_test("-1", "000");
//   single_test("-1", "50");
//   single_test("-1", "-50");
//   single_test("-1", "050");
//   single_test("-1", " 5");
//   single_test("-1", "5");
//   single_test("-1", "+5");
//   single_test("-1", "-5");
//   single_test("-1", "-+5");

    return 0;
//    char hex[256] = "abacba";
//
//    int x;
//    sscanf(hex, "%x", &x);
//    printf("% 20i", x);
//    printf("<END>\n", x);
//
//    char out[256];
//    print(out, " 20", hex);
//    printf("%s<END>\n", out);
//    return 0;

//   char hex[256] = "abacaba";

//   int x;
//   sscanf(hex, "%x", &x);
//   printf("%-5i", x);
//   printf("<END>\n", x);

//   char out[256];
//   print(out, "-5", hex);
//   printf("%s<END>\n", out);
//   printf("%i", strlen(out));
//   return 0;
}