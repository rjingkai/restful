package cn.example.sacw.test.single;


/**
 * 单例模式的演示
 * 这是懒汉模式，当调用公共方法时会产生一个实例
 * @author Administrator
 */
public class Single {

    /**
     * 提供一个静态实例
     */
    public static Single single = null;

    /**
     * 私有化构造方法
     */
    private Single(){}

    /**
     * 给外界提供的获得实例的方法
     * @return 返回实例
     */
    public static Single getInstance(){
        if (single == null){
            single = new Single();
        }
        return single;
    }

}
