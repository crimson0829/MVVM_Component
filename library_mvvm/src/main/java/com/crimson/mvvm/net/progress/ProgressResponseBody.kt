package com.crimson.mvvm.net.progress

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException


class ProgressResponseBody(
    private  val responseBody: ResponseBody,
    private val progressListener: ProgressListener?
) : ResponseBody() {

    private var bufferedSource: BufferedSource? = null

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }


    override fun source(): BufferedSource {
        val bsCopy = bufferedSource
        return bsCopy ?: source(responseBody.source()).buffer()
    }

    private fun source(source: Source): Source {
        progressListener?.onStarted()


        return object : ForwardingSource(source) {
            var totalBytesRead = 0L

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)

                totalBytesRead += if (bytesRead != -1L) bytesRead else 0

                val percent =
                    if (bytesRead == -1L) 100f
                    else
                        totalBytesRead.toFloat() / responseBody.contentLength().toFloat() * 100


                progressListener?.onUpdate(percent.toInt())

                if (percent.toInt() == 100) {
                    progressListener?.onFinished()
                }

                return bytesRead
            }
        }
    }
}