section .text
        global my_strlen
        global _main
        extern _printf

        formatout: db "%d", 0
        teststr: db "test", 0



my_strlen:
        xor eax, eax
        xor edx, edx
        mov ecx, [esp + 4]

loop_start:

        movq mm1, [ecx]
        pxor mm0, mm0
        pcmpeqb mm0, mm1
        pmovmskb ebx, mm0

        cmp ebx, 0
        jne found_non_zero_pack

        add ecx, 8
        add eax, 8

        jmp loop_start

found_non_zero_pack:

        bsf ebp, ebx
        add eax, ebp

        ret


_main:     
        push teststr
        call my_strlen
        add esp, 4
        
        push eax
        push formatout
        call _printf
        add esp, 8

        ret
