package com.xshop.common.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *  
 *
 * @program: xshop
 * @Date: 2018/8/28 14:28 
 * @Author: xuhao
 * @Description:
 **/
public class DoubleUtil {

    /**
     * double 乘法
     * @param numberList number集合
     * @param scale 精度
     * @return
     */
    public static double mul(List<Number> numberList, int scale) {
        BigDecimal bigDecimal = new BigDecimal(0);
        try {
            for (Number number : numberList){
                BigDecimal decimal = new BigDecimal(number.toString());
                bigDecimal = bigDecimal.multiply(decimal);
            }
            if(scale > 0){
                bigDecimal.setScale(scale);
            }
            return bigDecimal.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * double 乘法
     * @param numberList number集合
     * @return
     */
    public static double mul(List<Number> numberList) {
        BigDecimal bigDecimal = new BigDecimal(0);
        try {
            for (Number number : numberList){
                BigDecimal decimal = new BigDecimal(number.toString());
                bigDecimal = bigDecimal.multiply(decimal);
            }
            return bigDecimal.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * double 计算式 +, -, *, /
     * @param args number集合
     * @param scale 精度
     * @return
     */
    public static double cal(List<String> args, int scale) {
        BigDecimal bigDecimal = new BigDecimal(args.get(0));
        try {
            for(int i = 1; i < args.size(); i = i+2 ){
                String str = args.get(i);
                BigDecimal decimal = new BigDecimal(args.get(i+1));
                if(Objects.equals(str,"+")){
                    bigDecimal = bigDecimal.add(decimal);
                }else if(Objects.equals(str,"-")){
                    bigDecimal = bigDecimal.subtract(decimal);
                }else if(Objects.equals(str,"*")){
                    bigDecimal = bigDecimal.multiply(decimal);
                }else if(Objects.equals(str,"/")){
                    bigDecimal = bigDecimal.divide(decimal);
                }
            }
            if(scale > 0){
                bigDecimal.setScale(scale);
            }
            return bigDecimal.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * double 除法
     * @param d1  被除数
     * @param d2  除数
     * @param scale  精度
     * @param roundingMode 取值规则
     * @return
     */
    public static double div(double d1, double d2, int scale, int roundingMode) {
        // 当然在此之前，你要判断分母是否为0，
        // 为0你可以根据实际需求做相应的处理
        if(d2 == 0 ){
            return -1;
        }

        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));

        try {
            return bd1.divide(bd2, scale, roundingMode).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    /**  除法
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,2);
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
