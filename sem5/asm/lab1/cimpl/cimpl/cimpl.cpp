#pragma warning(disable : 4996)

#include <cstdlib>
#include <cstdio>
#include <cstdint>
#include <cstring>

const int IN_LEN_CHARS = 32;

int parse_int(const char* str)
{
    int result = 0;
    int i = 0;
    while (str[i] != 0) {
        result *= 10;
        result += (str[i] & 15u);
        i++;
    }
    return result;
}


unsigned char number[16];
void parse_int128(const char* str)
{
    int i;
    for (i = 0; i < 16; ++i)
        number[i] = 0;

    i = 0;
    while (str[i] != 0)
    {
        number[15] <<= 4;
        for (int j = 15; j > 0; j--)
        {
            number[j] |= (number[j - 1] >> 4);
            number[j - 1] <<= 4;
        }

        if (str[i] & 64u)
        {
            number[0] |= ((str[i] & 15u) + 9);
        }
        else
        {
            number[0] |= (str[i] & 15u);
        }

        i++;
    }
}

void inverse_plus_1() {
    for (int i = 0; i < 16; ++i)
        number[i] = ~(number[i]);

    uint16_t carry = 1;
    for (int i = 0; i < 16; ++i) {
        uint16_t tmp = ((uint16_t)number[i] + carry);
        number[i] = tmp & 255u;
        carry = tmp >> 8;
    }
}

char div_by_10() {
    uint16_t carry = 0;
    for (int i = 15; i > 0; --i) {
        uint16_t cur = number[i] + (carry << 8u);
        number[i] = (unsigned char)(cur / 10);
        carry = cur % 10;
    }

    uint16_t cur = number[0] + (carry << 8u);
    number[0] = (unsigned char)(cur / 10);
    carry = cur % 10;

    return carry + '0';
}

bool ne0() {
    for (int i = 0; i < 16; ++i)
        if (number[i] != 0)
            return true;
    return false;
}

void print(char* out_buf, const char* format, const char* hex_number)
{
    bool negate = (hex_number[0] == '-');
    if (negate)
        hex_number = hex_number + 1;

    // format string parse
    bool left_justification = false;
    bool force_sign = false;
    bool force_char_to_sign = false;
    bool left_pads_zeros = false;
    int width = 0;

    int i = 0;
    char cur_format_char = format[0];
    while (cur_format_char != 0) {
        switch (cur_format_char)
        {
        case '-':
            left_justification = true;
            break;
        case '+':
            force_sign = true;
            break;
        case ' ':
            force_char_to_sign = true;
            break;
        case '0':
            left_pads_zeros = true;
            break;
        default:
            // width case
            width = parse_int(format + i);
            goto FMT_PARSE_END;
            break;
        }
        i++;
        cur_format_char = format[i];
    }
FMT_PARSE_END:
    
    parse_int128(hex_number);
    if (negate) {
        inverse_plus_1();
    }

    negate = number[15] & 128u;
    if (negate) {
        inverse_plus_1();
    }

    char buf[64];

    i = 0;
    while (ne0())
    {
        buf[i] = div_by_10();
        i++;
    }

    if (i == 0) {
        buf[0] = '0';
        i++;
    }

    for (int j = 0; j < i / 2; j++) {
        unsigned char x = buf[j];
        buf[j] = buf[i - j - 1];
        buf[i - j - 1] = x;
    }
    buf[i] = 0;

    if (width > 0) {
        for (int j = 0; j < width; ++j)
            out_buf[j] = ' ';
    }
    out_buf[width] = 0;

    int start = 0;
    if (left_justification || width == 0) {   // 1
        if (force_sign) { // 1.1
            out_buf[0] = (negate ? '-' : '+');
            start = 1;
        }
        else if (force_char_to_sign || negate) { //1.2
            out_buf[0] = (negate ? '-' : ' ');
            start = 1;
        }

        int j = 0;
        while (buf[j] != 0) {
            out_buf[start + j] = buf[j];
            j++;
        }
        if (start + j >= width)
            out_buf[start + j] = 0;
    }
    else { // 2
        if (left_pads_zeros) { // 3
            for (int j = 0; j < width; ++j)
                out_buf[j] = '0';
            out_buf[width] = 0;

            if (force_sign) { // 3.1
                out_buf[0] = (negate ? '-' : '+');
                start = 1;
            }
            else if (force_char_to_sign || negate) { //3.2
                out_buf[0] = (negate ? '-' : ' ');
                start = 1;
            }

            if (start + i < width)
                start = width - i;

            int j = 0;
            while (buf[j] != 0) {
                out_buf[start + j] = buf[j];
                j++;
            }
            if (start + j >= width)
                out_buf[start + j] = 0;
        }
        else { // 4
            // width > 0
            // right justification
            // spaces

            if (force_sign) {
                out_buf[0] = (negate ? '-' : '+');
                start = 1;
            }
            else if (force_char_to_sign || negate) {
                out_buf[0] = (negate ? '-' : ' ');
                start = 1;
            }

            int j = 0;
            while (buf[j] != 0) {
                out_buf[start + j] = buf[j];
                j++;
            }
            if (start + j >= width)
                out_buf[start + j] = 0;
            else { // 4.1
                int delta = width - (start + j);
                j = width - 1;
                while (j - delta >= 0) {
                    out_buf[j] = out_buf[j - delta];
                    out_buf[j - delta] = ' ';
                    j--;
                }
            }
        }
    }

}

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
    single_test("0", "+ 50");
    return 0;
}
//                                               -16<END>
//                                               -16