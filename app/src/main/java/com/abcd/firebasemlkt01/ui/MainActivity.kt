package com.abcd.firebasemlkt01.ui

import android.Manifest.permission
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.abcd.firebasemlkt01.R
import com.abcd.firebasemlkt01.ui.presenter.MainPresenter
import com.abcd.firebasemlkt01.ui.view.MainView
import com.abcd.firebasemlkt01.utils.presenter.MyPDPresenter
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView.UIView {

    lateinit var presenter: MainPresenter
    private val CAMERA_REQUEST: Int = 1001

    lateinit var progressPresenter: MyPDPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Presenter
        presenter = MainPresenter(this@MainActivity)
        //Initialize Progress Presenter
        progressPresenter = MyPDPresenter(this@MainActivity)
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

        capturedImage.setImageBitmap(bitmap)
        presenter.onGettingFirebaseVisionImage(bitmap)
    }

    override fun settingExtractTextFRImage(imageTxt: String) {
        imgTxtView.text = imageTxt

        progressPresenter.dismissDialog()
    }

    override fun settingFirebaseVisionImage(bitmap: Bitmap) {

        progressPresenter.showDialog()

        val fbVisionImg: FirebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap)
        var fbVisionTxtDetect: FirebaseVisionTextRecognizer = FirebaseVision.getInstance().onDeviceTextRecognizer
        fbVisionTxtDetect.processImage(fbVisionImg)
            .addOnSuccessListener {
                presenter.onGettingExtractTextFRImage(if (it.equals("")) "No Found any text!!" else it.text)
            }
            .addOnFailureListener {
                //                presenter.onGettingExtractTextFRImage("Error: " + it.printStackTrace())

                settingFirebaseVisionImage(bitmap)
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
        val imageBitmap = data?.extras?.get("data") as? Bitmap
        presenter.onGettingBitmap(imageBitmap)
    }
}
