package com.abcd.firebasemlkt01.baseDialog

import android.app.AlertDialog
import android.graphics.Color

interface BaseDialogView {

    interface UIView {
        fun dismissDialog(builder: AlertDialog)

        fun showDialog(builder: AlertDialog)

        fun message(message: String)

        fun textColor(color: Int)

        fun textSize(txtsize: Float)

        fun alertCancellable(cancel: Boolean)

        fun getAlertBuilder(): AlertDialog

        fun padding(left: Int, top: Int, right: Int, bottom: Int)
    }

    interface PresenterView {

        fun setAlertDialogView(flag: Boolean)

        fun setMessage(message: String = "Loading Image...")

        fun setTextColor(color: Int = Color.parseColor("#000000"))

        fun setTextSize(txtsize: Float = 20f)

        fun setAlertCancellable(cancel: Boolean = true)

        fun setPadding(left: Int = 0, top: Int = 30, right: Int = 30, bottom: Int = 30)

    }

}