
// C_PUSH argument 1
@1
D=A
@ARG
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_POP pointer 1
@SP
M=M-1
A=M
D=M
@THAT
M=D

// C_PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP that 0
@0
D=A
@THAT
A=M
D=D+A
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THAT
A=M
M=D
@0
D=A
@THAT
A=M
D=A-D
@THAT
M=D


// C_PUSH constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP that 1
@1
D=A
@THAT
A=M
D=D+A
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THAT
A=M
M=D
@1
D=A
@THAT
A=M
D=A-D
@THAT
M=D


// C_PUSH argument 0
@0
D=A
@ARG
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1

// sub
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D-M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

// C_POP argument 0
@0
D=A
@ARG
A=M
D=D+A
@ARG
M=D
@SP
M=M-1
A=M
D=M
@ARG
A=M
M=D
@0
D=A
@ARG
A=M
D=A-D
@ARG
M=D

(FibonacciSeries_MAIN_LOOP_START)

// C_PUSH argument 0
@0
D=A
@ARG
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// if-goto COMPUTE_ELEMENT
@SP
M=M-1
A=M
D=M
@FibonacciSeries_COMPUTE_ELEMENT
D;JNE

// goto END_PROGRAM
@FibonacciSeries_END_PROGRAM
0;JMP
(FibonacciSeries_COMPUTE_ELEMENT)

// C_PUSH that 0
@0
D=A
@THAT
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH that 1
@1
D=A
@THAT
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// add
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D+M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

// C_POP that 2
@2
D=A
@THAT
A=M
D=D+A
@THAT
M=D
@SP
M=M-1
A=M
D=M
@THAT
A=M
M=D
@2
D=A
@THAT
A=M
D=A-D
@THAT
M=D


// C_PUSH pointer 1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1

// add
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D+M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

// C_POP pointer 1
@SP
M=M-1
A=M
D=M
@THAT
M=D

// C_PUSH argument 0
@0
D=A
@ARG
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1

// sub
@SP
A=M
A=A-1
A=A-1
D=M
A=A+1
D=D-M
@SP
M=M-1
M=M-1
A=M
M=D
@SP
M=M+1

// C_POP argument 0
@0
D=A
@ARG
A=M
D=D+A
@ARG
M=D
@SP
M=M-1
A=M
D=M
@ARG
A=M
M=D
@0
D=A
@ARG
A=M
D=A-D
@ARG
M=D


// goto MAIN_LOOP_START
@FibonacciSeries_MAIN_LOOP_START
0;JMP
(FibonacciSeries_END_PROGRAM)
