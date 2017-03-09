package com.cheyifu.icar.tabMy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheyifu.icar.R;
import com.cheyifu.icar.base.BaseMVPFragment;
import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.global.BaseApplication;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.tabHome.activity.CarMessageActivity;
import com.cheyifu.icar.tabHome.widget.ImageViewPopuwindow;
import com.cheyifu.icar.tabMy.activity.AboutUsActivity;
import com.cheyifu.icar.tabMy.activity.SettingActivity;
import com.cheyifu.icar.tabMy.activity.WebSiteActivity;
import com.cheyifu.icar.tabMy.bean.InfoBean;
import com.cheyifu.icar.utils.SPUtils;
import com.cheyifu.icar.utils.ToastUtil;
import com.cheyifu.icar.utils.isFascClick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/14.
 */
public class MyFragment extends BaseMVPFragment<MyPrensenter> implements IMyView, View.OnClickListener {

    private View inflate;
    private LinearLayout about;
    private RelativeLayout setting;
    private LinearLayout my_iv_bg;
    //    private SimpleDraweeView my_pic;
    private TextView my_phone;
    private TextView discount;
    private TextView eb;

    private ImageViewPopuwindow imageViewPopuwindow;
    //相机拍照
    private static final int CODE_NATIVE_REQUEST = 0xa3;//本地
    private static final int CODE_CAMERA_REQUEST = 0xa1;//拍照
    private static final int CODE_RESULT_REQUEST = 0xa4;//最终剪切的结果
    private static final String IMAGE_FILE_NAME = "cheyifu_my_head.jpg";//头像文件名字
    public static final String IMAGE_UNSPECIFIED = "image/*";
    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 320;
    private static int output_Y = 320;
    private LinearLayout ll_1;
    private LinearLayout ll_2;
    private LinearLayout ll_3;
    private LinearLayout ll_4;
    private LinearLayout ll_6;
    private LinearLayout tuijian;


    @Override
    protected View onCreateViewExecute(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_my, null);
        initView();
        initListener();
        initData();
        return inflate;
    }

    private void initData() {
        //加载数据
        String str = SPUtils.getString(BaseApplication.getContext(), Constants.SP_PHONE_NUM_KEY, "");
        getPresenter().getInfo(str);
//        new ImageLoadFresco.LoadImageFrescoBuilder(BaseApplication.getContext(),my_pic, Constants.my_bitmap).setIsCircle(true).build();
    }

    private void initListener() {
        about.setOnClickListener(this);
        setting.setOnClickListener(this);
        my_iv_bg.setOnClickListener(this);
//        my_pic.setOnClickListener(this);
        ll_1.setOnClickListener(this);
        ll_2.setOnClickListener(this);
        ll_3.setOnClickListener(this);
        ll_4.setOnClickListener(this);
        ll_6.setOnClickListener(this);
        tuijian.setOnClickListener(this);
    }

    private void initView() {
        about = (LinearLayout) inflate.findViewById(R.id.about_us);
        setting = (RelativeLayout) inflate.findViewById(R.id.setting);
//        my_pic = (SimpleDraweeView) inflate.findViewById(R.id.my_pic);
        my_iv_bg = (LinearLayout) inflate.findViewById(R.id.my_iv_bg);
        my_phone = (TextView) inflate.findViewById(R.id.my_phone);
        eb = (TextView) inflate.findViewById(R.id.eb);
        discount = (TextView) inflate.findViewById(R.id.discount);
        ll_1 = (LinearLayout) inflate.findViewById(R.id.ll_1);
        ll_2 = (LinearLayout) inflate.findViewById(R.id.ll_2);
        ll_3 = (LinearLayout) inflate.findViewById(R.id.ll_3);
        ll_4 = (LinearLayout) inflate.findViewById(R.id.ll_4);
        ll_6 = (LinearLayout) inflate.findViewById(R.id.ll_6);
        tuijian = (LinearLayout) inflate.findViewById(R.id.tuijian);
    }

    @Override
    protected IUI getUI() {
        return MyFragment.this;
    }

    @Override
    protected MyPrensenter createPresenter() {
        return new MyPrensenter();
    }

    @Override
    public String getPageName() {
        return MyFragment.class.getSimpleName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_us:
                Intent intent = new Intent(getContext(), AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.setting:
                Intent setting = new Intent(getContext(), SettingActivity.class);
                getActivity().startActivity(setting);


                break;
//            case R.id.my_pic:
//                if (imageViewPopuwindow == null) {
//                    imageViewPopuwindow = new ImageViewPopuwindow(getActivity(), itemListener);
//                }
//                imageViewPopuwindow.showAtLocation(inflate.findViewById(R.id.my_center), Gravity.BOTTOM, 0, 0);
//                break;
            case R.id.my_iv_bg:
                //背景图

                break;
            case R.id.ll_1:
//                Intent intent1=new Intent(getContext(), CarSureActivity.class);
//                startActivity(intent1);
                ToastUtil.showStringToast("开发中...");
                break;
            case R.id.ll_2:
                Intent intent2 = new Intent(getContext(), CarMessageActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_3:
                Intent intent3 = new Intent(getContext(), WebSiteActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_4:
//                Intent intent1=new Intent(getContext(), CarSureActivity.class);
//                startActivity(intent1);
                ToastUtil.showStringToast("开发中...");
                break;
            case R.id.ll_6:
//                Intent intent6=new Intent(getContext(), IdearBackActivity.class);
//                startActivity(intent6);
                ToastUtil.showStringToast("开发中...");
                break;
            case R.id.tuijian:
                ToastUtil.showStringToast("开发中...");
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
//            my_pic.setImageBitmap(boundPhoto);

            File vc = new File(Environment.getExternalStorageDirectory() + "/CheYiFu");
            if (!vc.exists()) {
                vc.mkdir();
            }
            File f = new File(vc, "myHead.jpg");

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_CANCELED) {
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

    @Override
    public void responseBean(InfoBean infoBean) {
        String mobile = infoBean.getMobile();
        my_phone.setText(mobile);
        eb.setText(infoBean.getEbBalance());
        discount.setText(infoBean.getDiscount());

    }
}
