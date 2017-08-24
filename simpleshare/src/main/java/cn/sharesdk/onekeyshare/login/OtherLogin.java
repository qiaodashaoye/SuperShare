package cn.sharesdk.onekeyshare.login;

import java.util.HashMap;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2017/8/24.
 */

public class OtherLogin {

    /**
     * QQ.NAME
     * @param platform  QQ.NAME
     */
    public static void showLogin(String platform){
        //添加授权
        Platform platform11 = ShareSDK.getPlatform(platform);
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
}
