import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {
    public TreeNode convertOperationToTree(String operation) {
        if (operation == null || operation.isEmpty()) {
            return null;
        }
        // todo
        return null;
    }

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
