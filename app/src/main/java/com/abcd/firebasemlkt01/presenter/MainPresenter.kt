package com.abcd.firebasemlkt01.presenter

import android.graphics.Bitmap
import com.abcd.firebasemlkt01.MainActivity
import com.abcd.firebasemlkt01.view.MainView

class MainPresenter(private val mainActivity: MainActivity) : MainView.PresenterView {

    var permissionFlag: Boolean

    init {
        permissionFlag = false
    }

    override fun onGettingPermissions(flag: Boolean) {
        permissionFlag = flag
    }

    override fun grantPermission(): Boolean {
        return permissionFlag
    }

    override fun onGettingBitmap(bitmap: Bitmap?) {
        mainActivity.settingBitmap(bitmap)
    }
}