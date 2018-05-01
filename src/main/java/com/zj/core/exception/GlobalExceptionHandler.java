package com.zj.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(value = JsonException.class)
//    @ResponseBody
//    public ResultBean<String> jsonErrorHandler(HttpServletRequest req, JsonException e) throws Exception {
//        ResultBean<String> r = new ResultBean<>();
//        r.setMessage(e.getMessage());
//        r.setCode(ResultBean.ERROR);
//        r.setUrl(req.getRequestURL().toString());
//        return r;
//    }
//	
//	@ExceptionHandler(value = FileSizeLimitExceededException.class)
//    @ResponseBody
//	public ResultBean fileSizeLimitExceededExceptionHandler(HttpServletRequest req, FileSizeLimitExceededException e) throws Exception{
//		ResultBean<String> r = new ResultBean<>();
//        r.setMessage(e.getMessage());
//        r.setCode(ResultBean.ERROR);
//        r.setUrl(req.getRequestURL().toString());
//        return r;
//	}
}
