import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        try {

            String inputFilePath = new File("input.txt").getAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equalsIgnoreCase("end")) {
                    continue;
                }

                int problemNumber = Integer.parseInt(line.trim());
                StringBuilder output = new StringBuilder(problemNumber + "\n");

                while (!(line = reader.readLine()).equalsIgnoreCase("end")) {
                    switch (problemNumber) {
                        case 1:
                            output.append(accept1(line.trim())).append("\n");
                            break;
                        case 2:
                            output.append(accept2(line.trim())).append("\n");
                            break;
                        case 3:
                            output.append(isAccepted3(line.trim())).append("\n");
                            break;
                        case 4:
                            output.append(accept4(line.trim())).append("\n");
                            break;
                        case 5:
                            output.append(accept5(line.trim())).append("\n");
                            break;
                        case 6:
                            output.append(accepts(line.trim())).append("\n");
                            break;
                        case 7:
                            output.append(acceptsString(line.trim())).append("\n");
                            break;
                        case 8:
                            output.append(contains101or010(line.trim())).append("\n");
                            break;
                        case 9:

                            output.append(containsNoConsecutiveCharacters(line.trim())).append("\n");
                            break;
                        case 10:
                            output.append(acceptsString10(line.trim())).append("\n");
                            break;

                    }
                }
                writer.write(output.toString().trim() + "\nx\n");
            }
            reader.close();
            writer.close();
            System.out.println("Check output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private static int transition1(int state, char input) {
        if (state == 0) {
            return (input == 'a') ? 0 : 1;
        } else if (state == 1) {
            return (input == 'a') ? 2 : 1;
        } else {
            return 2;
        }
    }

    // DFA function to check if input is accepted
    private static boolean accept1(String input) {
        int currentState = 0; // Start state
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            currentState = transition1(currentState, symbol);
        }
        return currentState == 0 || currentState == 1; // Accept if in final states q0 or q1
    }

    private static int transition2(int state, char input) {
        if (state == 0) {
            return (input == '1') ? 1 : 2;
        } else if (state == 1) {
            return (input == '1') ? 3 : 3;
        } else if (state == 2) {
            return (input == '0') ? 0 : 3;
        } else {
            return (input == '1') ? 3 : 3;
        }
    }

    // DFA function to check if input is accepted
    private static boolean accept2(String input) {
        int currentState = 0; // Start state
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            currentState = transition2(currentState, symbol);
        }
        return currentState == 1; // Accept if in final state q1
    }





    private static boolean isAccepted3(String input) {
        // DFA states
        final int q0 = 0;
        final int q1 = 1;

        int currentState = q0;

        // Transition function
        for (char c : input.toCharArray()) {
            if (currentState == q0 && c == 'y') {
                currentState = q0;
            } else if (currentState == q0 && c == 'x') {
                currentState = q1;
            } else if (currentState == q1 && c == 'y') {
                currentState = q1;
            } else if (currentState == q1 && c == 'x') {
                currentState = q0;
            } else {
                return false; // Invalid transition
            }
        }

        // Check if the final state is accepting
        return currentState == q1;
    }


    private static int transition4(int state, char input) {
        if (state == 0) {
            return (input == 'a') ? 1 : 3;
        } else if (state == 1) {
            return (input == 'a') ? 1 : 2;
        } else if (state == 2) {
            return (input == 'a') ? 1 : 2;
        } else if (state == 3) {
            return (input == 'a') ? 4 : 3;
        } else {
            return (input == 'a') ? 4 : 3;
        }
    }

    // DFA function to check if input is accepted
    private static boolean accept4(String input) {
        int currentState = 0; // Start state
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            currentState = transition4(currentState, symbol);
        }
        return currentState == 1 || currentState == 3; // Accept if in final states q1 or q3
    }




    private static int transition5(int state, char input) {
        if (state == 0) {
            return (input == '0') ? 0 : 1;
        } else if (state == 1) {
            return (input == '0') ? 2 : 3;
        } else if (state == 2) {
            return (input == '0') ? 0 : 1;
        } else {
            return (input == '0') ? 2 : 3;
        }
    }

    // DFA function to check if input is accepted
    private static boolean accept5(String input) {
        int currentState = 0; // Start state
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            currentState = transition5(currentState, symbol);
        }
        return currentState == 0; // Accept if in final state q0
    }





    public static boolean accepts(String input) {
        int state = 0;
        for (char c : input.toCharArray()) {
            if (state == 0 && c == '1') {
                state = 1;
            } else if (state == 1 && c == '1') {
                state = 2;
            } else if (state == 2 && c == '1') {
                // If already in state 2 and another '1' is encountered, reject
                return false;
            } else {
                state = 0;
            }
        }
        return state != 2;
    }


    public static boolean acceptsString(String input) {
        int currentState = 0;  // Initial state
        boolean q1 = false;
        boolean q2 = false;

        for (char c : input.toCharArray()) {
            switch (currentState) {
                case 0:
                    if (c == '0') {
                        currentState = 1;
                    }
                    break;
                case 1:
                    if (c == '1') {
                        currentState = 2;
                    } else if (c == '0') {
                        // If '01' is encountered, set q1 to true
                        q1 = true;
                    }
                    break;
                case 2:
                    if (c == '0') {
                        currentState = 3;
                        // If '10' is encountered, set q2 to true
                        q2 = true;
                    }
                    break;
                case 3:
                    if (c == '1') {
                        currentState = 2;
                    } else if (c == '0') {
                        currentState = 1;
                    }
                    break;
            }
        }

        // Return true if both '01' and '10' are encountered
        return q1 && q2;
    }




    public static boolean contains101or010(String input) {
        Pattern pattern = Pattern.compile(".*101.*|.*010.*");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }





    public static boolean containsNoConsecutiveCharacters(String input) {
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                return false; // Two consecutive characters are the same
            }
        }
        return true; // No two consecutive characters are the same
    }






    public static boolean acceptsString10(String input) {
        // Initialize a flag to track if a '0' is followed by at least one '1'
        boolean hasOneAfterZero = false;

        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == '0' && input.charAt(i + 1) == '1') {
                hasOneAfterZero = true;
            } else if (input.charAt(i) == '0' && input.charAt(i + 1) != '1') {
                return false; // If '0' is not followed by '1', return false
            }
        }

        // If '0' is the last character, return false
        if (input.charAt(input.length() - 1) == '0') {
            return false;
        }

        // If the input consists only of '1', return true
        if (!hasOneAfterZero && input.indexOf('0') == -1) {
            return true;
        }

        return hasOneAfterZero; // If '0' is followed by at least one '1', return true
    }





}