package com.abcd.firebasemlkt01.ui

import android.Manifest.permission
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.abcd.firebasemlkt01.R
import com.abcd.firebasemlkt01.baseDialog.BaseDialogPresenter
import com.abcd.firebasemlkt01.ui.presenter.MainPresenter
import com.abcd.firebasemlkt01.ui.view.MainView
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView.UIView {

    lateinit var presenter: MainPresenter
    private val CAMERA_REQUEST: Int = 1001

    lateinit var baseDialog: BaseDialogPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Presenter
        presenter = MainPresenter(this@MainActivity)
        //Initialize Progress Presenter
        baseDialog = BaseDialogPresenter(this@MainActivity)
        //Calling Permission
        settingPermissions()
        //Setting Listeners
        settingListeners()
    }

    override fun settingBitmap(bitmap: Bitmap?) {
        if (bitmap == null) {
            presenter.onGettingExtractTextFRImage("Not Found!!")
            return
        }

        baseDialog.setAlertDialogView(true)

        capturedImage.setImageBitmap(bitmap)
        capturedImage.isShowCropOverlay = false
        presenter.onGettingFirebaseVisionImage(bitmap)
    }

    override fun settingExtractTextFRImage(imageTxt: String) {
        imgTxtView.text = imageTxt

        baseDialog.setAlertDialogView(false)
    }

    override fun settingFirebaseVisionImage(bitmap: Bitmap) {

        val fbVisionImg: FirebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap)
        var fbVisionTxtDetect: FirebaseVisionTextRecognizer = FirebaseVision.getInstance().onDeviceTextRecognizer
        fbVisionTxtDetect.processImage(fbVisionImg)
            .addOnSuccessListener {
                presenter.onGettingExtractTextFRImage(if (it.equals("")) "Not Found any text!!" else it.text)
            }
            .addOnFailureListener {
                when {
                    it.printStackTrace().equals("Waiting for the text recognition model to be downloaded. Please wait.") -> {
                        settingFirebaseVisionImage(
                            bitmap
                        )
                    }
                    else -> presenter.onGettingExtractTextFRImage("Error: " + it.printStackTrace())
                }

            }


    }

    private fun settingPermissions() {
        val permissions = arrayOf(permission.CAMERA, permission.WRITE_EXTERNAL_STORAGE)
        Permissions.check(this/*context*/, permissions, null/*options*/, null, object : PermissionHandler() {
            override fun onGranted() {
                presenter.onGettingPermissions(true)
            }
        })
    }

    private fun settingListeners() {
        btnOpenCamera.setOnClickListener {
            if (!presenter.grantPermission()) {
                Toast.makeText(this, getString(R.string.denied), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != CAMERA_REQUEST && resultCode != Activity.RESULT_OK) return

        when (requestCode) {

            CAMERA_REQUEST -> CropImage.activity(data!!.data)
                .setBackgroundColor(Color.parseColor("#80FFFFA6"))
                .setActivityTitle("Cropping Activity")
                .start(this)

            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE ->
                presenter.onGettingBitmap(
                    MediaStore.Images.Media.getBitmap(
                        this.contentResolver,
                        CropImage.getActivityResult(data).uri
                    )
                )

        }

    }
}
