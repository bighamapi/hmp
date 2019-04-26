package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    public Result error(MaxUploadSizeExceededException e){
        LOG.error("上传文件过大");
        return new Result(false, StatusCode.ERROR, "上传文件过大");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result nullPointerExceptionHandler(NullPointerException ex) {
        ex.printStackTrace();
        LOG.error("空指针异常"+ex.getMessage());
        return new Result(false, StatusCode.ERROR, "执行出错");
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, "执行出错");
    }
}
