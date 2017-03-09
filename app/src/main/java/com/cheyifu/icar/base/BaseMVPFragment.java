package com.cheyifu.icar.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * MVP Fragment的基类.
 *
 * @author penny
 */
public abstract class BaseMVPFragment<P extends IPresenter> extends BaseFragment {

    private static final String KEY_DATA = "keyData";

    protected P mPresenter;

    protected View mRootView;

    protected Bundle mData;

    protected Bundle mExtras;
    protected Bundle bundle;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bundle = savedInstanceState;
        if (savedInstanceState != null) {
            Bundle bundle = savedInstanceState.getBundle(KEY_DATA);
            if (bundle != null) {
                mData = bundle;
            }
        }
        if (mRootView == null) {
            mPresenter = createPresenter();
            getPresenter().init(getActivity(), getUI());
            mRootView = onCreateViewExecute(inflater, container, savedInstanceState);
            getPresenter().onUICreate(savedInstanceState);
        }

        return mRootView;
    }

    /**
     * onCreateViewExecute:MVPd的Fragment不应该再实现onCreateView()方法，而是应该事先onCreateViewExecute()方法. <br/>
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View onCreateViewExecute(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * getUI:得到UI.一般都是Fragment或者Activity本身 <br/>
     *
     * @return
     */
    protected abstract IUI getUI();

    /**
     * createPresenter:创建一个Presenter，子类来实现，可以通过new的方式直接new出来一个. <br/>
     *
     * @return
     */
    protected abstract P createPresenter();

    /**
     * getPresenter:子类应该通过这个方法拿到Presenter的实例，而不是通过变量拿到. <br/>
     *
     * @return
     */
    protected final P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onUIStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onUIStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onUIResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onUIPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null && mRootView.getParent() != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
        getPresenter().onUIDestory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mData != null) {
            outState.putBundle(KEY_DATA, mData);
        }
        if(getPresenter() != null){
            getPresenter().onSaveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * getData:得到传输过来的数据. <br/>
     *
     * @return
     */
    public Bundle getData() {
        return mData;
    }

    /**
     * setData:设置启动这个Fragment必须的数据. <br/>
     *
     * @param bundle
     */
    public void setData(Bundle bundle) {
        this.mData = bundle;
    }

    /**
     * getExtras:得到Extras. <br/>
     *
     * @return Extras
     */
    public Bundle getExtras() {
        return mExtras;
    }

    /**
     * setExtras:设置Extras. <br/>
     *
     * @param extras extras
     */
    public void setExtras(Bundle extras) {
        this.mExtras = extras;
    }
}
