package a195059.calculator;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.pow;

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

    public static String convertSpecialOperator(String equation, String operator) {
        ArrayList<String> numbers = new ArrayList<String>(Arrays.asList(getNumbers(equation)));
        String operators = getOperators(equation);
        Log.d("numbers before:", numbers.toString());
        Log.d("operators before:", operators.toString());
        if (equation.contains(operator)) {
            for (Integer j = 0; j < operators.length(); j++) {
                if (operators.charAt(j) == operator.charAt(0)) {
                    Log.d("Found ^ at:", j.toString());
                    String first = numbers.get(j);
                    String second = numbers.get(j + 1);
                    Double result = pow(Double.parseDouble(first), Double.parseDouble(second));
                    operators = StringUtils.deleteCharAt(operators, j);
                    numbers.remove(j + 1);
                    numbers.set(j, result.toString());
                    Log.d("numbers after:", numbers.toString());
                    Log.d("operators after:", operators.toString());
                    j--;
                }
            }
        }
        return joinEquation(numbers.toArray(new String[numbers.size()]), operators);
    }

    public static boolean checkIfOpCorrect(String equation) {
        String lastChar = equation.substring(equation.length() - 1);
        if (lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("/") || lastChar.equals("*") || lastChar.equals("^") || lastChar.equals("%")) return false;
        else if(equation.contains("∞") || equation.contains("NaN") || equation.contains("Infinity")) return false;
        else return true;
    }

    public static String getRotation(Context context){
        final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_0:
                return "portrait";
            case Surface.ROTATION_90:
                return "landscape";
            case Surface.ROTATION_180:
                return "reverse portrait";
            default:
                return "reverse landscape";
        }
    }
}
