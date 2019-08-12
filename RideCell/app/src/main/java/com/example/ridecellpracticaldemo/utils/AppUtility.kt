package com.example.ridecellpracticaldemo.utils

import android.app.ProgressDialog
import android.content.Context

class AppUtility {
    companion object {


        var pDialog: ProgressDialog? = null

        var searchString = ""
        fun showProgress(context: Context) {
            if (pDialog == null) {
                pDialog = ProgressDialog(context)
                pDialog!!.setMessage("Loading...")
            }
            pDialog!!.show()
        }

        fun dismissProgress() {
            if (pDialog != null && pDialog!!.isShowing) {
                pDialog!!.dismiss()
                pDialog = null
            }
        }


    }

}