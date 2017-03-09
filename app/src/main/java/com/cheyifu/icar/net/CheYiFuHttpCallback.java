package com.cheyifu.icar.net;

public interface CheYiFuHttpCallback<T> {

    void onCompleted(T response, int httpCode, String httpMessage);

    void onFailure(Throwable throwable);
}
