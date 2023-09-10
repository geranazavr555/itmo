global _print

section .data
ten:		dd 10
number:		dd 0, 0, 0, 0
buf:		dd 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1

section .text
_print:
					; void print(char *out_buf, const char *format, const char *hex_number)
					; out_buf 		-- esp + 4 + offset
					; format  		-- esp + 8 + offset
					; hex_number	-- esp + 12  + offset
		pushad
		;int 	3
	
		xor	edi, edi		; edi will store bool flags
					; 1  - negate
					; 2  - left_justification
					; 4  - force_sign
					; 8  - force_char_to_sign
					; 16 - left_pads_zeros
		xor	eax, eax	
		mov 	al, 45		; eax <- '-'
		cmp	al, [esp + 12 + 32]
		jne	parse_fmt
	
		or 	edi, 1		; negate = true
		inc	dword [esp + 12 + 32]	; actually we don't need the first '-' byte
	
parse_fmt:		push 	dword 0 		; width = 0
		xor	ecx, ecx		; i = 0
	
parse_fmt_loop:	xor	edx, edx
		mov	esi, [esp + 8 + 4 + 32]	; esi stores the pointer to format
		mov	dl, [esi + ecx]	; dl stores cur_format_char
		cmp	dl, 0		; esi + ecx is the pointer to cur_format_char
		je	parse_fmt_end
	
		cmp	dl, 45		; - case
		jne	parse_fmt_plus
		or 	edi, 2
		jmp	parse_fmt_loop_end

parse_fmt_plus:	cmp	dl, 43		; + case
		jne	parse_fmt_space
		or 	edi, 4
		jmp	parse_fmt_loop_end

parse_fmt_space:	cmp	dl, 32		; space case
		jne	parse_fmt_zero
		or 	edi, 8
		jmp	parse_fmt_loop_end

parse_fmt_zero:	cmp	dl, 48		; 0 case
		jne	parse_fmt_width
		or 	edi, 16
		jmp	parse_fmt_loop_end

parse_fmt_width:	xor	eax, eax		; Parsing width (int)
		;int 	3
parse_width_loop:	cmp	dl, 0
		je 	parse_width_loop_end
		xor	ebx, ebx
		mov	bl, dl
		mul	dword [ten]
		mov	dl, bl
		and	dl, 15
		add	eax, edx
		inc	ecx
		xor	edx, edx
		mov	dl, [esi + ecx]
		jmp	parse_width_loop
		
parse_width_loop_end:	mov	[esp + 32], eax	; save width ?????????????????????????????????????????
		jmp	parse_fmt_end

parse_fmt_loop_end:	inc 	ecx		; i++
		jmp	parse_fmt_loop

parse_fmt_end:	xor	esi, esi		; Start parsing hex number
					; ebp is a pointer to hex_number
		mov	ebp, [esp + 12 + 4 + 32]
parse_hex:		xor	eax, eax
		mov	al, [ebp + esi]	; al -- str[i]
		cmp	al, 0		; esi -- i
		je	parse_hex_end	

		mov	ebx, [number + 8]	; shift number array left with 4 bits
		shld	[number + 12], ebx, 4
		mov	ebx, [number + 4]
		shld	[number + 8], ebx, 4
		mov	ebx, [number]
		shld	[number + 4], ebx, 4
		shl	dword [number], 4

		mov 	cl, 64
		and	cl, al		; cl -- str[i] & 64u
		and	al, 15		; al -- str[i] & 15u
		cmp	cl, 0 		; if (str[i] & 64u)
		je 	parse_hex_inc_i
		add	al, 9		; ((str[i] & 15u) + 9)
parse_hex_inc_i:	or 	[number], al 	; number[0] |=
		inc 	esi 		; i++
		jmp	parse_hex

parse_hex_end:	bt 	edi, 0 		; if (negate) {
		jnc	follow1		
		call 	inverse_plus_1
follow1:		bt 	dword [number + 12], 31 ; if (number[15] & 128u)
		jc 	set_negate_true
		and	edi, 0xfffffffe	; negate = false
		jmp	follow2
set_negate_true:	or	edi, 1 		; negate = true
		call 	inverse_plus_1

follow2:		xor 	ecx, ecx		; i = 0
number_char_loop:	cmp	dword [number], 0
		jne 	not_0
		cmp	dword [number + 4], 0
		jne 	not_0
		cmp	dword [number + 8], 0
		jne 	not_0
		cmp	dword [number + 12], 0
		jne 	not_0
		jmp	reverse_buf
not_0:		call 	div_by_10
		mov	[buf + ecx], dl	; buf[i] = div_by_10();
		inc 	ecx		; i++
		jmp	number_char_loop


reverse_buf:	xor	ebx, ebx		; j = 0 in ebx
		mov	ebp, ecx		
		shr	ebp, 1 		; ebp stores i / 2
reverse_buf_iter:	cmp 	ebx, ebp		; if (j < i / 2)
		jge	reverse_buf_end
		mov	dl, [buf + ebx]	; x = buf[j];
		mov	esi, ecx		
		sub 	esi, ebx
		dec	esi 		; esi stores i - j - 1
		mov 	al, [buf + esi]	; al stores buf[i - j - 1]
		mov	[buf + ebx], al	; buf[j] = buf[i - j - 1]
		mov	[buf + esi], dl	; buf[i - j - 1] = x
		inc 	ebx		; j++
		jmp 	reverse_buf_iter

reverse_buf_end:	mov	[buf + ecx], byte 0 	; buf[i] = 0
		;int 3
		
					; TMP: just copy buf to out_buf
		mov 	ebp, [esp + 4 + 4 + 32]	
		xor 	ecx, ecx
tmp1:		mov	al, [buf + ecx]
		mov	[ebp + ecx], al
		inc 	ecx
		cmp 	ecx, 64
		jne	tmp1

		pop 	eax
		popad
		ret

inverse_plus_1:	not	dword [number]	; two's complement of number
		not	dword [number + 4]
		not	dword [number + 8]
		not	dword [number + 12]
		add	dword [number], dword 1
		adc	dword [number + 4], dword 0
		adc	dword [number + 8], dword 0
		adc	dword [number + 12], dword 0
		ret

div_by_10:		xor	edx, edx		; divides number by 10. Returns reminder as a char in edx
		mov	ebx, 10
		mov	eax, [number + 12]
		div 	ebx
		mov	[number + 12], eax
		mov	eax, [number + 8]
		div 	ebx
		mov	[number + 8], eax
		mov	eax, [number + 4]
		div 	ebx
		mov	[number + 4], eax
		mov	eax, [number]
		div 	ebx
		mov	[number], eax
		add	edx, 48		; '0' is 48
		ret