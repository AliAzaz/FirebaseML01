package com.abcd.firebasemlkt01.baseDialog

import android.content.Context

class BaseDialogPresenter(context: Context) : BaseDialogView.PresenterView {

    private val baseDialogMain: BaseDialogMain = BaseDialogMain(context)

    init {
        setMessage()
        setTextColor()
        setTextSize()
        setAlertCancellable(false)
        setPadding()
    }

    override fun setAlertDialogView(flag: Boolean) {
        when {
            flag -> baseDialogMain.showDialog(baseDialogMain.getAlertBuilder())
            else -> baseDialogMain.dismissDialog(baseDialogMain.getAlertBuilder())
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

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        baseDialogMain.setPadding(left, top, right, bottom)
    }
}