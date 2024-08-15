import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class VMTranslator {

    CodeWriter codeWriter;
    Parser parser;

    public static void main(String[] args) throws IOException {
        /*
        DESCRIPTION: Main method for VMTranslator.
        PRECONDITION: N/A.
        POSTCONDITION: N/A.
         */

        System.out.println("Enter directory of .vm vmFiles to translate in order (absolute path): ");
        String directoryPath = new Scanner(System.in).nextLine();
        File directory = new File(directoryPath);

        // Find all vmFiles containing ".vm" in the given directory, and add them to an arraylist named "vmFiles":
        ArrayList<File> vmFiles = new ArrayList<>();
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().contains(".vm")) {
                vmFiles.add(file);
            }
        }

        // Create an output file in the same directory as the input vmFiles, with ending .asm:
        String outputFilePath = directoryPath + "/" + directory.getName() + ".asm";
        File outputFile = new File(outputFilePath);

        // Create a CodeWriter object to write to the output file:
        CodeWriter codeWriter = new CodeWriter(outputFile);
        System.out.println("Run writeInit()? (enter 'y' or 'n')");
        if (new Scanner(System.in).nextLine().equals("y"))
            codeWriter.writeInit();
        for (File file : vmFiles) {
            System.out.println("Translating " + file.getName() + "...");
            Parser parser = new Parser(file);
            while (parser.hasMoreCommands()) {
                parser.advance();
                CommandType command = parser.getCurrentCommand();
                switch (parser.getCurrentCommand()) {
                    case C_ARITHMETIC:
                        codeWriter.writeArithmetic(parser.arg1());
                        break;
                    case C_PUSH, C_POP:
                        codeWriter.writePushPop(command, parser.arg1(), parser.arg2());
                        break;
                    case C_LABEL:
                        codeWriter.writeLabel(parser.arg1());
                        break;
                    case C_GOTO:
                        codeWriter.writeGoto(parser.arg1());
                        break;
                    case C_IF:
                        codeWriter.writeIf(parser.arg1());
                        break;
                    case C_FUNCTION:
                        codeWriter.writeFunction(parser.arg1(), parser.arg2());
                        break;
                    case C_RETURN:
                        codeWriter.writeReturn();
                        break;
                    case C_CALL:
                        codeWriter.writeCall(parser.arg1(), parser.arg2());
                        break;
                }
            }
        }

        codeWriter.close();
        System.out.println("All vmFiles translated & outputted to file " + outputFilePath);
    }
}
