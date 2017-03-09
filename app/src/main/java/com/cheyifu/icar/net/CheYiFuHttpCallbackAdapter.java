package com.cheyifu.icar.net;

/**
 *
 * 为了方便使用，而且可以在onCompleted以及onFaluire的时候做统一的处理
 *
 * 非常建议继承这个类实现回调，方便后续统一处理类似Token失效等问题
 *
 * @author wsl
 */
public abstract class CheYiFuHttpCallbackAdapter<R> implements CheYiFuHttpCallback<R> {

    protected int httpCode;
    protected String httpMessage;

    @Override
    public final void onCompleted(R response,int httpCode, String httpMessage) {
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
        completed(response);
    }

    @Override
    public final void onFailure(Throwable throwable) {
        failure(throwable);
    }

    public abstract void completed(R response);
    public abstract void failure(Throwable throwable);
}
