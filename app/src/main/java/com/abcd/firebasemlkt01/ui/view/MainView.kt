package com.abcd.firebasemlkt01.ui.view

import android.graphics.Bitmap
import com.google.firebase.ml.vision.text.FirebaseVisionText

interface MainView {

    interface UIView {

        fun setBitmap(bitmap: Bitmap)

        fun setVisionText(bitmap: Bitmap, visionText: FirebaseVisionText)

        fun setVisionImage(bitmap: Bitmap?)

        fun setLabelOnImage(bitmap: Bitmap, blocks: List<FirebaseVisionText.TextBlock>)
    }

    interface PresenterView {
        fun onGettingPermissions(flag: Boolean = false)

        fun grantPermission(): Boolean

        fun onGettingBitmap(bitmap: Bitmap)

        fun onGettingVisionText(bitmap: Bitmap, visionText: FirebaseVisionText)

        fun onGettingVisionImage(bitmap: Bitmap)

        fun onGettingLabelFromImage(bitmap: Bitmap, blocks: List<FirebaseVisionText.TextBlock>)
    }

    interface ModelView
}