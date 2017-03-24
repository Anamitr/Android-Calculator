package a195059.calculator;

import android.text.TextUtils;

/**
 * Created by socha on 24.03.2017.
 */

public class StringUtils {

    public static String insertDotsZeros(String equation) {
        String[] numbers = equation.split("[-/+\\*]");
        String operators = equation.replaceAll("[0123456789.]", "");
        for (int i = 0; i < numbers.length; i++) {
            if (!numbers[i].contains(".")) numbers[i] += ".0";
            //System.out.println(numbers[i]);
        }
        equation = "";
        for (int i = 0; i < numbers.length - 1; i++) {
            equation += numbers[i];
            equation += operators.charAt(i);
        }
        equation += numbers[numbers.length - 1];
        return equation;
    }

    public static String deleteLastOp(String equation) {
        if (equation.matches(".*[-/+\\*].*")) {
            String[] numbers = equation.split("[-/+\\*]");
            String operators = equation.replaceAll("[0123456789.]", "");
            for (int i = 0; i < numbers.length; i++) {
                if (!numbers[i].contains(".")) numbers[i] += ".0";
                System.out.println(numbers[i]);
            }
            equation = "";
            for (int i = 0; i < numbers.length - 1; i++) {
                equation += numbers[i];
                equation += operators.charAt(i);
            }
            equation = equation.substring(0, equation.length() - 1);
        } else equation = "0";
        return equation;
    }

    public static String getLastNumber(String equation) {
        String[] numbers = equation.split("[-/+\\*]");
        return numbers[numbers.length-1];
    }

    public static String getSafeSubstring(String s, int maxLength) {
        if (!TextUtils.isEmpty(s)) {
            if (s.length() >= maxLength) {
                return s.substring(0, maxLength);
            }
        }
        return s;
    }
}
