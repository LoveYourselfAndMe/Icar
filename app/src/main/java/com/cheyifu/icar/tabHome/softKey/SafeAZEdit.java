package com.cheyifu.icar.tabHome.softKey;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow.OnDismissListener;

public class SafeAZEdit extends EditText implements OnDismissListener,View.OnFocusChangeListener{

	private SoftKeyBoard softKeyBoard;

	public SafeAZEdit(Context context) {
		super(context);
		initSafeEdit(context);
	}

	public SafeAZEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
		initSafeEdit(context);
	}

	public SafeAZEdit(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initSafeEdit(context);
	}
	
	public void initSafeEdit(Context context){
		setOnFocusChangeListener(this);
	}

	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_UP){
			if (softKeyBoard == null) {
				softKeyBoard=new SoftKeyBoard(getContext());
				softKeyBoard.setEdit(this);
//				softKeyBoard.setViewMode(2);
				softKeyBoard.setOnDismissListener(this);
				softKeyBoard.show();
			}
		}
		return true;
	}

	@Override
	public void onDismiss() {
		softKeyBoard.recycle();
		softKeyBoard=null;
	}

	@Override
	public void onFocusChange(View view, boolean b) {
		if (b) {
//			if (softKeyBoard == null) {
//				softKeyBoard=new SoftKeyBoard(getContext());
//				softKeyBoard.setEdit(this);
//				softKeyBoard.setViewMode(3);
//				softKeyBoard.setOnDismissListener(this);
//				softKeyBoard.show();
//			}
		}


	}
}
