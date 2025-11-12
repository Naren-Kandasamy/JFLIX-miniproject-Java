package UTILS;

public class InputValidator {
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isPositive(int value) {
        return value > 0;
    }
}