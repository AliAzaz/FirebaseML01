package com.abcd.firebasemlkt01.utils.presenter

import android.content.Context
import com.abcd.firebasemlkt01.utils.MyProgressDialog
import com.abcd.firebasemlkt01.utils.view.MyPDView

class MyPDPresenter(context: Context) : MyProgressDialog(context), MyPDView.MyPDUIPresenter {

    override fun settingDialog(boolean: Boolean) {
        when {
            boolean -> super.showDialog()
            else -> super.dismissDialog()
        }
    }

}