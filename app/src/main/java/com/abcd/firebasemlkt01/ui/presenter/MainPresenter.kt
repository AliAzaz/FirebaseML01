package com.abcd.firebasemlkt01.ui.presenter

import android.graphics.Bitmap
import com.abcd.firebasemlkt01.ui.MainActivity
import com.abcd.firebasemlkt01.ui.view.MainView

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

    override fun onGettingExtractTextFRImage(imageTxt: String) {
        mainActivity.settingExtractTextFRImage(imageTxt)
    }

    override fun onGettingFirebaseVisionImage(bitmap: Bitmap) {
        mainActivity.settingFirebaseVisionImage(bitmap)
    }
}