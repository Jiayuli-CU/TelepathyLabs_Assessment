import java.util.ArrayDeque;
import java.util.Deque;

public class SolutionImplementer implements Solution {
    @Override
    public TreeNode convertOperationToTree(String operation) {
        if (operation == null || operation.isEmpty()) {
            return null;
        }
        String postfix = convertInfixToPostfix(operation);
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0; i < postfix.length(); i++) {
            TreeNode curr = new TreeNode(postfix.charAt(i));
            switch (curr.getValue()) {
                case '+':
                case '-':
                case '*':
                case '/':
                    curr.setRight(stack.pollLast());
                    curr.setLeft(stack.pollLast());
                    break;
                default:
                    break;
            }
            stack.offerLast(curr);
        }
        return stack.pollLast();
    }

    private String convertInfixToPostfix(String operation) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < operation.length(); i++) {
            char c = operation.charAt(i);
            switch (c) {
                case '+':
                case '-':
                    while (!stack.isEmpty() && stack.peekLast() != '(') {
                        builder.append(stack.pollLast());
                    }
                    stack.offerLast(c);
                    break;
                case '*':
                case '/':
                    while (!stack.isEmpty() && stack.peekLast() != '(' && stack.peekLast() != '+' && stack.peekLast() != '-') {
                        builder.append(stack.pollLast());
                    }
                    stack.offerLast(c);
                    break;
                case ')':
                    while (stack.peekLast() != '(') {
                        builder.append(stack.pollLast());
                    }
                    break;
                default:
                    stack.offerLast(c);
            }
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pollLast());
        }
        return builder.toString();
    }

    @Override
    public int calculateOperation(String operation) {
        if (operation == null || operation.isEmpty()) {
            return 0;
        }
        return calculateSubOperation(operation, new int[1]);
    }

    private int calculateSubOperation(String operation, int[] start) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char operator = '+';
        while (start[0] < operation.length()) {
            char c = operation.charAt(start[0]);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (c == '(') {
                start[0]++;
                num = calculateSubOperation(operation, start);
            }
            if (c != ' ' && !Character.isDigit(c) || start[0] == operation.length() - 1) {
                switch (operator) {
                    case '+' -> stack.offerLast(num);
                    case '-' -> stack.offerLast(-num);
                    case '*' -> stack.offerLast(stack.pollLast() * num);
                    case '/' -> stack.offerLast(stack.pollLast() / num);
                    default -> {
                    }
                }
                operator = c;
                num = 0;
            }
            if (c == ')') {
                break;
            }
            start[0]++;
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pollLast();
        }
        return ans;
    }
}
