package com.abcd.firebasemlkt01

import android.Manifest.permission
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.abcd.firebasemlkt01.presenter.MainPresenter
import com.abcd.firebasemlkt01.view.MainView
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView.UIView {

    lateinit var presenter: MainPresenter
    val CAMERA_REQUEST: Int = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Presenter
        presenter = MainPresenter(this@MainActivity)

        //Setting Listeners
        settingListeners()
    }

    override fun settingPermissions() {
        val permissions = arrayOf(permission.CAMERA, permission.WRITE_EXTERNAL_STORAGE)
        Permissions.check(this/*context*/, permissions, null/*options*/, null, object : PermissionHandler() {
            override fun onGranted() {
                presenter.onGettingPermissions(true)
            }
        })
    }

    override fun settingBitmap(bitmap: Bitmap?) {
        capturedImage.setImageBitmap(bitmap)
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
        val imageBitmap = data?.extras?.get("data") as Bitmap
        presenter.onGettingBitmap(imageBitmap)
    }
}
