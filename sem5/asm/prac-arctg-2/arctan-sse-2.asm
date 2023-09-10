section .text
        global _atan_sse
;        extern _printf

;        formatout: db "%.10f", 0

	align 16
        x: dd 1.0

	align 16
        two: dd 2.0

	align 16
	one: dd 1.0            

	align 16
	minusmask0: dd 80000000h, 80000000h, 80000000h, 80000000h
             
_atan_sse:  
                          
	mov eax, [esp + 8]
	movd xmm0, dword [esp + 4]
	xorps xmm1, xmm1
	movd xmm2, dword [two]
	movd xmm3, dword [one]

	movaps xmm4, xmm0 ; ???????
	mulps xmm0, xmm0 ; x^2
	xorps xmm0, [minusmask0]
	
      
begin:

	movaps xmm5, xmm4
	divps xmm5, xmm3
	addps xmm1, xmm5
	mulps xmm4, xmm0
	addps xmm3, xmm2

        sub eax, 1

        jnz begin
end:                


	movss dword [esp + 4], xmm1 ; ???
	fld dword [esp + 4]

	; ??????????????????????????????????
        ret
