package cn.weizhis.cms.common;

/**
 * @Auther: minliang
 * @Date: 2018/10/2 21:13
 * @Description: 结果处理类 json
 */
public class InvokeResult<T> {
    private T data;
    private String message;
    private boolean sucess;
    private boolean hasErrors;

    public InvokeResult() {
        this.sucess = true;
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

    public boolean isSucess() {
        return sucess;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public InvokeResult failure(String message){
        this.sucess = false;
        this.hasErrors = true;
        this.message = message;
        return this;
    }

    public InvokeResult sucess(){
        this.sucess = true;
        this.hasErrors = false;
        return this;
    }
}
