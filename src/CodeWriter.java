/*
AUTHOR: Ian Utnehmer, MiraCosta
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeWriter {
    /*
    DESCRIPTION: Translates VM commands into Hack assembly code.
    PRECONDITION: outputFileName is a valid file path to a .asm file.
    POSTCONDITION: outputFile is a PrintWriter object that can be used to write to the output file.
     */
    private PrintWriter outputFile;
    private int jumpCounter = 0;
    private int labelCounter = 0;
    public CodeWriter(File outputFileName) throws FileNotFoundException {

        /*
        DESCRIPTION: Constructor for CodeWriter class.
        PRECONDITION: outputFileName is a valid file path to a .asm file.
        POSTCONDITION: outputFile is a PrintWriter object that can be used to write to the output file.
         */

        outputFile = new PrintWriter(outputFileName);
        // Exctract the name of the current file from the outputFileName:

    }
    public void writeArithmetic(String command) {

        /*
        DESCRIPTION: Writes to the output file the assembly code that implements the given arithmetic command.
        PRECONDITION: command is a valid arithmetic command.
        POSTCONDITION: The assembly code that implements the given arithmetic command has been written to the output file.
         */

        switch (command) {
            case "add":
                outputFile.println(ArithmeticFormatter1() + "D=D+M\n@SP\nM=M-1\nM=M-1\nA=M\nM=D\n@SP\nM=M+1");
                break;
            case "sub":
                outputFile.println(ArithmeticFormatter1() + "D=D-M\n@SP\nM=M-1\nM=M-1\nA=M\nM=D\n@SP\nM=M+1");
                break;
            case "neg":
                outputFile.println("D=0\n" +
                        "@SP\n" +
                        "A=M-1\n" +
                        "M=D-M");
                break;
            case "eq":
                outputFile.println(ArithmeticFormatter1() + ArithmeticFormatter2("JNE"));
                jumpCounter++;
                break;
            case "gt":
                outputFile.println(ArithmeticFormatter1() + ArithmeticFormatter2("JGT"));
                jumpCounter++;
                break;
            case "lt":
                outputFile.println(ArithmeticFormatter1() + ArithmeticFormatter2("JLT"));
                jumpCounter++;
                break;
            case "and":
                outputFile.println(ArithmeticFormatter1() + "M=M&D");
                break;
            case "or":
                outputFile.println(ArithmeticFormatter1() + "M=M|D");
                break;
            case "not":
                outputFile.println("@SP\n" +
                        "A=M-1\n" +
                        "M=!M");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    public String ArithmeticFormatter1() {

        /*
        DESCRIPTION: Returns a String that can be used to format arithmetic commands. For ease of access.
        PRECONDITION: None.
        POSTCONDITION: A String that can be used to format arithmetic commands has been returned.
         */

        return "@SP\n" +
                "A=M\n" +
                "A=A-1\n" +
                "A=A-1\n" +
                "D=M\n" +
                "A=A+1\n";
    }
    public String ArithmeticFormatter2(String strJump) {
        /*
        DESCRIPTION: Returns a String that can be used to format arithmetic commands. For ease of access.
        PRECONDITION: strJump is a valid jump command.
        POSTCONDITION: A String that can be used to format arithmetic commands has been returned.
         */

        return  "D=D-M\n" +
                "@SP\n" +
                "M=M-1\n" +
                "M=M-1\n" +
                "@TRUE" + jumpCounter + "\n" +
                "D;" + strJump + "\n" +
                "@SP\n" +
                "A=M\n" +
                "M=0\n" +
                "@END" + jumpCounter + "\n" +
                "0;JMP\n" +
                "(TRUE" + jumpCounter + ")\n" +
                "@SP\n" +
                "A=M\n" +
                "M=-1\n" +
                "(END" + jumpCounter + ")\n" +
                "@SP\n" +
                "M=M+1";
    }
    public void writePushPop(CommandType command, String segment, int index) {

        /*
        DESCRIPTION: Writes to the output file the assembly code that implements the given command, where command is either C_PUSH or C_POP.
        PRECONDITION: command is a valid CommandType, segment is a valid segment, and index is a valid index.
        POSTCONDITION: The assembly code that implements the given command has been written to the output file.
         */

        // Writes to the output file the assembly code that implements the given command, where command is either C_PUSH or C_POP.

        switch (command) {
            case C_PUSH:
                switch (segment) {
                    case "constant":
                        outputFile.println("@" + index + "\n" +
                                "D=A\n" +
                                "@SP\n" +
                                "A=M\n" +
                                "M=D\n" +
                                "@SP\n" +
                                "M=M+1");
                        break;
                    case "local":
                        outputFile.println(pushFormatter("LCL", index));
                        break;
                    case "argument":
                        outputFile.println(pushFormatter("ARG", index));
                        break;
                    case "this":
                        outputFile.println(pushFormatter("THIS", index));
                        break;
                    case "that":
                        outputFile.println(pushFormatter("THAT", index));
                        break;
                    case "temp":
                        outputFile.println(pushFormatter("R5", index + 5));
                        break;
                    case "pointer":
                        if (index == 0) {
                            outputFile.println("@THAT\n" +
                                    "D=M\n" +
                                    "@SP\n" +
                                    "A=M\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "M=M+1");
                        } else if (index == 1) {
                            outputFile.println("@THIS\n" +
                                    "D=M\n" +
                                    "@SP\n" +
                                    "A=M\n" +
                                    "M=D\n" +
                                    "@SP\n" +
                                    "M=M+1");
                        }
                        break;
                    case "static":
                        outputFile.println("@" + Parser.outputFileString + "." + index + "\n" +
                                "D=M\n" +
                                "@SP\n" +
                                "A=M\n" +
                                "M=D\n" +
                                "@SP\n" +
                                "M=M+1");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + segment);
                }
                break;
            case C_POP:
                switch (segment) {
                    case "local":
                        outputFile.println(popFormatter("LCL", index));
                        break;
                    case "argument":
                        outputFile.println(popFormatter("ARG", index));
                        break;
                    case "this":
                        outputFile.println(popFormatter("THIS", index));
                        break;
                    case "that":
                        outputFile.println(popFormatter("THAT", index));
                        break;
                    case "temp":
                        outputFile.println("@" + index + "\n" +
                                "D=A\n" +
                                "@R5\n" +
                                "D=D+A\n" +
                                "@R13\n" +
                                "M=D\n" +
                                "@SP\n" +
                                "AM=M-1\n" +
                                "@SP\n" +
                                "A=M\n" +
                                "D=M\n" +
                                "@R13\n" +
                                "A=M\n" +
                                "M=D\n");
                        break;
                    case "pointer":
                        if (index == 0) {
                            outputFile.println("@SP\n" +
                                    "M=M-1\n" +
                                    "A=M\n" +
                                    "D=M\n" +
                                    "@THIS\n" +
                                    "M=D");
                        } else if (index == 1) {
                            outputFile.println("@SP\nM=M-1\nA=M\nD=M\n@THAT\nM=D");
                        }
                        break;
                    case "static":
                        outputFile.println("@SP\n" +
                                "M=M-1\n" +
                                "A=M\n" +
                                "D=M\n" +
                                "@" + Parser.outputFileString + "." + index + "\n" +
                                "M=D\n");
                }

        }

    }
    private String pushFormatter(String segment, int index) {

        /*
        DESCRIPTION: Returns a String that can be used to format push commands.
        PRECONDITION: segment is a valid segment and index is a valid index.
        POSTCONDITION: A String that can be used to format push commands has been returned.
         */
        String ptr = "@" + segment + "\n" +
                "A=M\n" +
                "D=D+A\n" +
                "A=D\n" +
                "D=M\n";

        return "@" + index + "\n" +
                "D=A\n" +
                ptr +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1";
    }
    private String popFormatter(String segment, int index) {

        /*
        DESCRIPTION: Returns a String that can be used to format pop commands.
        PRECONDITION: segment is a valid segment and index is a valid index.
        POSTCONDITION: A String that can be used to format pop commands has been returned.
         */

        return "@" + index + "\n" +
                "D=A\n" +
                "@" + segment + "\n" +
                "A=M\n" +
                "D=D+A\n" +
                "@" + segment + "\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M\n" +
                "@" + segment + "\n" +
                "A=M\n" +
                "M=D\n" +
                "@" + index + "\n" +
                "D=A\n" +
                "@" + segment + "\n" +
                "A=M\n" +
                "D=A-D\n" +
                "@" + segment + "\n" +
                "M=D\n";
    }
    public void writeInit() {

        /*
        DESCRIPTION: Writes assembly code that effects the VM initialization, also called bootstrap code.
        PRECONDITION: None.
        POSTCONDITION: The assembly code that effects the VM initialization has been written to the output file.
         */

        outputFile.println("@256\n" +
                "D=A\n" +
                "@SP\n" +
                "M=D");
        writeCall("Sys.init", 0);
    }
    public void writeLabel(String label) {

        /*
        DESCRIPTION: Writes assembly code that effects the label command.
        PRECONDITION: label is a valid label.
        POSTCONDITION: The assembly code that effects the label command has been written to the output file.
         */

        outputFile.println("(" + Parser.outputFileString + "_" + label + ")");
    }
    public void writeGoto(String label) {

        /*
        DESCRIPTION: Returns a String that can be used to format goto commands.
        PRECONDITION: label is a valid label.
        POSTCONDITION: A String that can be used to format goto commands has been returned.
         */

        outputFile.println("@" + Parser.outputFileString + "_" + label + "\n0;JMP");
    }
    public void writeIf(String label) {

        /*
        DESCRIPTION: Returns a String that can be used to format if-goto commands.
        PRECONDITION: label is a valid label.
        POSTCONDITION: A String that can be used to format if-goto commands has been returned.
         */

        outputFile.println("@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M\n" +
                "@" + Parser.outputFileString + "_" + label + "\n" +
                "D;JNE");
    }
    public void writeFunction(String functionName, int numLocals) {

        /*
        DESCRIPTION: Writes assembly code that effects the function command.
        PRECONDITION: functionName is a valid function name and numLocals is a valid number of local variables.
        POSTCONDITION: The assembly code that effects the function command has been written to the output file.
         */

        outputFile.println("(" + functionName + ")");
        for (int i = 0; i < numLocals; i++) {
            writePushPop(CommandType.C_PUSH, "constant", 0);
        }
    }
    public void writeCall(String functionName, int numArgs) {

        /*
        DESCRIPTION: Returns a String that can be used to format call commands.
        PRECONDITION: functionName is a valid function name and numArgs is a valid number of arguments.
        POSTCONDITION: A String that can be used to format call commands has been returned.
         */

        String returnAddress = "RETURN_ADDRESS" + labelCounter;
        labelCounter++;
        outputFile.println("@" + returnAddress + "\n" +
                "D=A\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n" +
                "@LCL\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n" +
                "@ARG\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n" +
                "@THIS\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n" +
                "@THAT\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n" +
                "D=M\n" +
                "@" + numArgs + "\n" +
                "D=D-A\n" +
                "@5\n" +
                "D=D-A\n" +
                "@ARG\n" +
                "M=D\n" +
                "@SP\n" +
                "D=M\n" +
                "@LCL\n" +
                "M=D\n" +
                "@" + functionName + "\n" +
                "0;JMP\n" +
                "(" + returnAddress + ")");
    }
    public void writeReturn() {

        /*
        DESCRIPTION: Returns a String that can be used to format return commands.
        PRECONDITION: None.
        POSTCONDITION: A String that can be used to format return commands has been returned.
         */

        outputFile.println("@LCL\n" +
                "D=M\n" +
                "@FRAME\n" +
                "M=D\n" +
                "@5\n" +
                "D=D-A\n" +
                "A=D\n" +
                "D=M\n" +
                "@RET\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M\n" +
                "@ARG\n" +
                "A=M\n" +
                "M=D\n" +
                "@ARG\n" +
                "D=M+1\n" +
                "@SP\n" +
                "M=D\n" +
                "@FRAME\n" +
                "D=M\n" +
                "@1\n" +
                "D=D-A\n" +
                "A=D\n" +
                "D=M\n" +
                "@THAT\n" +
                "M=D\n" +
                "@FRAME\n" +
                "D=M\n" +
                "@2\n" +
                "D=D-A\n" +
                "A=D\n" +
                "D=M\n" +
                "@THIS\n" +
                "M=D \n" +
                "@FRAME\n" +
                "D=M\n" +
                "@3\n" +
                "D=D-A\n" +
                "A=D\n" +
                "D=M\n" +
                "@ARG\n" +
                "M=D\n" +
                "@FRAME\n" +
                "D=M\n" +
                "@4\n" +
                "D=D-A\n" +
                "A=D\n" +
                "D=M\n" +
                "@LCL\n" +
                "M=D\n" +
                "@RET\n" +
                "A=M\n" +
                "0;JMP\n");
    }
    public void close() {

        /*
        DESCRIPTION: Closes the output file.
        PRECONDITION: None.
        POSTCONDITION: The output file has been closed.
         */

        outputFile.close();
    }
}
