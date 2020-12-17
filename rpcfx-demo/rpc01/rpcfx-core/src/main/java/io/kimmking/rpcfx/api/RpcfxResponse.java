package io.kimmking.rpcfx.api;

public class RpcfxResponse {

    private Object result;

    private boolean status;

    private Exception exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public void setStatus(final boolean status) {
        this.status = status;
    }
    
    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
