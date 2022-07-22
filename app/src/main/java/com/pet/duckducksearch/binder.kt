package com.pet.duckducksearch

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

fun <T : View> Activity.bindView(@IdRes id: Int): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(id) }