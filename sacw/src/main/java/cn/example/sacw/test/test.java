package cn.example.sacw.test;

import javax.swing.text.PlainDocument;

public class test {
    public static void main(String[] args) {

        /**
         * 判断一个字符串中某个字符的出现的次数，只能使用 String 自带的方法。
         * 思路：将需要判断的字符利用replace方法替换成"",得到新的长度，用旧的长度来减去新长度就可以了
         */

        /**
         * 某个字符串
         */
        String str = "ABCBBBBQ";

        /**
         * 需要替换的字符串
         */
        String replacestr = "B";

        /**
         * 声明数量
         */
        int count = 0;

        /**
         * 替换之前字符串的长度
         */
        int oldlength = str.length();

        /**
         * 将需要替换的字符串变为空
         */
        str = str.replace(replacestr,"");

        /**
         * 用旧的字符串长度减去替换之后的长度就是某个字符串出现的次数
         */
        count = oldlength - str.length();

        System.out.println(count);

    }
}
