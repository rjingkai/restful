package cn.example.sacw.test.gcms;


/**
 * 定义工厂类
 * @author Administrator
 */
public class SenderFactory {


    public static Serder sender(String type){
        if ("mail".equals(type)){
            return new MailSender();
        }else if("phone".equals(type)){
            return  new PhoneSender();
        }
        return null;
    }
}
