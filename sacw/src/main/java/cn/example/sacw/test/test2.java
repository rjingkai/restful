package cn.example.sacw.test;

import java.util.Stack;
import java.util.stream.Stream;

public class test2 {
    public static void main(String[] args) {

        /**
         * 如何反转一个字符串？不通过reserve方法。
         * 思路：利用数据结构的栈（stack），因为栈的特点就是先入后出，先把每个字符按顺序入栈，再依次出栈，可实现反转。
         */

        /**
         * 需要反转的字符串
         */
        String str = "ABCDEFG";

        /**
         * 将字符串变为char数组
         */
        char[] charArray = str.toCharArray();

        /**
         * 声明java的栈
         */
        Stack<Character> stack = new Stack<>();

        /**
         * 声明stringbuilder
         */
        StringBuilder sb = new StringBuilder();

        /**
         * 通过foreach入栈
         */
        for (char item: charArray) {
            //push()是入栈
            stack.push(item);
        }

        /**
         * 通过for循环出栈
         */
        for (int i = 0 ;i < charArray.length ; i++){
            //pop()是出栈
            sb.append(stack.pop());
        }

        System.out.println("反转前："+str);

        System.out.println("反转后："+sb.toString());

        /**
         * 这里介绍一下栈的几个方法：
         * empty()：判断stack是否为空；
         * peek()：栈顶值
         * push()：进栈
         * pop()：出栈
         */
    }
}
