package com.crimson.mvvm.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.crimson.mvvm.ext.exhaustive


/**
 * DBResult 扩展
 */
fun <T> DBResult<T>.handle(queryingDB: () -> Unit,
                           emptyDB: () -> Unit,
                           dbError: (throwable: Throwable) -> Unit = { _ -> },
                           success: T.() -> Unit) {
    when (this) {
        is DBResult.Success -> {
            success.invoke(value)
        }
        DBResult.Querying -> {
            queryingDB()
        }
        DBResult.EmptyDB -> {
            emptyDB()
        }
        is DBResult.DBError -> {
            dbError(throwable)
        }
    }.exhaustive
}


fun <T> MutableLiveData<DBResult<T>>.querying() {
    value = DBResult.Querying
}

fun <T> MutableLiveData<DBResult<T>>.queryingPost() {
    postValue(DBResult.Querying)
}

fun <T> MutableLiveData<DBResult<T>>.emptyData() {
    value = DBResult.EmptyDB
}

fun <T> MutableLiveData<DBResult<T>>.emptyDataPost() {
    postValue(DBResult.EmptyDB)
}


fun <T> MutableLiveData<DBResult<T>>.subscribe(queryModel: T?, includeEmptyData: Boolean = false) {
    if (includeEmptyData) {
        if (queryModel == null) {
            value = DBResult.EmptyDB
        } else {
            value = DBResult.Success(queryModel)
        }
    } else {
        queryModel?.apply {
            value = DBResult.Success(this)
        }
    }
}


fun <T> MutableLiveData<DBResult<T>>.subscribePost(queryModel: T?, includeEmptyData: Boolean = false) {
    if (includeEmptyData) {
        if (queryModel == null) {
            postValue(DBResult.EmptyDB)
        } else {
            postValue(DBResult.Success(queryModel))
        }
    } else {
        queryModel?.apply {
            postValue(DBResult.Success(this))
        }
    }
}


fun <T> MutableLiveData<DBResult<T>>.subscribeList(queryModel: T?, includeEmptyData: Boolean = false) {
    if (includeEmptyData) {
        if (queryModel == null) {
            value = DBResult.EmptyDB
        } else {
            if (this is List<*>) {
                val list = this as List<*>
                if (list.isNullOrEmpty()) {
                    value = DBResult.EmptyDB
                } else {
                    value = DBResult.Success(queryModel)
                }
            } else {
                value = DBResult.Success(queryModel)
            }
        }
    } else {
        queryModel?.apply {
            value = DBResult.Success(this)
        }
    }
}


fun <T> MutableLiveData<DBResult<T>>.subscribeListPost(queryModel: T?, includeEmptyData: Boolean = false) {
    if (includeEmptyData) {
        if (queryModel == null) {
            postValue(DBResult.EmptyDB)
        } else {
            if (this is List<*>) {
                val list = this as List<*>
                if (list.isNullOrEmpty()) {
                    postValue(DBResult.EmptyDB)
                } else {
                    postValue(DBResult.Success(queryModel))
                }
            } else {
                postValue(DBResult.Success(queryModel))
            }
        }
    } else {
        queryModel?.apply {
            postValue(DBResult.Success(this))
        }
    }
}

fun <T> MutableLiveData<DBResult<T>>.callError(throwable: Throwable) {
    value = DBResult.DBError(throwable)
}

fun <T> MutableLiveData<DBResult<T>>.callErrorPost(throwable: Throwable) {
    postValue(DBResult.DBError(throwable))
}


fun <T> MutableLiveData<DBResult<T>>.success(model: T) {
    value = DBResult.Success(model)
}

fun <T> MutableLiveData<DBResult<T>>.successPost(model: T) {
    postValue(DBResult.Success(model))
}

fun <T> MutableLiveData<DBResult<T>>.onSuccess(action: (T) -> Unit) {
    value?.let {
        when (it) {
            is DBResult.Success -> {
                action(it.value)
            }
            else -> {
            }
        }
    }
}

fun <T> LiveData<DBResult<T>>.onSuccess(action: (model: T) -> Unit = { _ -> }) {
    value?.let {
        when (it) {
            is DBResult.Success -> {
                action(it.value)
            }
            else -> {
            }
        }
    }
}

val <T> MutableLiveData<DBResult<T>>.getSuccess: T?
    get() {
        return value?.let {
            when (it) {
                is DBResult.Success -> {
                    it.value
                }
                else -> {
                    null
                }
            }
        }
    }

val <T> LiveData<DBResult<T>>.getSuccess: T?
    get() {
        return value?.let {
            when (it) {
                is DBResult.Success -> {
                    it.value
                }
                else -> {
                    null
                }
            }
        }
    }