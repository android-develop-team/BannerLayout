package com.android.banner

import android.view.View
import android.view.ViewGroup

/**
 * @author y
 * @create 2019/4/16
 */

val DEFAULT_IMAGE_LOADER: ImageLoaderManager<BannerInfo>
    get() = object : ImageLoaderManager<BannerInfo> {
        override fun display(container: ViewGroup, info: BannerInfo, position: Int): View {
            throw KotlinNullPointerException("ImageLoaderManager == null")
        }
    }

fun <T : BannerInfo> BannerLayout.imageLoaderManager(imageLoaderManager: () -> ImageLoaderManager<T>) = apply { setImageLoaderManager(imageLoaderManager.invoke()) }

fun <T : BannerInfo> BannerLayout.setImageLoaderManager(action: (container: ViewGroup, info: T, position: Int) -> View): BannerLayout {
    val imageManager = object : ImageLoaderManager<T> {
        override fun display(container: ViewGroup, info: T, position: Int): View = action(container, info, position)
    }
    setImageLoaderManager(imageManager)
    return this
}

fun <T : BannerInfo> BannerLayout.addOnItemClickListener(action: (view: View, position: Int, info: T) -> Unit): BannerLayout {
    val listener = object : OnBannerClickListener<T> {
        override fun onBannerClick(view: View, position: Int, info: T) = action(view, position, info)
    }
    addOnBannerClickListener(listener)
    return this
}

fun BannerLayout.doOnPageScrolled(action: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit) = addOnBannerChangeListener(onPageScrolled = action)

fun BannerLayout.doOnPageSelected(action: (position: Int) -> Unit) = addOnBannerChangeListener(onPageSelected = action)

fun BannerLayout.doOnPageScrollStateChanged(action: (state: Int) -> Unit) = addOnBannerChangeListener(onPageScrollStateChanged = action)

fun BannerLayout.addOnBannerChangeListener(
        onPageScrolled: (position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit = { _: Int, _: Float, _: Int -> },
        onPageSelected: (position: Int) -> Unit = { _: Int -> },
        onPageScrollStateChanged: (state: Int) -> Unit = { _: Int -> }
): BannerLayout {
    val listener = object : OnBannerChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = onPageScrolled(position, positionOffset, positionOffsetPixels)
        override fun onPageSelected(position: Int) = onPageSelected(position)
        override fun onPageScrollStateChanged(state: Int) = onPageScrollStateChanged(state)
    }
    addOnBannerChangeListener(listener)
    return this
}