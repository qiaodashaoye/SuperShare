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
import cn.sharesdk.onekeyshare.share.OthreShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView)findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OthreShare.showShare(MainActivity.this,"我是主题","我是内容","http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg","http://sharesdk.cn");

            }
        });
     //   showShare("11","22","http://www.adanshi.com/eggMatter/page/login/erweima/android.png","www.baidu.com");
    }

    private void sd(){
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("麦巴特新能源专业平台");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("一款与智能充电相关的APP");
        oks.setUrl("www.baidu.com");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //  oks.setImagePath("/Mob/logo.png");///确保SDcard下面存在此张图片
        //这里设置网络图片地址~
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // url仅在微信（包括好友和朋友圈）中使用
        //   oks.setUrl("www.baidu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("暂无评论内容~");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("www.baidu.com");

        // 启动分享GUI
        oks.show(MainActivity.this);
    }
}
