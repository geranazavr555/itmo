    .global cheburek
    .extern puts
    .extern fputs
    .extern stdout


    .text
lol_kek:

    xor %rsi, %rsi
    add $1, %rsi

    cmp %rsi, %rdi
    je 2f

    add $1, %rsi

    cmp %rsi, %rdi
    je 3f

1:  mov $0, %rdx
    mov %rdi, %rax
    div %rsi
    mov $0, %r8
    cmp %rdx, %r8
    je 2f

    mov %rsi, %rax
    mul %rax

    cmp %rdi, %rax

    jg 3f

    add $1, %rsi

    jmp 1b

2:  mov $0, %rax
    jmp 4f

3:  mov $1, %rax
    jmp 4f

4:  ret


cheburek:
        
    pushq %r14
    pushq %r15

    xor %r14, %r14
    mov $0x7FFFFFFFFFFFFFFF, %r15

    mov %rdi, %r11 
    xor %r10, %r10

    cmp %r10, %rsi
    je 3f

1:  pushq %rsi
    sub $8, %rsp
    mov 0(%r11, %r10, 4), %rdi

    mov $0x00000000FFFFFFFF, %r8
    and %r8, %rdi

    call lol_kek

    mov $0, %r8
    cmp %r8, %rax
    je 2f

    cmp %rdi, %r14
    cmovl %rdi, %r14

    cmp %rdi, %r15
    cmovg %rdi, %r15

2:

    add $8, %rsp
    pop %rsi

    inc %r10
    cmp %r10, %rsi
    je 3f

    jmp 1b


3:
    mov %r14, %rax

    mul %r15


    pop %r15
    pop %r14
    ret