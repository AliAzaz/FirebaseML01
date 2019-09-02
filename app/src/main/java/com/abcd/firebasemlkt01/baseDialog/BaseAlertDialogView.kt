package com.abcd.firebasemlkt01.baseDialog

import android.app.AlertDialog
import android.graphics.Color

interface BaseAlertDialogView {

    interface UIView {
        fun dismissDialog(builder: AlertDialog)

        fun showDialog(builder: AlertDialog)

        fun message(message: String)

        fun textColor(color: Int)

        fun textSize(txtsize: Float)

        fun alertCancellable(cancel: Boolean)

        fun createBuilder(): AlertDialog
    }

    interface PresenterView {

        fun settingDialogVisibility(flag: Boolean)

        fun setMessage(message: String = "Loading Image...")

        fun setTextColor(color: Int = Color.parseColor("#000000"))

        fun setTextSize(txtsize: Float = 20f)

        fun setAlertCancellable(cancel: Boolean = true)

    }

}