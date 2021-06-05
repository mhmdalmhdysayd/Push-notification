package com.example.assigmintthreepuchnavigtion

import android.app.Activity
import android.app.ProgressDialog
import android.widget.Toast
import com.kaopiz.kprogresshud.KProgressHUD

public class GlobalData {

        var progressDialog: ProgressDialog? = null
        var hud: KProgressHUD? = null

        fun progressDialogKH(activity: Activity?, status: Boolean) {
            try {
                if (activity != null && !activity.isFinishing) {
                    if (status) {
                        if (hud != null) {
                            if (!hud!!.isShowing) {
                                hud = KProgressHUD.create(activity)
                                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE) //                        .setBackgroundColor(activity.getResources().getColor(R.color.colorAccent))
                                    .setCancellable(true)
                                    .setAnimationSpeed(2)
                                    .setDimAmount(0.5f)
                                    .show()
                            }
                        } else {
                            hud = KProgressHUD.create(activity)
                                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE) //                        .setBackgroundColor(activity.getResources().getColor(R.color.colorAccent))
                                .setCancellable(true)
                                .setAnimationSpeed(2)
                                .setDimAmount(0.5f)
                                .show()
                        }
                    } else {
                        if (hud != null && hud!!.isShowing) {
                            hud!!.dismiss()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun Toast(c: Activity?, msg: String?) {
            if (c != null) {
                Toast.makeText(c, msg, Toast.LENGTH_SHORT).show()
            }
        }

    }