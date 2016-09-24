package com.jkxy.calculator;
import java.math.BigDecimal;
/**
 * Created by X on 2016/3/4.
 */
public enum Counts {
    PLUS, SUB, MUL, DIV;
    public String Values(String num1, String num2) {
        BigDecimal number1 = new BigDecimal(num1);
        BigDecimal number2 = new BigDecimal(num2);
        BigDecimal number = BigDecimal.valueOf(0);
        switch (this) {
            case PLUS:
                number = number1.add(number2);
                break;
            case SUB:
                number = number1.subtract(number2);
                break;
            case MUL:
                number = number1.multiply(number2);
                break;
            case DIV:
                number = number1.divide(number2,2,BigDecimal.ROUND_UP);//保留两位小数
                break;
            default:
                break;

        }

        return number.toPlainString();//数值直接显示，不以科学计数法显示
    }
}
