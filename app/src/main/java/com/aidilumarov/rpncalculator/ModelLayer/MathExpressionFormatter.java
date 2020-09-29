package com.aidilumarov.rpncalculator.ModelLayer;

public class MathExpressionFormatter {

    private char openingBracket = '(';
    private char closingBracket = ')';
    private char point = '.';

    public String formatExpression(MathExpression mathExpression) {
        String formattedExpression = "";
        String expression = mathExpression.getExpression();

        for (int i = 0; i < expression.length(); i++) {

            // If char is digit, then we just append it to the formatted expression
            if (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == point) {
                formattedExpression += expression.charAt(i);
                continue;
            }

            // If we find an opening bracket,
            // first we need to check if it contains only a single number
            if (expression.charAt(i) == openingBracket) {

                // Check if brackets contain a single number
                // Find the closest closing bracket
                int nextClosingBracket = -1;
                for (int j = i; j < expression.length(); j++) {
                    if (expression.charAt(j) == closingBracket) {
                        nextClosingBracket = j;
                        break;
                    }
                }
                // If we found a closing bracket, then we need to check if there is only one number inside
                if (nextClosingBracket > i) {
                    String subStringToCheck = expression.substring(i + 1, nextClosingBracket);

                    // If it is valid number, then we append it
                    // and move the counter to the back of the closing bracket
                    if (isValidNumber(subStringToCheck)) {
                        formattedExpression += subStringToCheck;
                        i = nextClosingBracket;
                        continue;
                    }
                }
            }

            // Otherwise we just add the character to formatted expression
            // and surround it with spaces
            if (i == 0 || formattedExpression.charAt(formattedExpression.length() - 1) == ' ') {
                formattedExpression += expression.charAt(i) + " ";
            } else {
                formattedExpression += " " + expression.charAt(i) + " ";
            }
        }

        return formattedExpression.trim();
    }

    public boolean isValidNumber(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public double parseStringToDouble(String string) {
        return Double.parseDouble(string);
    }
}
