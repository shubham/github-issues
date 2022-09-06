package com.babapanda.gitpr.base

import android.view.View

interface BaseHandler<T> {
    fun onclick(view: View, data: T)
}