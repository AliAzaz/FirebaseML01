package com.abcd.firebasemlkt01.baseDialog

import android.content.Context

class BaseAlertDialogPresenter(context: Context) : BaseAlertDialogView.PresenterView {

    private val baseAlert: BaseAlertDialog = BaseAlertDialog(context)


    override fun settingDialogVisibility(flag: Boolean) {
        when {
            flag -> baseAlert.showDialog(baseAlert.createBuilder())
            else -> baseAlert.dismissDialog(baseAlert.createBuilder())
        }
    }

    override fun setMessage(message: String) {
        baseAlert.message(message)
    }

    override fun setTextColor(color: Int) {
        baseAlert.textColor(color)
    }

    override fun setTextSize(txtsize: Float) {
        baseAlert.textSize(txtsize)
    }

    override fun setAlertCancellable(cancel: Boolean) {
        baseAlert.alertCancellable(cancel)
    }
}