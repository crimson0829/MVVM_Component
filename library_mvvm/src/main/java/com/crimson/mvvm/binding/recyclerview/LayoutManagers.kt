package com.crimson.mvvm.binding.recyclerview

import androidx.annotation.IntDef
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * A collection of factories to create RecyclerView LayoutManagers so that you can easily set them
 * in your layout.
 */
object LayoutManagers {
    /**
     * A [LinearLayoutManager].
     */
    @JvmStatic
    fun linear(): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.LayoutManager {
                return LinearLayoutManager(recyclerView.context)
            }
        }
    }

    /**
     * A [LinearLayoutManager] with the given orientation and reverseLayout.
     */
    @JvmStatic
    fun linear(@Orientation orientation: Int, reverseLayout: Boolean): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.LayoutManager {
                return LinearLayoutManager(recyclerView.context, orientation, reverseLayout)
            }
        }
    }

    /**
     * A [GridLayoutManager] with the given spanCount.
     */
    @JvmStatic
    fun grid(spanCount: Int): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.LayoutManager {
                return GridLayoutManager(recyclerView.context, spanCount)
            }
        }
    }

    /**
     * A [GridLayoutManager] with the given spanCount, orientation and reverseLayout.
     */
    @JvmStatic
    fun grid(
        spanCount: Int, @Orientation orientation: Int,
        reverseLayout: Boolean
    ): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.LayoutManager {
                return GridLayoutManager(
                    recyclerView.context,
                    spanCount,
                    orientation,
                    reverseLayout
                )
            }
        }
    }

    /**
     * A [StaggeredGridLayoutManager] with the given spanCount and orientation.
     */
    @JvmStatic
    fun staggeredGrid(spanCount: Int, @Orientation orientation: Int): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): RecyclerView.LayoutManager {
                return StaggeredGridLayoutManager(spanCount, orientation)
            }
        }
    }

    interface LayoutManagerFactory {
        fun create(recyclerView: RecyclerView): RecyclerView.LayoutManager
    }

    @IntDef(LinearLayoutManager.HORIZONTAL, LinearLayoutManager.VERTICAL)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class Orientation
}