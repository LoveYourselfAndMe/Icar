package com.cheyifu.icar.base;

import java.io.Serializable;

/**
 * Created by penny on 2016/9/14 0014.
 * 所有javabean必须实现
 */
public class BaseBean implements Serializable{
    private int result;
    private String strError;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getStrError() {
        return strError;
    }

    public void setStrError(String strError) {
        this.strError = strError;
    }
}
