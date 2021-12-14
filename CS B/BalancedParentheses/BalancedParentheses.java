import java.util.Scanner;
import java.util.Stack;

public class BalancedParentheses {
    static boolean checkBrackets(String input) {
        Stack<Character> stack = new Stack<Character>(); //create stack

        for (int i = 0; i < input.length(); i++) { //traverse expression
            char c = input.charAt(i);
            char temp;

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }

            if (stack.isEmpty()) {
                return false;
            }
            
            if (c == ')') {
                temp = stack.pop();
                if (temp != '(') {
                    return false;
                }
            } else if (c == ']') {
                temp = stack.pop();
                if (temp != '[') {
                    return false;
                }
            } else if (c == '}') {
                temp = stack.pop();
                if (temp != '{') {
                    return false;
                }
            }
        }
        return (stack.isEmpty());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an expression to validate:");
        String input = in.nextLine(); //take user input
        if (checkBrackets(input)) { //evaluate input and return result
            System.out.println("The expression is valid.");
        } else {
            System.out.println("The expression is not valid.");
        }
        in.close();
    }
}