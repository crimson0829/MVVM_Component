package com.crimson.library.router.api

/**
 * @author crimson
 * @date   2020-02-10
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 *
 */
class RouterFragmentPath {


    object Article {

        private const val ARTICLE = "/articlef"

        const val PAGER_AUTHOR = "$ARTICLE/pager_author"

    }


}