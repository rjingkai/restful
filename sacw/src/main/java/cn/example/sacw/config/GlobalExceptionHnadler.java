package cn.example.sacw.config;


import cn.example.sacw.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常拦截类，程序中抛出的所有的异常都会被拦截
 */
@RestControllerAdvice
public class GlobalExceptionHnadler {


    //程序异常处理类，相当于不直接抛出错误信息，程序做处理
    @ExceptionHandler(Exception.class)
    public ResponseResult handlerException(Exception e){
        ResponseResult result = ResponseResult.fail(500,e.getMessage());
        return result;
    }


    //自定义异常处理类
    @ExceptionHandler(BusinessException.class)
    public ResponseResult handlerBusinessException(BusinessException e){
        return ResponseResult.fail(500,e.getMessage());
    }

}
