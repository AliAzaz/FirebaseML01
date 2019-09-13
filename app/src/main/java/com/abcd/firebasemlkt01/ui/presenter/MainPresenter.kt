package com.abcd.firebasemlkt01.ui.presenter

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.provider.MediaStore
import com.abcd.firebasemlkt01.R
import com.abcd.firebasemlkt01.ui.MainActivity
import com.abcd.firebasemlkt01.ui.view.MainView
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.theartofdev.edmodo.cropper.CropImage

class MainPresenter(private val mainActivity: MainActivity) : MainView.PresenterView {

    private var permissionFlag = false

    init {
        onGettingPermission()
    }

    override fun grantPermission(): Boolean {
        return permissionFlag
    }

    override fun onCaptureClicked(CAMERA_REQUEST: Int) {
        if (!permissionFlag) {
            mainActivity.setToast(mainActivity.getString(R.string.denied))
            return
        }

        mainActivity.startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST)

    }

    override fun onGettingBitmapForImageView(bitmap: Bitmap) {
        mainActivity.showDialog()
        mainActivity.setBitmapOnImageView(bitmap)
        onGettingVisionBitmapAnalysis(bitmap)
    }

    override fun onGettingBitmapURIForCrop(bitmapURI: Uri) {
        CropImage.activity(bitmapURI)
            .setBackgroundColor(Color.parseColor("#80FFFFA6"))
            .setActivityTitle("Cropping Activity")
            .start(mainActivity)
    }

    private fun onGettingVisionAnalysisText(bitmap: Bitmap, visionText: FirebaseVisionText) {

        val blocks = visionText.textBlocks

        mainActivity.setTextView(
            when {
                blocks.isEmpty() -> "No Text Found!!"
                else -> visionText.text
            }
        )

        if (blocks.isEmpty()) {
            mainActivity.dismissDialog()
            return
        }

        onGettingLabelFromImage(bitmap, blocks)

    }

    private fun onGettingVisionBitmapAnalysis(bitmap: Bitmap) {

        val fbVisionImg = FirebaseVisionImage.fromBitmap(bitmap)
        val fbVisionTxtDetect = FirebaseVision.getInstance().onDeviceTextRecognizer
        fbVisionTxtDetect.processImage(fbVisionImg)
            .addOnSuccessListener {
                onGettingVisionAnalysisText(bitmap, it)
            }
            .addOnFailureListener {
                when {
                    it.printStackTrace().equals("Waiting for the text recognition model to be downloaded. Please wait.") -> {
                        onGettingVisionBitmapAnalysis(bitmap)
                    }
                    else -> {
                        mainActivity.dismissDialog()
                        it.printStackTrace()
                    }
                }

            }
    }

    private fun onGettingLabelFromImage(bitmap: Bitmap, blocks: List<FirebaseVisionText.TextBlock>) {

        val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(mutableBitmap)
        val graphics = onGettingGraphics()

        for (i in blocks.indices) {
            val lines: List<FirebaseVisionText.Line> = blocks[i].lines
            for (j in lines.indices) {
                val elements: List<FirebaseVisionText.Element> = lines[j].elements
                for (k in elements.indices) {
                    canvas.drawRect(elements[k].boundingBox, graphics.first)
                }
            }
        }

        mainActivity.setBitmapOnImageView(mutableBitmap)
        mainActivity.dismissDialog()

    }

    private fun onGettingPermission() {
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        Permissions.check(mainActivity/*context*/, permissions, null/*options*/, null, object : PermissionHandler() {
            override fun onGranted() {
                permissionFlag = true
            }
        })
    }

    private fun onGettingGraphics(): Pair<Paint, Paint> {
        val rectPaint = Paint()
        rectPaint.color = Color.RED
        rectPaint.style = Paint.Style.STROKE
        rectPaint.strokeWidth = 4F

        val textPaint = Paint()
        textPaint.color = Color.RED
        textPaint.textSize = 40F

        return Pair(rectPaint, textPaint)
    }

}