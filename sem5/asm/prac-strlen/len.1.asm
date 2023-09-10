global _my_strlen
extern _printf

section .text

        formatout: db "%d", 0
        teststr: db "testababuiyhajkdf", 0

_my_strlen:
        push edx
        push ecx
        push ebx
        push ebp

        xor eax, eax
        xor edx, edx
        mov ecx, [esp + 20]

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

        pop ebp
		pop ebx
        pop ecx
        pop edx
        emms
        ret

