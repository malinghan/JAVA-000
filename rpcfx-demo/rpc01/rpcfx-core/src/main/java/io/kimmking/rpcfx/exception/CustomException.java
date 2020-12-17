package io.kimmking.rpcfx.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : linghan.ma
 * @Package io.kimmking.rpcfx.exception
 * @Description:
 * @date Date : 2020年12月18日 4:24 AM
 **/
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {
    
    private String message;
    
    public CustomException() {
        super();
    }
    
    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
