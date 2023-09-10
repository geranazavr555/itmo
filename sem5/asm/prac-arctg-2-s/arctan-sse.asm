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

;_main:     
;        push 1000000
;        push dword [x]
;        call arctg_taylor
;        
;        fstp qword [esp]
;        push formatout
;        call _printf
;        add esp, 12
;
;        ret
        

_atan_sse:  
                          
	mov eax, [esp + 8]
	movss xmm0, dword [esp + 4]
	xorps xmm1, xmm1
	movss xmm2, dword [two]
	movss xmm3, dword [one]

	movss xmm4, xmm0
	mulss xmm0, xmm0 ; x^2
	xorps xmm0, [minusmask0]
	
      
begin:

	movss xmm5, xmm4
	divss xmm5, xmm3
	addss xmm1, xmm5
	mulss xmm4, xmm0
	addss xmm3, xmm2

        sub eax, 1

        jnz begin
end:                


	movss dword [esp + 4], xmm1
	fld dword [esp + 4]

	; ??????????????????????????????????
        ret
