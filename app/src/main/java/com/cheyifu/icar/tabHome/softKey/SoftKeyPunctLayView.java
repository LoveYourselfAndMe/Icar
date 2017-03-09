package com.cheyifu.icar.tabHome.softKey;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.cheyifu.icar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 标点符号视图
 */
public class SoftKeyPunctLayView extends SoftKeyView {

	private int col=9;
	private int row=4;
	public SoftKeyPunctLayView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SoftKeyPunctLayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SoftKeyPunctLayView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SoftKey[] initSoftKeys() {
		// TODO Auto-generated method stub
		//提取了<@#*./>到字母布局中
		List<String> str=new ArrayList<>();
		str.add("京");
		str.add("津");
		str.add("冀");
		str.add("鲁");
		str.add("沪");
		str.add("浙");
		str.add("苏");
		str.add("鄂");
		str.add("豫");
		str.add("皖");
		str.add("湘");
		str.add("赣");
		str.add("桂");
		str.add("川");
		str.add("贵");
		str.add("云");
		str.add("渝");
		str.add("青");
		str.add("陕");
		str.add("粤");
		str.add("琼");
		str.add("甘");
		str.add("闽");
		str.add("宁");
		str.add("藏");
		str.add("晋");
		str.add("蒙");
		str.add("新");
		str.add("黑");
		str.add("吉");
		str.add("辽");

//		String mSignArray="~!<>?`-=[]\\;',$%^&()_+{}|:\"";
//		String mSignArray="~!<>?`-=[]\\;',$%^&()_+{}|:\"";
		SoftKey [] softKeys=new SoftKey[str.size()];
		for(int index=0;index<softKeys.length;index++){
			SoftKey softKey=new SoftKey();
			softKey.setText(str.get(index));
//			softKey.setText(mSignArray.charAt(index)+"");
			softKeys[index]=softKey;
		}
		return softKeys;
	}

	@Override
	public SoftKey[] measureSoftKeysPos(SoftKey[] softKeys) {
		// TODO Auto-generated method stub
		if(softKeys==null){
			return null;
		}
		for(int index=0;index<softKeys.length;index++){
			softKeys[index].setWidth(blockWidth);
			softKeys[index].setHeight(blockHeight);
			softKeys[index].setX(blockWidth / 2 + (index % (col-1)) * blockWidth);
			softKeys[index].setY(blockHeight / 2 + (index / (col-1) % row) * blockHeight);
		}
		if(delBtn==null){
			delBtn=new SoftKey();
			delBtn.setKeyType(SoftKey.KeyType.ICON);
			delBtn.setIcon(R.drawable.ic_softkey_delete);
			delBtn.setWidth(blockWidth);
			delBtn.setHeight(blockHeight);
			delBtn.setX(blockWidth / 2 + (col-1) * blockWidth);
			delBtn.setY(blockHeight*5/2);
		}
		if(confirmBtn==null){
			confirmBtn=new SoftKey();
			confirmBtn.setText("确定");
			confirmBtn.setWidth(blockWidth*2);
			confirmBtn.setHeight(blockHeight);
			confirmBtn.setX((col-1) * blockWidth);
			confirmBtn.setY(blockHeight*7/2);
		}
		softKeys[softKeys.length-1].setX(blockWidth / 2 + (col-1) * blockWidth);
		softKeys[softKeys.length-1].setY(blockHeight/2);
		softKeys[softKeys.length-2].setX(blockWidth / 2 + (col-1) * blockWidth);
		softKeys[softKeys.length-2].setY(blockHeight*3/2);
		return softKeys;
	}

	@Override
	public int measureBlockWidth(int keyBoardwidth) {
		// TODO Auto-generated method stub
		return keyBoardwidth/col;
	}

	@Override
	public int measureBlockHeight(int keyBoardHeight) {
		// TODO Auto-generated method stub
		return keyBoardHeight/row;
	}

	@Override
	public void drawSoftKeysPos(Canvas canvas, SoftKey[] softKeys) {

		//绘制水平线分割线
		for(int index=0;index<row;index++){
			canvas.drawLine(0, index*blockHeight, getWidth(), index*blockHeight, keyBorderPaint);
		}

		//绘制垂直分割线
		for(int index=0;index<col-1;index++){
			if(index!=col-2){
				canvas.drawLine((index+1)*blockWidth, 0, (index+1)*blockWidth, getHeight(), keyBorderPaint);
			}else{
				canvas.drawLine((index+1)*blockWidth, 0, (index+1)*blockWidth, getHeight()-blockHeight, keyBorderPaint);
			}
		}

		//绘制所有的标点符号
		for(int index=0;index<softKeys.length-1;index++){
			drawSoftKey(canvas, softKeys[index]);
		}

		//绘制删除按钮
		drawSoftKey(canvas, delBtn);
		//重新绘制其中一个符号的位置到指定的位置
		drawSoftKey(canvas, softKeys[softKeys.length-1]);
		//绘制确认按钮
		drawSoftKey(canvas, confirmBtn);

	}

}
