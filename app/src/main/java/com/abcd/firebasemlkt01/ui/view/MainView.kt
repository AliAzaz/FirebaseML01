package com.abcd.firebasemlkt01.ui.view

import android.graphics.Bitmap
import com.google.firebase.ml.vision.text.FirebaseVisionText

interface MainView {

    interface UIView {

        fun setBitmapToImageView(bitmap: Bitmap)

        fun setVisionAnalysisText(bitmap: Bitmap, visionText: FirebaseVisionText)

        fun setVisionImage(bitmap: Bitmap?)

        fun setLabelOnImage(bitmap: Bitmap, blocks: List<FirebaseVisionText.TextBlock>)
    }

    interface PresenterView {
        fun onGettingPermissions(flag: Boolean = false)

        fun grantPermission(): Boolean

        fun onGettingBitmapFromImageView(bitmap: Bitmap)

        fun onGettingVisionAnalysisText(bitmap: Bitmap, visionText: FirebaseVisionText)

        fun onGettingVisionImage(bitmap: Bitmap)

        fun onGettingLabelFromImage(bitmap: Bitmap, blocks: List<FirebaseVisionText.TextBlock>)
    }

    interface ModelView
}