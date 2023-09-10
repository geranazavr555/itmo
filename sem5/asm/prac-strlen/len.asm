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

        movq xmm1, [ecx]
        pxor xmm0, xmm0
        pcmpeqb xmm0, xmm1
        pmovmskb ebx, xmm0

        cmp ebx, 0
        jne found_non_zero_pack

        add ecx, 16
        add eax, 16

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

