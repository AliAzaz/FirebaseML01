package com.abcd.firebasemlkt01.baseDialog

import android.content.Context

class BaseAlertDialogPresenter(context: Context) : BaseAlertDialog(context), BaseAlertDialogView.PresenterView {

    override fun settingDialogVisibility(flag: Boolean) {
        when {
            flag -> super.showDialog(super.createBuilder())
            else -> super.dismissDialog(super.createBuilder())
        }
    }

    override fun setMessage(message: String) {
        super.message(message)
    }

    override fun setTextColor(color: Int) {
        super.textColor(color)
    }

    override fun setTextSize(txtsize: Float) {
        super.textSize(txtsize)
    }

    override fun setAlertCancellable(cancel: Boolean) {
        super.alertCancellable(cancel)
    }
}