package com.crimson.module.login.view


import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.crimson.module.login.model.LoginModel
import com.crimson.mvvm.base.BaseViewModel
import com.crimson.mvvm.binding.consumer.bindConsumer
import com.crimson.mvvm.coroutines.callRemoteLiveDataAsync
import com.crimson.mvvm.coroutines.ioCoroutineGlobal
import com.crimson.mvvm.coroutines.onMain
import com.crimson.mvvm.ext.tryCatch
import com.crimson.mvvm.ext.view.toast
import com.crimson.mvvm.net.errorResponseCode
import com.crimson.mvvm.net.handle
import org.koin.core.inject


/**
 * View Model
 */
class LoginViewModel : BaseViewModel() {

    val model: LoginModel by inject()

    val userName = ObservableField<String>("")

    val userPassword = ObservableField<String>("")


    val userNameChanged = bindConsumer<String> {

        userName.set(this)
    }

    val userPasswordChanged = bindConsumer<String> {

        userPassword.set(this)
    }


    val onClickBtn = bindConsumer<Unit> {

        login()

    }

    fun login() {
        if (userName.get().isNullOrEmpty()) {
            toast("username empty!")
            return
        }

        if (userPassword.get().isNullOrEmpty()) {
            toast("password empty!")
            return
        }

        callRemoteLiveDataAsync {
            model.login(userName.get() ?: "", userPassword.get() ?: "")
        }
            //观察livedata
            ?.observe(lifecycleOwner, Observer {

                //LiveData.handle() 扩展
                it.handle({
                    //when loading
                    onDataLoading()

                }, {
                    //result empty
                   onDataResult()

                }, {
                    //result error 可做错误处理
                    toast("网络错误")
                    onDataResult()

                }, { _, responseCode ->

                    //result remote error,可根据responseCode做错误提示
                    errorResponseCode(responseCode)
                    onDataResult()

                }, {
                    //result success
                    onDataResult()
                    ioCoroutineGlobal {

                        tryCatch {

                            val message = string()

                            onMain {

                                toast(message)
                            }
                        }



                    }
                })
            })
    }


}
