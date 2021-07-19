package com.solution.power;

public class Atoi {

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.method("1.3"));
    }

    public int method(String str) {
        if(str == null || (str = str.trim()).length() <= 0){
            return 0;
        }

        // 正负号标记
        int sign = 1;
        // 转换值
        int base = 0;
        // 索引下标
        int i = 0;

        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            sign = str.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            // int的范围 -2147483647 ~ 2147483647，超出就会发生溢出
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // 计算转换值
            base = 10 * base + (str.charAt(i++) - '0');
        }

        return base * sign;
    }

}
