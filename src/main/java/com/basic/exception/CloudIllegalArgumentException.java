package com.basic.exception;

/**
 * 请求 Cloud 时参数异常
 *
 * @author vains
 */
public class CloudIllegalArgumentException extends CloudServiceException {

    public CloudIllegalArgumentException(String msg) {
        super(msg);
    }

}
