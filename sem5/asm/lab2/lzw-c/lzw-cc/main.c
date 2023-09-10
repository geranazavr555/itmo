#include <stdint.h>
#include <stdlib.h>
#include <stddef.h>
#include <stdio.h>

uint32_t get_bits(const uint8_t* in, size_t byte_pos, uint8_t bit_pos, uint8_t count, size_t* byte_pos_out, uint8_t* bit_pos_out) {
    uint32_t result = (in[byte_pos] >> bit_pos);
    byte_pos++;
    uint8_t count1 = 8 - bit_pos;
    count -= count1;
    bit_pos = 0;
    if (count >= 8) {
        result |= ((uint32_t) in[byte_pos]) << count1;
        byte_pos++;
        count -= 8;
        count1 += 8;
    }
    if (count > 0) {
        result |= ((uint32_t)(in[byte_pos] & ((1 << count) - 1))) << count1;
        bit_pos = count;
    }                        
    *byte_pos_out = byte_pos;
    *bit_pos_out = bit_pos;
    return result;
}

const size_t TABLE_SIZE = (1 << 12);
    
size_t byte_pos;
uint8_t bit_pos;
uint8_t bit_count;         
const uint8_t *in;
size_t in_size;

uint8_t** table;

uint8_t PREDEF_TABLE[256];

size_t out_pos;

void initialize(const uint8_t* in_, size_t in_size_) {
    byte_pos = 0;
    bit_pos = 0;
    bit_count = 9;
    in = in_;
    in_size = in_size_;
    out_pos = 0;

    for (size_t i = 0; i < 256; i++) {
        PREDEF_TABLE[i] = i;
    }
}

uint8_t* input;

uint32_t get_next_code() {
    return get_bits(input, byte_pos, bit_pos, bit_count, byte_pos, bit_pos);
}

enum special_codes {
    CLEAR = 256;
    EOI = 257;
}
    

void initialize_table() {
    for (size_t i = 0; i < 256; i++)
        table[i] = PREDEF_TABLE + i;
    for (size_t i = 256; i < TABLE_SIZE; i++)
        table[i] = NULL;
}
               
size_t write_string(uint8_t *restrict out, size_t offset, uint32_t code, size_t len) {
    memcpy((out + offset), table[code], len);
}

size_t lzw_decode(const uint8_t *in, size_t in_size, uint8_t *restrict out, size_t out_size) {
    initialize(in, in_size);
    uint8_t* table_[TABLE_SIZE];
    table = &table_;

    uint8_t code, old_code;
    size_t len = 1, offset = 0;
    while ((code = get_next_code()) != EOI) {
        if (code == CLEAR) {
            initialize_table();
            code = get_next_code();
            if (code == EOI)
                break;  
            offset += write_string(out, offset, code, len);
            // len -- ???      
            old_code = code;
        } else {
            if (is_in_table(code)) {                                         
                
            } else {
            
            }
        }
    }    
}

const uint8_t TEST[] = {0xFF, 0xFA, 0x00, 0xAB, 0x75, 0x67, 0x31, 0x11, 0x00, 0xFF, 0x00};

int main() {
    size_t byte_pos_out;
    uint8_t bit_pos_out;
    printf("%04x\n", get_bits(TEST, 1, 1, 12, &byte_pos_out, &bit_pos_out));
    printf("%d\n", byte_pos_out);
    printf("%d\n", bit_pos_out);
    return 0;
}            