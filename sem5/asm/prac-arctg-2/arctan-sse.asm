section .text
        global _atan_sse
;        extern _printf

;        formatout: db "%.10f", 0

	align 16
        x: dd 1.0

	align 16
        eight: dd 8.0, 8.0, 8.0, 8.0

	align 16
	one: dd 1.0            

	align 16                                                 
	minusmask1: dd 80000000h, 80000000h, 80000000h, 80000000h
	minusmask0: dd 80000000h, 00000000h, 00000000h, 00000000h
	divconst: dd 1.0, 3.0, 5.0, 7.0
             
_atan_sse:  
                          
	mov eax, [esp + 8]
	movd xmm0, dword [esp + 4] ; [0, 0, 0, x]
	movaps xmm1, xmm0
	movaps xmm2, xmm0
	mulps xmm1, xmm1
	xorps xmm1, [minusmask0] ;-x^2
	pshufd xmm1, xmm1, 0h

	xorps xmm3, xmm3
                                    
	mulps xmm0, xmm1
	pshufd xmm0, xmm0, 10010011b
	addps xmm0, xmm2

	mulps xmm0, xmm1
	pshufd xmm0, xmm0, 10010011b
	addps xmm0, xmm2

	mulps xmm0, xmm1
	pshufd xmm0, xmm0, 10010011b
	addps xmm0, xmm2
                             
	movaps xmm4, [divconst]
	movaps xmm5, [eight]

	pshufd xmm2, xmm2, 0h
	mulps xmm2, xmm2
	mulps xmm2, xmm2
	mulps xmm2, xmm2 ; x^8

begin:        
        sub eax, 1
	jle end

	movaps xmm6, xmm0
	divps xmm6, xmm4
	addps xmm4, xmm5
	addps xmm3, xmm6
	mulps xmm0, xmm2
	         
end:                
	xorps xmm1, xmm1            
	addss xmm1, xmm3
	pshufd xmm3, xmm3, 00111001b
	addss xmm1, xmm3            
	pshufd xmm3, xmm3, 00111001b
	addss xmm1, xmm3            
	pshufd xmm3, xmm3, 00111001b
	addss xmm1, xmm3           
	
	movss dword [esp + 4], xmm1
	fld dword [esp + 4]
                                            
        ret
