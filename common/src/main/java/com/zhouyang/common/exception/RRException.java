package com.zhouyang.common.exception;

/**
 * @author: ZY
 * @description 异常
 * @date: 2023/8/10 17:29
 */
public class RRException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RRException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
