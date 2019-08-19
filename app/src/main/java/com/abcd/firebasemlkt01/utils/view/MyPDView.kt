package com.abcd.firebasemlkt01.utils.view

interface MyPDView {

    interface MyPDUIView {

        fun showDialog()

        fun dismissDialog()

    }

    interface MyPDUIPresenter {

        fun settingDialog(boolean: Boolean)

    }

}