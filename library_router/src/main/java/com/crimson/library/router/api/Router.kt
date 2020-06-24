package com.crimson.library.router.api

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable

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

/**
 * Postcard 扩展
 *
 */
@Suppress("UNCHECKED_CAST")
fun Postcard.putParams(vararg params: Pair<String, Any?>) = apply {
    for (p in params) {
        val (k, v) = p
        when (v) {
            null -> withSerializable(k, null)
            is Boolean -> withBoolean(k, v)
            is Byte -> withByte(k, v)
            is Char -> withChar(k, v)
            is Short -> withShort(k, v)
            is Int -> withInt(k, v)
            is Long -> withLong(k, v)
            is Float -> withFloat(k, v)
            is Double -> withDouble(k, v)
            is String -> withString(k, v)
            is CharSequence -> withCharSequence(k, v)
            is Parcelable -> withParcelable(k, v)
            is Serializable -> withSerializable(k, v)
            is BooleanArray -> extras.putBooleanArray(k, v)
            is ByteArray -> withByteArray(k, v)
            is CharArray -> withCharArray(k, v)
            is DoubleArray -> extras.putDoubleArray(k, v)
            is FloatArray -> withFloatArray(k, v)
            is IntArray -> extras.putIntArray(k, v)
            is LongArray -> extras.putLongArray(k, v)
            is ShortArray -> withShortArray(k, v)
            is Array<*> -> {
                when {
                    v.isArrayOf<Parcelable>() -> extras.putParcelableArray(
                        k, v as Array<out Parcelable>
                    )
                    v.isArrayOf<CharSequence>() -> extras.putCharSequenceArray(
                        k, v as Array<out CharSequence>
                    )
                    v.isArrayOf<String>() -> extras.putStringArray(k, v as Array<out String>)
                    else -> throw Exception("Unsupported bundle component (${v.javaClass})")
                }
            }
            is ArrayList<*> -> {
                when {
                    v.isArrayListOf<Parcelable>() -> {
                        withParcelableArrayList(k, v as ArrayList<out Parcelable>)
                    }
                    v.isArrayListOf<Int>() -> {
                        withIntegerArrayList(k, v as ArrayList<Int>)
                    }
                    v.isArrayListOf<String>() -> {
                        withStringArrayList(k, v as ArrayList<String>)

                    }
                    v.isArrayListOf<CharSequence>() -> {
                        withCharSequenceArrayList(k, v as ArrayList<CharSequence>?)

                    }

                }

            }
            is Bundle -> withBundle(k, v)
            else -> throw Exception("Unsupported bundle component (${v.javaClass})")
        }
    }
}


/**
 * Checks if ArrayList can contain element of type [T].
 */
@Suppress("REIFIED_TYPE_PARAMETER_NO_INLINE")
fun <reified T : Any> ArrayList<*>.isArrayListOf(): Boolean =
    T::class.java.isAssignableFrom(this::class.java.componentType!!)


fun Postcard.put(key: String?, value: Boolean) = apply {
    withBoolean(key, value)
}

fun Postcard.put(key: String?, value: Byte) = apply {
    withByte(key, value)
}

fun Postcard.put(key: String?, value: Char) = apply {
    withChar(key, value)
}

fun Postcard.put(key: String?, value: Short) = apply {
    withShort(key, value)
}

fun Postcard.put(key: String?, value: Int) = apply {
    withInt(key, value)
}

fun Postcard.put(key: String?, value: Long) = apply {
    withLong(key, value)
}


fun Postcard.put(key: String?, value: Float) = apply {
    withFloat(key, value)
}

fun Postcard.put(key: String?, value: Double) = apply {
    withDouble(key, value)
}

fun Postcard.put(key: String?, value: Parcelable?) = apply {
    withParcelable(key, value)
}


fun Postcard.put(key: String?, value: ByteArray?) = apply {
    withByteArray(key, value)
}


fun Postcard.put(key: String?, value: ShortArray?) = apply {
    withShortArray(key, value)
}

fun Postcard.put(key: String?, value: CharArray?) = apply {
    withCharArray(key, value)
}


fun Postcard.put(key: String?, value: FloatArray?) = apply {
    withFloatArray(key, value)
}


fun Postcard.put(key: String?, value: IntArray?) = apply {
    extras.putIntArray(key, value)
}


fun Postcard.put(key: String?, value: LongArray?) = apply {
    extras.putLongArray(key, value)
}


fun Postcard.put(key: String?, value: String?) = apply {
    withString(key, value)
}


fun Postcard.put(key: String?, value: Bundle?) = apply {
    withBundle(key, value)
}

fun Postcard.put(key: String?, value: Array<CharSequence?>?) = apply {
    withCharSequenceArray(key, value)
}

fun Postcard.put(key: String?, value: Array<Parcelable?>?) = apply {
    withParcelableArray(key, value)
}


fun Postcard.put(key: String?, value: ArrayList<out Parcelable?>?) = apply {
    withParcelableArrayList(key, value)
}


fun Postcard.put(key: String?, value: SparseArray<out Parcelable?>?) = apply {
    withSparseParcelableArray(key, value)
}