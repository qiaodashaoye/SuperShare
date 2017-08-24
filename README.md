# SimpleShare
集成第三方登录和分享功能
# 1、集成步骤

## 第一步：首先导入依赖
 ```java
  allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
  	}	
```
> compile 'com.github.qiaodashaoye:SimpleShare:1.0.1'

## 第二步：
 - 在main文件夹下新建assets文件夹，并把从ShareSDK官网下载的SDK资源包文件夹下的ShareSDK.xml文件放到里面，
里面对应的是各个平台的Key信息，要把自己从个平台申请的Key换上（切记要换成自己的,不然调不起登录或分享）,
为了提高扩展性库中没有添加任何平台分享或登录所需要的jar包，所以可以根据自己需求添加。jar包必须添加，不添加
不会显示分享图标，例如，添加QQ分享功能，必须在libs文件夹下添加ShareSDK-QQ-3.0.1.jar。ShareSDK给了默认的各平台图标，
可以字节拷到drawable下，也可放自己的。
## 第三步：
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

# 2、 使用方法
 - 登录调用  参数是授权平台的名字，下面以QQ为例
 ```java
 OtherLogin.showLogin(QQ.NAME);
 ```
  - 分享调用
  ```java
   /**
       *此方法不用指定分享的平台,会调用九宫格的平台列表界面
       * @param title 标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
       * @param text 分享文本，所有平台都需要这个字段(必填)
       * @param imageurl 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博(选填)
       * @param url 仅在微信（包括好友和朋友圈）中使用,如果不需要此字段可以直接传空或null
       */
  OthreShare.showShare(context,title,ext,imageurl,url);
  ```