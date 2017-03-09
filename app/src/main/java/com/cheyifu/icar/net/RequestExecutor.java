package com.cheyifu.icar.net;


import com.cheyifu.icar.utils.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class RequestExecutor<T> {

    private Call<T> mCall;

    private CheYiFuHttpCallback<T> mCallback;

    public RequestExecutor(Call<T> call, CheYiFuHttpCallback<T> callback) {
        this.mCall = call;
        this.mCallback = callback;
    }

    /**
     * 执行
     * @return this
     */
    public final RequestExecutor<T> call(){
        mCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                mCallback.onCompleted(response.body(),response.code(),response.message());

                Logger.i("response.body()", "" + response.body());
                Logger.i("response.code()",""+response.code());
                Logger.i("response.msg()",""+response.message());
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Logger.i("onFailure",""+t.toString());
                mCallback.onFailure(t);
            }
        });

        return RequestExecutor.this;
    }

    public final boolean isExecuted() {
        return mCall.isExecuted();
    }

    public final void cancel() {
        mCall.cancel();
    }

    public final boolean isCanceled() {
        return mCall.isCanceled();
    }
}
