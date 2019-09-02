package com.abcd.firebasemlkt01.baseDialog

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

open class BaseAlertDialog(context: Context) : BaseAlertDialogView.UIView, ProgressBar(context) {

    private val llPadding = 30
    private val ll: LinearLayout = LinearLayout(context)
    private val tvText: TextView = TextView(context)
    private val builder = AlertDialog.Builder(context)
    lateinit var llParam: LinearLayout.LayoutParams

    init {
        setAlertDialog()
    }

    private fun setAlertDialog() {

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

        tvText.layoutParams = llParam

        ll.addView(this)

    }


    override fun dismissDialog(dialog: AlertDialog) {
        if (dialog.window != null) {
            dialog.dismiss()
        }
    }

    override fun showDialog(dialog: AlertDialog) {
        dialog.show()
        if (dialog.window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window.attributes = layoutParams
        }
    }

    override fun message(message: String) {
        tvText.text = message
    }

    override fun textColor(color: Int) {
        tvText.setTextColor(color)
    }

    override fun textSize(txtsize: Float) {
        tvText.textSize = txtsize
    }

    override fun alertCancellable(cancel: Boolean) {
        builder.setCancelable(cancel)
    }

    override fun createBuilder(): AlertDialog {
        ll.addView(tvText)
        builder.setView(ll)

        return builder.create()
    }
}