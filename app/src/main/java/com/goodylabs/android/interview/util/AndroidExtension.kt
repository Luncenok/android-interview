package com.goodylabs.android.interview.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

fun Activity.hideSoftInput() {
    getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(
        window.decorView.applicationWindowToken,
        0
    )
}

fun View.showSoftInput() {
    context.getSystemService<InputMethodManager>()?.showSoftInput(this, 0)
}
