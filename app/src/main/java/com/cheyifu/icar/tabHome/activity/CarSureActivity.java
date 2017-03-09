package com.cheyifu.icar.tabHome.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabHome.widget.ImageViewPopuwindow;
import com.cheyifu.icar.utils.ToastUtil;
import com.cheyifu.icar.utils.isFascClick;
import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPActivity;
import com.cheyifu.icar.tabHome.iView.ICarView;
import com.cheyifu.icar.tabHome.persenter.CarPresenter;
import com.cheyifu.icar.utils.AndroidUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CarSureActivity extends BaseMVPActivity<CarPresenter> implements ICarView, View.OnClickListener {
    //相机拍照
    private static final int CODE_NATIVE_REQUEST = 0xa3;//本地
    private static final int CODE_CAMERA_REQUEST = 0xa1;//拍照
    private static final int CODE_RESULT_REQUEST = 0xa4;//最终剪切的结果
    private static final String IMAGE_FILE_NAME = "cheyifu_xingshizheng.jpg";//头像文件名字
    public static final String IMAGE_UNSPECIFIED = "image/*";
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 320;
    private static int output_Y = 320;

    private RelativeLayout back;
    private RelativeLayout rl_right;
    private ImageView renzheng;
    private ImageViewPopuwindow imageViewPopuwindow;

    @Override
    protected void onCreateExecute(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_sure);
        TextView textView = (TextView) findViewById(R.id.tv_titlebar);
        textView.setText("车辆认证");
        back = (RelativeLayout) findViewById(R.id.iv_back);
        rl_right = (RelativeLayout) findViewById(R.id.rl_right);
        renzheng = (ImageView) findViewById(R.id.iv_xingshizheng);

        initListener();


    }

    private void initListener() {
        back.setOnClickListener(this);
        rl_right.setOnClickListener(this);
        renzheng.setOnClickListener(this);
    }

    @Override
    protected CarPresenter createPresenter() {
        return new CarPresenter();
    }

    @Override
    protected IUI getUI() {
        return CarSureActivity.this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                AndroidUtils.hideSoftInput(this, null);
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                break;
            case R.id.rl_right:
                ToastUtil.showStringToast("立即验证");
                break;
            case R.id.iv_xingshizheng:
                //拍照
                imageViewPopuwindow = new ImageViewPopuwindow(this, itemListener);
                imageViewPopuwindow.showAtLocation(this.findViewById(R.id.car_sure_renzheng), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
        }

    }

    private View.OnClickListener itemListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    //拍照
                    choseHeadImageFromCameraCapture();
                    imageViewPopuwindow.dismiss();
                    break;
                case R.id.btn_pick_photo:
                    //从相册选择
                    choseHeadImageFromGallery();
                    imageViewPopuwindow.dismiss();
                    break;
            }

        }
    };

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
//        intentFromGallery.setType("image/*");//选择图片
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        startActivityForResult(intentFromGallery, CODE_NATIVE_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
// 判断存储卡是否可用，存储照片文件
        if (isFascClick.hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {
        //需要解决图片的旋转问题

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
//把裁剪的数据填入里面
// 设置裁剪
        intent.putExtra("crop", "true");
// aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
// outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);
        this.startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap boundPhoto = extras.getParcelable("data");
//            bitmap = BitmapUtil.compressImage(boundPhoto);
            renzheng.setImageBitmap(boundPhoto);

            File vc = new File(Environment.getExternalStorageDirectory() + "/CheYiFu");
            if (!vc.exists()) {
                vc.mkdir();
            }
            File f = new File(vc, "xingshizheng.jpg");

            FileOutputStream out = null;
            try {//打开输出流 将图片数据填入文件中
                out = new FileOutputStream(f);
                boundPhoto.compress(Bitmap.CompressFormat.PNG, 100, out);
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //上传服务器



        } else {
            ToastUtil.showStringToast("没有拿到图片片的数据路径");
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_CANCELED) {
            // 用户没有进行有效的设置操作，返回
            return;
        }
        switch (requestCode) {
            case CODE_NATIVE_REQUEST://如果是来自本地的
                if (data != null) {
                    Uri uri1 = data.getData();
                    cropRawPhoto(uri1);//直接裁剪图片
                }
                break;
            case CODE_CAMERA_REQUEST:

                if (isFascClick.hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                } else {
                    ToastUtil.showStringToast("没有SD卡");
                }
                break;
            case CODE_RESULT_REQUEST:
                if (data != null) {
                    setImageToHeadView(data);//设置图片框
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
