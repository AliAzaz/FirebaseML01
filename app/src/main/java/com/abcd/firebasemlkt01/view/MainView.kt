package com.abcd.firebasemlkt01.view

import android.graphics.Bitmap

interface MainView {

    interface UIView {

        fun settingBitmap(bitmap: Bitmap?)
    }

    interface PresenterView {
        fun onGettingPermissions(flag: Boolean = false)

        fun grantPermission(): Boolean

        fun onGettingBitmap(bitmap: Bitmap?)
    }
}