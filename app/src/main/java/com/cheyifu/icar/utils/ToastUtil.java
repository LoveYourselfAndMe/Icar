package com.cheyifu.icar.utils;

import android.widget.Toast;

import com.cheyifu.icar.global.BaseApplication;


public class ToastUtil {
	private static Toast toast;
	/**
	 * 连续弹的吐司
	 * @param text
	 */
	public static void showToast(int text){
		if(toast==null){
			toast = Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT);
			
		}
		toast.setText(text);
		toast.show();
	}
	public static void showStringToast(String text){
		if(toast==null){
			toast = Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();
	}
}
