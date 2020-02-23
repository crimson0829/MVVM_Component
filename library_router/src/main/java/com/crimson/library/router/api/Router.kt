package com.crimson.library.router.api

import android.net.Uri
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author crimson
 * @date   2020-02-10
 * router顶层函数
 */

//注入
fun routerInject(obj: Any) = ARouter.getInstance().inject(obj)

//path
fun routerPath(path: String?): Postcard {
    return ARouter.getInstance().build(path)
}

//uri
fun routerUri(uri: Uri?): Postcard {
    return ARouter.getInstance().build(uri)
}

/**
 * 构建fragment
 *
 * @param fragment_path
 * @return
 */
fun routerFragment(fragment_path: String?): Fragment {
    return ARouter.getInstance().build(fragment_path).navigation() as Fragment
}