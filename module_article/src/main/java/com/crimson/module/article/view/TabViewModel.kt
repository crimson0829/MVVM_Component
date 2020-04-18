package com.crimson.module.article.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.crimson.library.router.api.RouterActivityPath
import com.crimson.library.router.api.routerPath
import com.crimson.module.article.model.AuthorModel
import com.crimson.module.article.model.kdo.TabListEntity
import com.crimson.mvvm.base.BaseViewModel
import com.crimson.mvvm.binding.consumer.bindConsumer
import com.crimson.mvvm.binding.consumer.bindTiConsumer
import com.crimson.mvvm.coroutines.callRemoteLiveDataAsync
import com.crimson.mvvm.coroutines.ioCoroutineGlobal
import com.crimson.mvvm.ext.logw
import com.crimson.mvvm.ext.view.toast
import com.crimson.mvvm.livedata.SingleLiveData
import com.crimson.mvvm.net.errorResponseCode
import com.crimson.mvvm.net.handle
import com.crimson.mvvm.rx.bus.RxCode
import com.crimson.mvvm.rx.bus.RxDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.inject

/**
 * @author crimson
 * @date   2020-02-23
 */
/**
 * data from coroutine
 */
class TabViewModel : BaseViewModel() {


    //koin inject
    val model by inject<AuthorModel>()

     val onClickBtn = bindConsumer<Unit> {

         routerPath(RouterActivityPath.Login.PAGER_LOGIN)
             .withString("login","from tab")
             .navigation()

      }

    //live data
    val tabDataCompleteLD by inject<SingleLiveData<MutableList<String>>>()

    val fragments = arrayListOf<Fragment>()

    var errorDis: Disposable? = null

    val vp2SelectedConsumer =
        bindConsumer<Int> {

            logw("vp2page -> $this")
        }

    val vp2ScrolledConsumer =
        bindTiConsumer<Int, Float, Int> { t1, t2, t3 ->
            logw("vp2pos -> $t1 positionOffset->$t2 positionOffsetPixels -> $t3")
        }



    /**
     * run with 协程
     */
    fun getData() {

//        viewModelScope.launch {
//
//        }


        callRemoteLiveDataAsync {
            model.getData()
        }
            //观察livedata
            ?.observe(lifecycleOwner, Observer {

                //LiveData.handle() 扩展
                it.handle({
                    //when loading
                    onLoadingViewInjectToRoot()

                },{
                    //result empty
                    onLoadingViewResult()

                },{
                    //result error 可做错误处理
                    toast("网络错误")
                    onLoadingError()

                },{_,responseCode->

                    //result remote error,可根据responseCode做错误提示
                    errorResponseCode(responseCode)
                    onLoadingError()

                },{
                    //result success
                    onLoadingViewResult()
                    ioCoroutineGlobal {
                        handleData(this)
                    }
                })
            })


    }


    private fun handleData(tabData: TabListEntity) {
        val titles = arrayListOf<String>()

        tabData.data.forEach {
            titles.add(it.name)
            //也可用router初始化
            val fragment = AuthorFragment()

            fragment.arguments = Bundle().apply {
                putInt("id", it.id)
            }
            fragments.add(fragment)
        }

        tabDataCompleteLD.postValue(titles)
    }

    override fun registerRxBus() {

        errorDis = rxbus.toObservable(RxCode.POST_CODE, Integer::class.java)
            .subscribe {
                if (it.toInt() == RxCode.ERROR_LAYOUT_CLICK_CODE) {
                    getData()
                }
            }

        RxDisposable.add(errorDis)


    }

    override fun removeRxBus() {
        RxDisposable.remove(errorDis)

    }

}