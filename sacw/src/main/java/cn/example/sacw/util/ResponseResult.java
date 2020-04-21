package cn.example.sacw.util;


import lombok.Getter;
import lombok.Setter;
import springfox.documentation.service.ResponseMessage;

/**
 * 统一返回数据格式的实体类
 * @author Administrator
 */
@Getter
@Setter
public class ResponseResult<T> {

    /**
     * 状态码
     */
    int code;


    /**
     * 状态码描述
     */
    String message;


    /**
     * 返回的数据
     */
    T data;

    private ResponseResult() {
        this(200,"success");
    }

    private ResponseResult(int code,String message) {
        this.code=code;
        this.message=message;
    }

    private ResponseResult(ResponseMessage responseMessage) {
        this.code= responseMessage.getCode();
        this.message= responseMessage.getMessage();
    }

    private ResponseResult(int code,String message,T data) {
        this.code=code;
        this.message=message;
        this.data=data;
    }

    private ResponseResult (ResponseMessage responseMessage, T data) {
        this.code= responseMessage.getCode();
        this.message= responseMessage.getMessage();
        this.data=data;
    }

    public static ResponseResult success() {
        return new ResponseResult();
    }

    public static <T> ResponseResult success(T data) {
        return success(200,"success",data);
    }

    public static ResponseResult success(int code,String message) {
        return success(code,message,null);
    }

    public static ResponseResult success(ResponseMessage responseMessage) {
        return success(responseMessage.getCode(),responseMessage.getMessage(),null);
    }

    public static <T> ResponseResult success(ResponseMessage responseMessage,T data) {
        return success(responseMessage.getCode(),responseMessage.getMessage(),data);
    }

    public static <T> ResponseResult success(int code,String message,T data) {
        return new ResponseResult(code,message,data);
    }

    public static ResponseResult fail() {
        return fail(500,"出错");
    }

    public static ResponseResult fail(int code,String message) {
        return fail(code,message,null);
    }

    public static ResponseResult fail(ResponseMessage responseMessage) {
        return fail(responseMessage.getCode(),responseMessage.getMessage(),null);
    }

    public static <T> ResponseResult fail(ResponseMessage responseMessage, T data) {
        return fail(responseMessage.getCode(),responseMessage.getMessage(),data);
    }

    public static <T> ResponseResult fail(int code,String message,T data) {
        return new ResponseResult(code,message,data);
    }


}


