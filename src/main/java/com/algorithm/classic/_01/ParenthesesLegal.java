package com.algorithm.classic._01;

public class ParenthesesLegal {
    public static boolean parenthesesLegal(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        char[] parentheses = s.toCharArray();
        int count = 0;
        for (char parenthesis : parentheses) {
            count = parenthesis == '(' ? count + 1 : count - 1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }


    public static int minParenthesesLegal(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] parentheses = s.toCharArray();
        int count = 0;
        int need = 0;
        for (char parenthesis : parentheses) {
            count = parenthesis == '(' ? count + 1 : count - 1;
            if (count < 0) {
                need++;
                count = 0;
            }
        }
        return need + count;
    }


    public static void main(String[] args) {
        System.out.println(parenthesesLegal("((()))"));
        System.out.println(parenthesesLegal("((("));
        System.out.println(parenthesesLegal(")()("));
    }

}
