package com.crimson.library.router.api

/**
 * @author crimson
 * @date   2020-02-10
 *  用于组件开发中，ARouter对Activity跳转的统一路径注册
 *  在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */
class RouterActivityPath {

    /**
     * 引导
     */
    object Guide {

        private const val GUIDE = "/guide"
        //引导页
        const val PAGER_GUIDE = "$GUIDE/pager_guide"
    }

    /**
     * 登录
     */
    object Login {

        private const val LOGIN = "/login"

        const val PAGER_LOGIN = "$LOGIN/pager_login"

    }

    /**
     * 文章
     */
    object Article{

        private const val ARTCLE = "/article"

        const val PAGER_TAB="$ARTCLE/pager_tab"

        const val PAGER_LOGIN_TEST="$ARTCLE/pager_login_test"

    }


}