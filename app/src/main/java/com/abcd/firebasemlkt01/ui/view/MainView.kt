package com.abcd.firebasemlkt01.ui.view

import android.graphics.Bitmap
import android.net.Uri

interface MainView {

    interface UIView {

        fun setTextView(analyzedText: String)

        fun setBitmapOnImageView(bitmap: Bitmap)

        fun setToast(message: String)

        fun showDialog()

        fun dismissDialog()

    }

    interface PresenterView {

        fun grantPermission(): Boolean

        fun onGettingBitmapForImageView(bitmap: Bitmap)

        fun onCaptureClicked(CAMERA_REQUEST: Int)

        fun onGettingBitmapURIForCrop(bitmapURI: Uri)
    }

    interface ModelView
}