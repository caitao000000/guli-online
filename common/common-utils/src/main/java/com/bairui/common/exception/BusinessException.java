package com.bairui.common.exception;

public class BusinessException  extends  RuntimeException{
    private String errorCode;
    private int errorType = -1; //  -1 参数null异常  1 参数类型异常   2 参数错误
    private String errorMsg ="";

    public String getErrorCode() {
        return errorCode;
    }

    public int getErrorType() {
        return errorType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String msg,Throwable cause){
        super(msg,cause);
    }
    public void printStackTrace(){
        printStackTrace(System.err);
    }
    public BusinessException(String errorCode,String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
