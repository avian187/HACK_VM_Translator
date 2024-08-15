//bootstrap
@256
D=A
@SP
M=D

// call Sys.init 0
@RETURN_ADDRESS0
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.init
0;JMP
(RETURN_ADDRESS0)

// function Sys.init 0
(Sys.init)

// C_PUSH constant 4000
@4000
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP pointer 0
@SP
M=M-1
A=M
D=M
@THIS
M=D

// C_PUSH constant 5000
@5000
D=A
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

// call Sys.main 0
@RETURN_ADDRESS1
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.main
0;JMP
(RETURN_ADDRESS1)

// C_POP temp 1
@1
D=A
@R5
D=D+A
@R13
M=D
@SP
AM=M-1
@SP
A=M
D=M
@R13
A=M
M=D

(1Sys_LOOP)

// goto LOOP
@1Sys_LOOP
0;JMP

// function Sys.main 5
(Sys.main)

// C_PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH constant 4001
@4001
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP pointer 0
@SP
M=M-1
A=M
D=M
@THIS
M=D

// C_PUSH constant 5001
@5001
D=A
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

// C_PUSH constant 200
@200
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP local 1
@1
D=A
@LCL
A=M
D=D+A
@LCL
M=D
@SP
M=M-1
A=M
D=M
@LCL
A=M
M=D
@1
D=A
@LCL
A=M
D=A-D
@LCL
M=D


// C_PUSH constant 40
@40
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP local 2
@2
D=A
@LCL
A=M
D=D+A
@LCL
M=D
@SP
M=M-1
A=M
D=M
@LCL
A=M
M=D
@2
D=A
@LCL
A=M
D=A-D
@LCL
M=D


// C_PUSH constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP local 3
@3
D=A
@LCL
A=M
D=D+A
@LCL
M=D
@SP
M=M-1
A=M
D=M
@LCL
A=M
M=D
@3
D=A
@LCL
A=M
D=A-D
@LCL
M=D


// C_PUSH constant 123
@123
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Sys.add12 1
@RETURN_ADDRESS2
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
D=M
@1
D=D-A
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.add12
0;JMP
(RETURN_ADDRESS2)

// C_POP temp 0
@0
D=A
@R5
D=D+A
@R13
M=D
@SP
AM=M-1
@SP
A=M
D=M
@R13
A=M
M=D


// C_PUSH local 0
@0
D=A
@LCL
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH local 1
@1
D=A
@LCL
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH local 2
@2
D=A
@LCL
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH local 3
@3
D=A
@LCL
A=M
D=D+A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1

// C_PUSH local 4
@4
D=A
@LCL
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

// return
@LCL
D=M
@FRAME
M=D
@5
D=D-A
A=D
D=M
@RET
M=D
@SP
M=M-1
A=M
D=M
@ARG
A=M
M=D
@ARG
D=M+1
@SP
M=D
@FRAME
D=M
@1
D=D-A
A=D
D=M
@THAT
M=D
@FRAME
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D 
@FRAME
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
@FRAME
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
@RET
A=M
0;JMP


// function Sys.add12 0
(Sys.add12)

// C_PUSH constant 4002
@4002
D=A
@SP
A=M
M=D
@SP
M=M+1

// C_POP pointer 0
@SP
M=M-1
A=M
D=M
@THIS
M=D

// C_PUSH constant 5002
@5002
D=A
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

// C_PUSH constant 12
@12
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

// return
@LCL
D=M
@FRAME
M=D
@5
D=D-A
A=D
D=M
@RET
M=D
@SP
M=M-1
A=M
D=M
@ARG
A=M
M=D
@ARG
D=M+1
@SP
M=D
@FRAME
D=M
@1
D=D-A
A=D
D=M
@THAT
M=D
@FRAME
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D 
@FRAME
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
@FRAME
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
@RET
A=M
0;JMP

