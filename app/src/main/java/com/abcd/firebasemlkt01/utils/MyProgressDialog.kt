package com.abcd.firebasemlkt01.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.abcd.firebasemlkt01.utils.view.MyPDView


open class MyProgressDialog(context: Context) : ProgressBar(context), MyPDView.MyPDUIView {

    private val llPadding = 30
    private val ll = LinearLayout(context)
    private val tvText = TextView(context)
    private val builder = AlertDialog.Builder(context)
    lateinit var llParam: LinearLayout.LayoutParams
    lateinit var dialog: AlertDialog

    init {
        setProgressDialog()
    }

    private fun setProgressDialog() {

        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPadding, llPadding, llPadding, llPadding)
        ll.gravity = Gravity.CENTER
        llParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        ll.layoutParams = llParam

        this.isIndeterminate = true
        this.setPadding(0, 0, llPadding, 0)

        tvText.text = "Loading ..."
        tvText.setTextColor(Color.parseColor("#000000"))
        tvText.textSize = 20f
        tvText.layoutParams = llParam

        ll.addView(this)
        ll.addView(tvText)

        builder.setCancelable(true)
        builder.setView(ll)
        dialog = builder.create()
    }

    override fun showDialog() {
        dialog.show()
        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window.attributes = layoutParams
        }
    }

    override fun dismissDialog() {
        dialog.dismiss()
    }
}