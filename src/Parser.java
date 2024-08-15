/*
Author: Ian Utnehmer, MiraCosta
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    /*
    DESCRIPTION: Handles the parsing of a single .vm file, and encapsulates access to the input code.
    PRECONDITION: The input file is a valid .vm file.
    POSTCONDITION: The input file has been parsed and can be accessed as a stream of commands.
     */


    public static String outputFileString = "";
    public static final ArrayList<String> Commands = new ArrayList<String>();

    private String arg1;
    private int arg2;
    private Scanner inputFile;
    public CommandType currentCommand;

    public Parser(File inputFileName) throws FileNotFoundException {

        /*
        DESCRIPTION: Constructor for Parser class.
        PRECONDITION: inputFileName is a valid file path to a .vm file.
        POSTCONDITION: inputFile is a Scanner object that can be used to read the input file.
         */

        inputFile = new Scanner(inputFileName);
        Commands.add("add");
        Commands.add("sub");
        Commands.add("neg");
        Commands.add("eq");
        Commands.add("gt");
        Commands.add("lt");
        Commands.add("and");
        Commands.add("or");
        Commands.add("not");

        String[] outputFileNameArray = inputFileName.toString().split("\\\\");
        outputFileString = outputFileNameArray[outputFileNameArray.length - 1].substring(0, outputFileNameArray[outputFileNameArray.length - 1].length() - 3);
    }

    public CommandType getCurrentCommand() {

        /*
        DESCRIPTION: Returns the current command type.
        PRECONDITION: currentCommand is a valid CommandType.
        POSTCONDITION: currentCommand is returned.
         */
        return currentCommand;
    }

    public boolean hasMoreCommands() {

        /*
        DESCRIPTION: Returns true if there are more commands in the input file.
        PRECONDITION: inputFile is a valid Scanner object.
        POSTCONDITION: Returns true if there are more commands in the input file.

         */

        return inputFile.hasNextLine();
    }

    public String cleanLine(String line) {

        /*
        DESCRIPTION: Removes all comments from a line.
        PRECONDITION: line is a valid String.
        POSTCONDITION: Returns a String with all comments removed.
         */

        // Remove all comments from line, alongside all spaces.

        int position = line.indexOf("//");
        if (position != -1) {
            line = line.substring(0, position);
        }
        return line;
    }

    public void advance() {

        /*
        DESCRIPTION: Reads the next command from the input file and makes it the current command.
        PRECONDITION: inputFile is a valid Scanner object.
        POSTCONDITION: The next command is read from the input file and made the current command.
         */

        arg1 = "";
        arg2 = -1;
        String line = "";
        do {
            line = cleanLine(inputFile.nextLine());
        }
        while (line.equals(""));
        String[] lineArray = line.split(" ");
        if (Commands.contains(lineArray[0])) {
            currentCommand = CommandType.C_ARITHMETIC;
            arg1 = lineArray[0];
        } else if (lineArray[0].equals("return")) {
            currentCommand = CommandType.C_RETURN;
            arg1 = lineArray[0];
        }else {
            arg1 = lineArray[1];
            switch (lineArray[0]) {
                case "push":
                    currentCommand = CommandType.C_PUSH;
                    break;
                case "pop":
                    currentCommand = CommandType.C_POP;
                    break;
                case "label":
                    currentCommand = CommandType.C_LABEL;
                    break;
                case "goto":
                    currentCommand = CommandType.C_GOTO;
                    break;
                case "if-goto":
                    currentCommand = CommandType.C_IF;
                    break;
                case "function":
                    currentCommand = CommandType.C_FUNCTION;
                    break;
                case "return":
                    currentCommand = CommandType.C_RETURN;
                    break;
                case "call":
                    currentCommand = CommandType.C_CALL;
                    break;
            }
            if (currentCommand == CommandType.C_PUSH
                    || currentCommand == CommandType.C_POP
                    || currentCommand == CommandType.C_FUNCTION
                    || currentCommand == CommandType.C_CALL) {
                try {
                    arg2 = Integer.parseInt(lineArray[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error: NumberFormatException in Parser.advance().");
                }
            }
        }
    }

    public String arg1() {

            /*
            DESCRIPTION: Returns the first argument of the current command.
            PRECONDITION: arg1 is a valid String.
            POSTCONDITION: arg1 is returned.
            */

            return arg1;
    }

    public int arg2() {

        /*
        DESCRIPTION: Returns the third argument of the current command.
        PRECONDITION: arg2 is a valid String.
        POSTCONDITION: arg2 is returned.
         */

        return arg2;
    }

}
