.section .data
.section .bss
.section .text
.globl main
main:
pushq %rbp
movq %rsp, %rbp
movl -0(%ebp), %eax
movl $1, %eax
popq %rbp
ret


