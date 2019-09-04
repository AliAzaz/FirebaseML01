package com.abcd.firebasemlkt01.baseDialog

import android.content.Context

class BaseDialogPresenter(context: Context) : BaseDialogView.PresenterView {

    private val baseDialogMain: BaseDialogMain = BaseDialogMain(context)

    override fun setAlertDialog(flag: Boolean) {
        when {
            flag -> baseDialogMain.showDialog(baseDialogMain.createBuilder())
            else -> baseDialogMain.dismissDialog(baseDialogMain.createBuilder())
        }
    }

    override fun setMessage(message: String) {
        baseDialogMain.message(message)
    }

    override fun setTextColor(color: Int) {
        baseDialogMain.textColor(color)
    }

    override fun setTextSize(txtsize: Float) {
        baseDialogMain.textSize(txtsize)
    }

    override fun setAlertCancellable(cancel: Boolean) {
        baseDialogMain.alertCancellable(cancel)
    }
}