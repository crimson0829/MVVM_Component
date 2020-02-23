# MVVM_Component

## 介绍

以[MVVM_Module](https://github.com/crimson0829/MVVM_Module)为基础，通过[ARouter](https://github.com/alibaba/ARouter)实现组件化的框架

## 特点

module可作为功能独立组件，也可作为独立App运行，高内聚，低耦合。


## 使用

1.1 组件的初始化:如果组件需初始化，可实现IModule接口并将完整路径添加到ModuleConfig下引用

```
//实现
class LoginModule : IModule {

    override fun initKoinModule() {

        injectKoinModules(loginModule)

    }

    override fun initModule(app: Application) {
    }
}

//在ModuleConfig中引用:

//登录组件
private const val LOGIN_MODULE = "com.crimson.module.login.LoginModule"

//组件集合
 private var modules = arrayListOf(LOGIN_MODULE)


```

1.2 组件的拦截：该方式通过AOP的形式实现，可实现IInterceptor接口并在@Router注解中申明就可起到拦截器的效果

```
//实现：
@Interceptor(priority = 1, name = "登录拦截器")
class LoginInterceptor : IInterceptor {
    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        if (postcard.extra == RouterIntercepter.LOGIN_INTERCEPTOR) {

            //这里还需增加个登录状态判断
            //if (未登录)

            callback.onInterrupt(null)
            //跳转登录页
            routerPath(RouterActivityPath.Login.PAGER_LOGIN)
                .withString("login","interceptor")
                .navigation()
        } else {
            callback.onContinue(postcard)
        }
    }

    override fun init(context: Context) {}
}

//申明：
@Route(path = ...,extras = RouterInterceptor.LOGIN_INTERCEPTOR)

```

1.3 组件的跳转：

1.3.1 Activity的跳转：在RouterActivityPath中申明并引用


```
  
  //申明链接
    object Login {

        private const val LOGIN = "/login"

        const val PAGER_LOGIN = "$LOGIN/pager_login"

    }
    
    //申明跳转页
    @Route(path = RouterActivityPath.Login.PAGER_LOGIN)
    class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {}
    
    //跳转
    routerPath(RouterActivityPath.Login.PAGER_LOGIN)
             //传递参数
             .withString("login", "login")
             .navigation()

```

1.3.2 Fragment初始化：在RouterFragmentPath中申明

```

    object Article {

        private const val ARTICLE = "/articlef"

        const val PAGER_AUTHOR = "$ARTICLE/pager_author"

    }
    
    //初始化
    val authorFragment =
               routerFragment(RouterFragmentPath.Article.PAGER_AUTHOR) as AuthorFragment

```

1.4 参数初始化与注入

```

//初始化
  @Autowired(name = "login")
  @JvmField
  var param: String? = ""
  
  //注入，可在onCreate()中注入，越早越好
   routerInject(this)
    

```

2 组件为独立App：module组件的build.gradle中需引入module.build.gradle

```
apply from: "../module.build.gradle"

android {
    defaultConfig {
        //如果是独立模块，则使用当前组件的包名
        if (isModule.toBoolean()) {
            applicationId "com.crimson.login"

        }

        multiDexEnabled true

    }


    //资源统一引用，防止资源重命名
    resourcePrefix "login_"

}

```

2.1 将gradle.properties中的isModule设为true

```
isModule=true

```


2.2 新建alone文件夹，将 AndroidManifest.xml 放入其中 作为独立app的清单文件，并申明DebugApplication为启动App

```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.crimson.module.login">

    <application
        android:name="com.crimson.library.router.debug.DebugApplication"
  >

        <activity
            android:name=".view.LoginActivity"
            android:screenOrientation="portrait" >

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>
</manifest>

```


## License

```
Copyright 2020 crimson0829
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


