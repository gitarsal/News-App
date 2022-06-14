package com.assignment.synaos.common

import android.app.Activity
import android.view.View
import com.assignment.synaos.utils.Constants
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.net.Uri


object ExtensionFunctions {

    fun String.getFormattedDate(): String {
        val serverDate = SimpleDateFormat(Constants.SERVER_DATE_FORMAT, Locale.getDefault())

        val displayDate = SimpleDateFormat(Constants.UI_DATE_FORMAT, Locale.getDefault())

        val date: Date? = serverDate.parse(this)
        return if (date != null)
            displayDate.format(date)
        else
            ""

    }


    fun View.gone(isHide: Boolean = true) {
        this.visibility = if (isHide) View.GONE else View.VISIBLE
    }

    fun Activity.openUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

}