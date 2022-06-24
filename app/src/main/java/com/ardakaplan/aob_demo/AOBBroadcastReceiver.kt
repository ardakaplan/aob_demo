package com.ardakaplan.aob_demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.gson.Gson

/**
 * Created by Arda Kaplan at 24.06.2022 - 14:46
 *
 * ardakaplan101@gmail.com
 */
class AOBBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        LahanaLogger.logAOB("onReceive tetiklendi")

        intent.extras?.containsKey(Constants.BROADCAST_DATA_KEY)?.let {

            LahanaLogger.logAOB("onReceive data : " + intent.extras!!.getString("DATA"))

            val broadcastMessage = intent.extras!!.getString(Constants.BROADCAST_DATA_KEY)

            val aobGeniiMessagingObject = Gson().fromJson(broadcastMessage, AobGeniiMessagingObject::class.java)

            LahanaLogger.logAOB("onReceive aobGeniiMessagingObject :\n" + aobGeniiMessagingObject)

            if (aobGeniiMessagingObject.type == AobGeniiMessagingObject.Type.HANDSHAKE) {

                LahanaLogger.logAOB("onReceive HANDSKAKE MESAJI GELDI")

            } else {

                LahanaLogger.logAOB("onReceive ${aobGeniiMessagingObject.type} MESAJI GELDI")

                LahanaLogger.logAOB("onReceive HANSHAKE gönderilecek")

                aobGeniiMessagingObject.type = AobGeniiMessagingObject.Type.HANDSHAKE

                sendBroadcast(context, aobGeniiMessagingObject)
            }

        }

        if (intent.extras == null) {

            LahanaLogger.logAOB("onReceive intent.extras is NULL")
        }


    }

    companion object {

        fun sendBroadcast(context: Context, aobGeniiMessagingObject: AobGeniiMessagingObject) {

            val broadcastMessage = Gson().toJson(aobGeniiMessagingObject).toString()

            val broadcastIntent = Intent(Constants.GENII_BROADCAST_LISTENER)
            broadcastIntent.setPackage(Constants.GENII_DEMO_PACKAGE_NAME)
            broadcastIntent.putExtra(Constants.BROADCAST_DATA_KEY, broadcastMessage)

            LahanaLogger.logAOB("BROADCAST GÖNDERİLDİ , mesaj ->" + broadcastMessage)

            context.sendBroadcast(broadcastIntent)
        }

    }
}