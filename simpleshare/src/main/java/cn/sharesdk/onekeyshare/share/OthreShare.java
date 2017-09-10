package cn.sharesdk.onekeyshare.share;

import android.content.Context;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/8/24.
 */

public class OthreShare {

    /**
     *此方法不用指定分享的平台,会调用九宫格的平台列表界面
     * @param title 标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
     * @param text 分享文本，所有平台都需要这个字段(必填)
     * @param imageurl 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博(选填)
     * @param url 仅在微信（包括好友和朋友圈）中使用,如果不需要此字段可以直接传空或null
     */
    public static void showShare(Context context,String title, String text, String imageurl, String url) {
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
            // titleUrl是标题的网络链接，QQ和QQ空间等使用
            oks.setTitleUrl(url);
        }
        //启动分享
        oks.show(context);
    }

}
