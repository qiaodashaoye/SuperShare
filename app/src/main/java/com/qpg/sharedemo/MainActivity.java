package com.qpg.sharedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加授权
                Platform platform11 = ShareSDK.getPlatform(QQ.NAME);
//platform11.SSOSetting(true);
                platform11.authorize();
                platform11.showUser(null);//必须要加的要不然不行！这个才是授权的！
             //   tm = (TelephonyManager) MainActivity.this.getSystemService(TELEPHONY_SERVICE);
            //    imi = tm.getDeviceId();
                platform11.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
            }
        });
        showShare("11","22","http://www.adanshi.com/eggMatter/page/login/erweima/android.png","www.baidu.com");
    }
    private void showShare1() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("23123");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }


    /**
     *此方法不用指定分享的平台,会调用九宫格的平台列表界面
     * @param title 标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
     * @param text 分享文本，所有平台都需要这个字段(必填)
     * @param imageurl 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博(选填)
     * @param url 仅在微信（包括好友和朋友圈）中使用,如果不需要此字段可以直接传空或null
     */
    private void showShare(String title,String text,String imageurl,String url) {
        final OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        if(imageurl!=null && !imageurl.equals("")){
            oks.setImageUrl(imageurl);
        }

        if(url!=null && !url.equals("")){
            oks.setUrl(url);
        }
        //启动分享
        oks.show(this);
    }


}
