package com.basic.exception;

/**
 * 平台统一异常
 *
 * @author vains
 */
public class CloudServiceException extends RuntimeException {

    public CloudServiceException(String msg) {
        super(msg);
    }

}
