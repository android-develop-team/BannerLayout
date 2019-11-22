package com.android.banner.page

import android.graphics.Color
import android.text.TextUtils
import com.android.banner.checkViewPager
import com.android.banner.doOnPageSelected
import com.android.banner.dotsSize
import com.android.banner.widget.BannerLayout

fun BannerLayout.marginPageView(
        margin: Int,
        pagePaddingTop: Int = 5,
        pagePaddingLeft: Int = 20,
        pagePaddingBottom: Int = 5,
        pagePaddingRight: Int = 20,
        pageRadius: Float = 25f,
        pageMark: String = " / ",
        pageTextSize: Float = 10f,
        pageTextColor: Int = Color.WHITE,
        pageBackgroundColor: Int = Color.DKGRAY,
        pageSite: Int = BannerPageView.PAGE_NUM_VIEW_TOP_RIGHT) = also {
    addPageView(
            margin, margin, margin, margin, pagePaddingTop, pagePaddingLeft, pagePaddingBottom, pagePaddingRight, pageRadius, pageMark, pageTextSize, pageTextColor, pageBackgroundColor, pageSite
    )
}

fun BannerLayout.paddingPageView(
        padding: Int,
        pageTopMargin: Int = 0,
        pageRightMargin: Int = 0,
        pageBottomMargin: Int = 0,
        pageLeftMargin: Int = 0,
        pageRadius: Float = 25f,
        pageMark: String = " / ",
        pageTextSize: Float = 10f,
        pageTextColor: Int = Color.WHITE,
        pageBackgroundColor: Int = Color.DKGRAY,
        pageSite: Int = BannerPageView.PAGE_NUM_VIEW_TOP_RIGHT) = also {
    addPageView(
            pageTopMargin, pageRightMargin, pageBottomMargin, pageLeftMargin, padding, padding, padding, padding, pageRadius, pageMark, pageTextSize, pageTextColor, pageBackgroundColor, pageSite
    )
}

fun BannerLayout.addPageView(
        pageTopMargin: Int = 0,
        pageRightMargin: Int = 0,
        pageBottomMargin: Int = 0,
        pageLeftMargin: Int = 0,
        pagePaddingTop: Int = 5,
        pagePaddingLeft: Int = 20,
        pagePaddingBottom: Int = 5,
        pagePaddingRight: Int = 20,
        pageRadius: Float = 25f,
        pageMark: String = " / ",
        pageTextSize: Float = 10f,
        pageTextColor: Int = Color.WHITE,
        pageBackgroundColor: Int = Color.DKGRAY,
        pageSite: Int = BannerPageView.PAGE_NUM_VIEW_TOP_RIGHT) = also {
    require(checkViewPager()) { "must add ViewPage first;" }
    val bannerPageView = BannerPageView(context)
    val params = bannerPageView.run {
        addPageView(dotsSize(), pageTopMargin, pageRightMargin, pageBottomMargin, pageLeftMargin, pagePaddingTop, pagePaddingLeft, pagePaddingBottom, pagePaddingRight, pageRadius, pageMark, pageTextSize, pageTextColor, pageBackgroundColor, pageSite)
    }
    doOnPageSelected { bannerPageView.text = TextUtils.concat((if (dotsSize() == 0) 0 else it % dotsSize() + 1).toString(), pageMark, dotsSize().toString()) }
    addView(bannerPageView, params)
}

