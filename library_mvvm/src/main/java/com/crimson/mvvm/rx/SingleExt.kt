package com.crimson.mvvm.rx

import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.util.concurrent.Callable
import java.util.concurrent.Future



fun <T> single(block: (SingleEmitter<T>) -> Unit): Single<T> = Single.create(block)

fun <T> deferredSingle(block: () -> Single<T>): Single<T> = Single.defer(block)

fun <T> singleOf(item: T) = item.toSingle()

fun <T> singleFrom(block: () -> T): Single<T> = Single.fromCallable(block)

fun <T> T.toSingle(): Single<T> = Single.just(this)

fun <T : Any> Future<T>.toSingle(): Single<T> = Single.fromFuture(this)
fun <T : Any> Callable<T>.toSingle(): Single<T> = Single.fromCallable(this)
fun <T : Any> (() -> T).toSingle(): Single<T> = Single.fromCallable(this)
fun <T> Throwable.toSingle(): Single<T> = Single.error(this)

@JvmName("toSingleFromThrowable")
fun <T> (() -> Throwable).toSingle(): Single<T> = Single.error(this)