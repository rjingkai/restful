package cn.example.sacw.config;


import cn.example.sacw.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常拦截类，程序中抛出的所有的异常都会被拦截
 * @author Administrator
 */
@RestControllerAdvice
public class GlobalExceptionHnadler {


    /**
     * 捕捉全局异常的类
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handlerException(Exception e){
        ResponseResult result = ResponseResult.fail(500,e.getMessage());
        return result;
    }


    /**
     * 处理自定义异常的类
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult handlerBusinessException(BusinessException e){
        return ResponseResult.fail(500,e.getMessage());
    }

}
