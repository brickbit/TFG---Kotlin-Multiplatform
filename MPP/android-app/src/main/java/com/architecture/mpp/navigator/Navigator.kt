package com.architecture.mpp.navigator

import android.content.Context
import android.content.Intent
import com.architecture.mpp.view.activity.MonumentDetailActivity

fun navigateToDetailActivity(context: Context, categoryId: String) {
    val intent = Intent(context, MonumentDetailActivity::class.java)
    intent.putExtra(MonumentDetailActivity.CATEGORY_ID_KEY, categoryId)
    context.startActivity(intent)
}