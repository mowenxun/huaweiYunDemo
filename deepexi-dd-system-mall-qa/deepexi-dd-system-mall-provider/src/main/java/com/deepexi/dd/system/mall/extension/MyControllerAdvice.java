package com.deepexi.dd.system.mall.extension;

import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.Iterator;

/**
 * @author donh
 * @date 2018/11/6
 * 全局异常统一处理
 */
@RestControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> errorHandler(Exception ex) {
        String code = "500";
        String msg = ex.getMessage();
        return getResponse(null, code, msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<?> myErrorHandler(ApplicationException ex) {
        String code = ex.getCode();
        String msg = ex.getMessage();
        return getResponse(null, code, msg, HttpStatus.OK);
    }

    /**
     * 拦截捕获 @RequestBody 参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> validExceptionHandler(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return getResponse(null, "400", msg, HttpStatus.OK);
    }

    /**
     * 拦截捕获数据绑定时异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<?> validExceptionHandler(BindException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return getResponse(null, "400", msg, HttpStatus.BAD_REQUEST);
    }

    /**
     * 拦截捕获 @RequestParam 参数校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> validExceptionHandler(ConstraintViolationException ex) {
        Iterator<ConstraintViolation<?>> it = ex.getConstraintViolations().iterator();
        String msg = "";
        if (it.hasNext()) {
            msg = it.next().getMessageTemplate();
        }
        return getResponse(null, "400", msg, HttpStatus.BAD_REQUEST);
    }

    /**
     * 拦截捕获 @RequestBody required=true 绑定请求参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> validExceptionHandler(HttpMessageNotReadableException ex) {
        String msg = "没有请求体";
        return getResponse(null, "400", msg, HttpStatus.BAD_REQUEST);
    }

    /**
     * 拦截捕获绑定请求参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = UnexpectedTypeException.class)
    public ResponseEntity<?> validExceptionHandler(UnexpectedTypeException ex) {
        String msg = "参数类型不对";
        return getResponse(null, "400", msg, HttpStatus.BAD_REQUEST);
    }

    /**
     * 获取http返回报文
     *
     * @param payload    Payload 内容
     * @param code       Payload 返回代码
     * @param msg        Payload
     * @param httpStatus
     * @return
     */
    private ResponseEntity<Payload<Object>> getResponse(Object payload, String code, String msg, HttpStatus httpStatus) {
        ResponseEntity<Payload<Object>> response = new ResponseEntity<Payload<Object>>(new Payload<Object>(payload, code, msg), httpStatus);
        return response;
    }

}