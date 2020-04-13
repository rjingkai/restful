package cn.example.sacw.config;


import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class BusinessException extends RuntimeException {


    private String message;

    private Throwable throwable;

    public BusinessException (String message){
        this(message,null);
    }

    public BusinessException (String message,Throwable throwable){
        super(message,throwable);
    }
}
