package cn.example.sacw.test.gcms;

public class test {

    public static void main(String[] args) {
        Serder s = SenderFactory.sender("mail");
        s.sender();
    }
}
