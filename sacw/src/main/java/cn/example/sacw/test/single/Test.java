package cn.example.sacw.test.single;

/**
 * @author Administrator
 */
public class Test {

    public static void main(String[] args) {

        /**
         * 这两次其实是同一个实例，输出的hashcode值都是一样的
         */
        System.out.println(Single.getInstance().hashCode());
        System.out.println(Single.getInstance().hashCode());
    }
}
