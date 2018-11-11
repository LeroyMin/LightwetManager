package cn.weizhis.cms.common;

/**
 * @Auther: minliang
 * @Date: 2018/10/2 21:13
 * @Description: 结果处理类 json
 */
public class InvokeResult<T> {
    private T data;
    private String message;
    private boolean success;
    private boolean hasErrors;

    public InvokeResult() {
        this.success = true;
        this.hasErrors = false;
    }
    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public InvokeResult failure(String message){
        this.success = false;
        this.hasErrors = true;
        this.message = message;
        return this;
    }

    public InvokeResult success(){
        this.success = true;
        this.hasErrors = false;
        return this;
    }
}
