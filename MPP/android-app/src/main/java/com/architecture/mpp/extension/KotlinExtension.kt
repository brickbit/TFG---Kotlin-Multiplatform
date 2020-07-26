package com.architecture.mpp.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.hideMe(gone: Boolean = true) {
    this.visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.showMe() {
    this.visibility = View.VISIBLE
}

fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Context.toast(textId: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, textId, length).show()
}

fun Fragment.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(activity, text, length).show()
}

fun Fragment.toast(textId: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(activity, textId, length).show()
}