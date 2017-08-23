# SimpleShare
集成第三方登录和分享功能
## 使用

##第一步：首先导入依赖

##第二步：
 - 在main文件夹下新建assets文件夹，并把从ShareSDK官网下载的SDK资源包文件夹下的ShareSDK.xml文件放到里面，
里面对应的是各个平台的Key信息，要把自己从个平台申请的Key换上（切记要换成自己的,不然调不起登录或分享）,
为了提高扩展性库中没有添加任何平台分享或登录所需要的jar包，所以可以根据自己需求添加。jar包必须添加，不添加
不会显示分享图标，例如，添加QQ分享功能，必须在libs文件夹下添加ShareSDK-QQ-3.0.1.jar。ShareSDK给了默认的各平台图标，
可以字节拷到drawable下，也可放自己的。
##第三步：
- 在清单文件下进行如下配置（直接复制下面代码即可），
<activity
     android:name="com.mob.tools.MobUIShell"
     android:theme="@android:style/Theme.Translucent.NoTitleBar"
     android:configChanges="keyboardHidden|orientation|screenSize"
     android:windowSoftInputMode="stateHidden|adjustResize" >

     <intent-filter>
         <data android:scheme="tencent100371282" />
         <action android:name="android.intent.action.VIEW" />
         <category android:name="android.intent.category.BROWSABLE" />
         <category android:name="android.intent.category.DEFAULT" />
     </intent-filter>

    <!-- 调用新浪原生SDK，需要注册的回调activity -->
    <intent-filter>
        <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>

    <!--集成line客户端登录授权，需要添如下格式的过滤器-->
    <intent-filter>	
	<data android:scheme="line.1477692153" />
	<action android:name="android.intent.action.VIEW"/>
	<category android:name="android.intent.category.BROWSABLE" />
	<category android:name="android.intent.category.DEFAULT" />
    </intent-filter>

 </activity>
 
 - 如果您的项目集微信或者微信朋友圈，请查看AndroidManifest.xml配置文件里的package路径,需要在package目录下创建wxapi目录再放置WXEntryActivity。
 没有此activity在微信分享后回调会检查是否有此activity,没有将会报错。
<activity     
    android:name=".wxapi.WXEntryActivity"
    android:theme="@android:style/Theme.Translucent.NoTitleBar"     
    android:configChanges="keyboardHidden|orientation|screenSize"     
    android:exported="true"     
    android:screenOrientation="portrait" />

- 而如果您的项目集易信的两个平台，请查看AndroidManifest.xml配置文件里的package路径需要在package目录下创建yxapi目录在放置回调Activity：
<activity     
    android:name=".yxapi.YXEntryActivity"     
    android:theme="@android:style/Theme.Translucent.NoTitleBar"
    android:configChanges="keyboardHidden|orientation|screenSize" 
    android:exported="true"     
    android:screenOrientation="portrait" />
-而如果您的项目集支付宝的两个平台，请查看AndroidManifest.xml配置文件里的package路径需要在package目录下创建apshare目录在放置回调Activity：
<!-- 支付宝分享回调 -->
<activity
    android:name=".apshare.ShareEntryActivity"
    android:theme="@android:style/Theme.Translucent.NoTitleBar"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:exported="true"/>
    -而如果您的项目集钉钉的平台，请查看AndroidManifest.xml配置文件里的package路径需要在package目录下创建ddshare目录在放置回调Activity：
    <!-钉钉分享回调-->
    <activity
       android:name=".ddshare.DDShareActivity"
        android:launchMode="singleInstance"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.VIEW"></action>
            <category android:name="android.intent.category.DEFAULT"></category>
        </intent-filter>
    </activity>

-这四个类的路径是需要根据您项目的包名来确定，如果路径错误，您将收不到操作回调，因此ShareSDK也无法给予您操作回调。为了避免出错，请使用相对路径的方式，直接复制上面的代码到您的AndroidManifest.xml中即可。

##第四步：
``` java
private void showShare() {
      OnekeyShare oks = new OnekeyShare();
      //关闭sso授权
      oks.disableSSOWhenAuthorize(); 
 
     // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
      //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
      // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
      oks.setTitle(getString(R.string.share));
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
 
 ```


>注意：这里没有金额设置，金额的信息已经包含在预支付码prepayid了。

## 文档

### 微信支付官方文档 支付流程
https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5

### 支付宝支付官方文档 支付流程
https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.sdGXaH&treeId=204&articleId=105296&docType=1

