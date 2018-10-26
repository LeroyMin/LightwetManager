package cn.weizhis.cms.exception;

/**
 * @Auther: minliang
 * @Date: 2018/10/2 15:01
 * @Description: 自定义异常
 */
public class BizzException extends RuntimeException {
    public BizzException(){
        super();
    }

    public BizzException(String message) {
        super(message);
    }

    public BizzException(String message, Throwable cause) {
        super(message, cause);
    }
}
