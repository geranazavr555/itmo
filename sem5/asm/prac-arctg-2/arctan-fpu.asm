section .text
        global _atan_fpu
;        extern _printf

;        formatout: db "%.10f", 0
        x: dd 1.0
        two: dd 2.0

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
        

_atan_fpu:   
        fld dword [two]
        fld dword [esp + 4]
        fld st0
        fmulp
        fchs
        fld dword [esp + 4]
        fldz
        fld1

        mov eax, [esp + 8]
              
begin:

        fld st2
        fld st1
        fdivr st0, st1
        faddp st3, st0
        fmul st0, st4
        fstp st3
        fadd st4

        sub eax, 1

        jnz begin
end:                
        fstp st0      
        ffree st1
        ffree st2
        ffree st3 

        ret
