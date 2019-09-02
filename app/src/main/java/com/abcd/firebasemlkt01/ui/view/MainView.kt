package com.abcd.firebasemlkt01.ui.view

import android.graphics.Bitmap

interface MainView {

    interface UIView {

        fun settingBitmap(bitmap: Bitmap?)

        fun settingExtractTextFRImage(imageTxt: String = "")

        fun settingFirebaseVisionImage(bitmap: Bitmap)
    }

    interface PresenterView {
        fun onGettingPermissions(flag: Boolean = false)

        fun grantPermission(): Boolean

        fun onGettingBitmap(bitmap: Bitmap?)

        fun onGettingExtractTextFRImage(imageTxt: String)

        fun onGettingFirebaseVisionImage(bitmap: Bitmap)
    }

    interface ModelView
}