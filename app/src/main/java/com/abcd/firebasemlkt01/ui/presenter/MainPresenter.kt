package com.abcd.firebasemlkt01.ui.presenter

import android.graphics.Bitmap
import com.abcd.firebasemlkt01.ui.MainActivity
import com.abcd.firebasemlkt01.ui.view.MainView
import com.google.firebase.ml.vision.text.FirebaseVisionText

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

    override fun onGettingBitmap(bitmap: Bitmap) {
        mainActivity.setBitmap(bitmap)
    }

    override fun onGettingVisionText(visionText: FirebaseVisionText) {
        mainActivity.setVisionText(visionText)
    }

    override fun onGettingVisionImage(bitmap: Bitmap) {
        mainActivity.setVisionImage(bitmap)
    }

    override fun onGettingLabelFromImage(blocks: List<FirebaseVisionText.TextBlock>) {
        mainActivity.setLabelOnImage(blocks)
    }
}