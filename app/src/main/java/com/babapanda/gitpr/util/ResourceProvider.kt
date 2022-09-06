package com.babapanda.gitpr.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String

    fun getColor(@ColorRes resId: Int): Int

    fun getDrawable(@DrawableRes resId: Int): Drawable?
}

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int): String = context.getString(resId)

    override fun getColor(resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getDrawable(resId: Int): Drawable? = ContextCompat.getDrawable(context, resId)

}