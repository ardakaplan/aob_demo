package com.ardakaplan.aob_demo

import android.util.Log

/**
 * Created by Arda Kaplan at 24.06.2022 - 14:58
 *
 * ardakaplan101@gmail.com
 */
class LahanaLogger {

    companion object {

        private const val AOB_LOG_TAG = "LAHANA - AOB"

        fun logAOB(message: String) {

            Log.d(AOB_LOG_TAG, message)
        }

    }
}