package a195059.calculator;

import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;

/**
 * Created by socha on 24.03.2017.
 */

public class StringUtils {

    public static String insertDotsZeros(String equation) {
        String[] numbers = getNumbers(equation);
        String operators = getOperators(equation);
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
            String[] numbers = getNumbers(equation);
            String operators = getOperators(equation);
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

    public static String[] getNumbers(String equation) {
        return equation.split("[-/+\\*^%]");
    }

    public static String getOperators(String equation) {
        return equation.replaceAll("[0123456789.]", "");
    }

    public static int countSubstring(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){
            lastIndex = str.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }

    public static String deleteCharAt(String str, int i) {
        return str.substring(0, i) + str.substring(i+1);
    }

    public static String joinEquation(String [] numbers, String operators ) {
        Log.d("numbers to join:", Arrays.asList(numbers).toString());
        String equation = "";
        for (int i = 0; i < numbers.length - 1; i++) {
            equation += numbers[i];
            equation += operators.charAt(i);
        }
        equation += numbers[numbers.length - 1];
        Log.d("equation after join:", equation);
        return equation;
    }
}
