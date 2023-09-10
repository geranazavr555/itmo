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
	
		xor	edi, edi		; edi will store bool flags
					; 1  - negate
					; 2  - left_justification
					; 4  - force_sign
					; 8  - force_char_to_sign
					; 16 - left_pads_zeros
		xor	eax, eax	
		mov 	al, 45		; eax <- '-'
		mov	esi, [esp + 12 + 32]
		cmp	al, [esi]
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
		
parse_width_loop_end:	mov	[esp], eax		; save width
		jmp	parse_fmt_end

parse_fmt_loop_end:	inc 	ecx		; i++
		jmp	parse_fmt_loop

parse_fmt_end:	xor	esi, esi		; Start parsing hex number
					; ebp is a pointer to hex_number
		mov	ebp, [esp + 12 + 4 + 32]
		;int 3
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
		jmp	reverse_buf_pre
not_0:		call 	div_by_10
		mov	[buf + ecx], dl	; buf[i] = div_by_10();
		inc 	ecx		; i++
		jmp	number_char_loop

reverse_buf_pre:	cmp 	ecx, 0
		jne 	reverse_buf
		mov	byte [buf], 48 	; buf[0] = '0';
		inc 	ecx


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
		
		jmp 	format_out
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

format_out:		mov 	esi, [esp + 4 + 4 + 32]	; esi stores the pointer to out_buf
		cmp 	dword [esp], 0	; if (width > 0) {
		jng	spaces_loop_end
		xor	ebx, ebx		; int j = 0
spaces_loop:	cmp 	ebx, [esp]		; if (j < width)
		jnl	spaces_loop_end
		mov	byte [esi + ebx], 32	; out_buf[j] = ' ';
		inc 	ebx		; j++
		jmp 	spaces_loop
spaces_loop_end:	mov	ebx, [esp]		; ebx -- width
		mov	byte [esi + ebx], 0 	; out_buf[width] = 0

		xor	ebp, ebp		; int start = 0;
		bt 	edi, 1 		; if (left_justification
		jc 	fmt_case1		
		cmp 	ebx, 0 		; || width == 0) {
		je 	fmt_case1
		jmp 	fmt_case2

fmt_case1:		; int 3
		bt 	edi, 2 		; if (force_sign) {		
		jnc 	fmt_case1_2		
		mov	eax, 43		; '+'
fmt_case1_if_neg:	bt 	edi, 0 		; if (negate)
		jnc	fmt_case1_out
		mov	eax, 45		; '-'
		jmp	fmt_case1_out

fmt_case1_2:	mov	eax, 32		; ' '
		bt 	edi, 3 		; if (force_char_to_sign
		jc 	fmt_case1_if_neg	
		bt 	edi, 0 		; || negate)
		jc 	fmt_case1_if_neg
		jmp	fmt_case1_out_loop

fmt_case1_out:	mov	[esi], al		; out_buf[0] = (negate ? '-' : ???);
		inc 	ebp		; start = 1

fmt_case1_out_loop: 	push 	edi
		xor	eax, eax		; int j = 0;
		xor	edx, edx
fmt_case1_wl:	mov	dl, [buf + eax]	; edx stores buf[j]
		cmp 	dl, 0 		; while (buf[j] != 0) {
		je 	fmt_case1_wl_end
		mov	edi, ebp
		add	edi, eax
		mov	[esi + edi], dl	; out_buf[start + j] = buf[j];
		inc 	eax		; j++
		jmp	fmt_case1_wl

fmt_case1_wl_end: 	pop 	edi
		mov	edx, ebp
		add	edx, eax		; edx stores start + j
		cmp 	edx, ebx 		; if (start + j >= width) {
		jnge	fmt_exit
		mov	byte [esi + edx], 0 	; out_buf[start + j] = 0;
		jmp 	fmt_exit

fmt_case2:		bt 	edi, 4 		; if (left_pads_zeros) {
		jnc 	fmt_case4
		xor	eax, eax		; for (int j = 0;
fmt_case2_zl:	cmp 	eax, ebx		; j < width;
		jnl	fmt_case2_zl_end
		mov	byte [esi + eax], 48	; out_buf[j] = '0';
		inc 	eax		; j++
		jmp 	fmt_case2_zl
fmt_case2_zl_end:	mov	byte [esi + ebx], 0 	; out_buf[width] = 0;
		
		bt 	edi, 2 		; if (force_sign) {		
		jnc 	fmt_case3_2		
		mov	eax, 43		; '+'
fmt_case3_if_neg:	bt 	edi, 0 		; if (negate)
		jnc	fmt_case3_out
		mov	eax, 45		; '-'
		jmp	fmt_case3_out
fmt_case3_2:	mov	eax, 32		; ' '
		bt 	edi, 3 		; if (force_char_to_sign
		jc 	fmt_case3_if_neg	
		bt 	edi, 0 		; || negate)
		jc 	fmt_case3_if_neg
		jmp	fmt_case3_follow	


fmt_case3_out:	mov	[esi], al		; out_buf[0] = (negate ? '-' : ???);
		inc 	ebp		; start = 1

fmt_case3_follow:	mov	eax, ebp
		add	eax, ecx 		; eax stores start + i
		cmp 	eax, ebx		; if (start + i < width)
		jnl	fmt_case1_out_loop	
		mov	ebp, ebx		; start = width - i
		sub 	ebp, ecx
		jmp	fmt_case1_out_loop

					; case 4 -- width > 0, right justification, spaces
fmt_case4:		;int 3
		bt 	edi, 2 		; if (force_sign) {		
		jnc 	fmt_case4_2		
		mov	eax, 43		; '+'
fmt_case4_if_neg:	bt 	edi, 0 		; if (negate)
		jnc	fmt_case4_out
		mov	eax, 45		; '-'
		jmp	fmt_case4_out
fmt_case4_2:	mov	eax, 32		; ' '
		bt 	edi, 3 		; if (force_char_to_sign
		jc 	fmt_case4_if_neg	
		bt 	edi, 0 		; || negate)
		jc 	fmt_case4_if_neg
		jmp	fmt_case4_out_loop

fmt_case4_out:	mov	[esi], al		; out_buf[0] = (negate ? '-' : ???);
		inc 	ebp		; start = 1

fmt_case4_out_loop:	push 	edi
		xor	eax, eax		; int j = 0;
		xor	edx, edx
fmt_case4_wl:	mov	dl, [buf + eax]	; dl stores buf[j]
		cmp 	dl, 0 		; while (buf[j] != 0) {
		je 	fmt_case4_wl_end
		mov	edi, ebp
		add	edi, eax
		mov	[esi + edi], dl	; out_buf[start + j] = buf[j];
		inc 	eax		; j++
		jmp	fmt_case4_wl

fmt_case4_wl_end:	pop 	edi
		mov	edx, ebp
		add	edx, eax		; edx stores start + j
		cmp 	edx, ebx 		; if (start + j >= width) {
		jnge	fmt_case4_1
		mov	byte [esi + edx], 0 	; out_buf[start + j] = 0;
		jmp 	fmt_exit

fmt_case4_1:	mov	eax, ebx		; now we don't need flags. Can destroy value in edi
		sub 	eax, edx		; eax stores delta = width - (start + j)
		mov 	edx, ebx		; edx stores j = width - 1
		dec 	edx		; now we don't need width. Can destroy value in ebx
fmt_case4_1_loop:	cmp 	edx, eax 		; while (j - delta >= 0) {
		jnge	fmt_exit
		mov	edi, edx
		sub 	edi, eax		; edi stores j - delta
		xor	ebx, ebx
		mov	bl, [esi + edi]	; ebx stores out_buf[j - delta]
		mov	[esi + edx], bl	; out_buf[j] = out_buf[j - delta];
		mov	byte [esi + edi], 32 	; out_buf[j - delta] = ' ';
		dec	edx		; j--
		jmp	fmt_case4_1_loop


fmt_exit:		pop 	eax
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