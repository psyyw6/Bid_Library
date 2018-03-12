package atos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DuplicateKeyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;
    private String content_title;

    public DuplicateKeyException(String content_title, String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.content_title = content_title;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getContentTitle() {
        return content_title;
    }

    public void setContentTitle(String content_title) {
        this.content_title = content_title;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }



}