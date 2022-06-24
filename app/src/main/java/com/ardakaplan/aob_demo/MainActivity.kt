package com.ardakaplan.aob_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ardakaplan.aob_demo.aobobjects.Company
import com.ardakaplan.aob_demo.aobobjects.EnvironmentType
import com.ardakaplan.aob_demo.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickables()
    }

    private fun setClickables() {

        binding.mainActivityButtonOpenGenii.setOnClickListener {

            openGeniiDemo()
        }

        binding.mainActivityButtonSendData.setOnClickListener {

            openWithoutOrder()
        }
    }

    private fun openWithoutOrder() {

        AOBBroadcastReceiver.sendBroadcast(
            this,
            AobGeniiMessagingObject(
                Random().nextInt(10000),
                "150106",
                "23452342343",
                Company.AYEDAS,
                EnvironmentType.DIR,
                AobGeniiMessagingObject.Type.OPEN_WITHOUT_ORDER,
                AobGeniiMessagingObject.Trigger.AOB
            )
        )
    }

    private fun openGeniiDemo() {

        val launchIntent = packageManager.getLaunchIntentForPackage(Constants.GENII_DEMO_PACKAGE_NAME)

        launchIntent?.let {

            val aobGeniiMessagingObject = AobGeniiMessagingObject(
                Random().nextInt(10000),
                "150106",
                "23452342343",
                Company.AYEDAS,
                EnvironmentType.DIR,
                AobGeniiMessagingObject.Type.STAND_UP,
                AobGeniiMessagingObject.Trigger.AOB
            )

            val broadcastMessage = Gson().toJson(aobGeniiMessagingObject).toString()

            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            it.putExtra(Constants.BROADCAST_DATA_KEY, broadcastMessage)

            startActivity(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        LahanaLogger.logAOB("AOB DEMO onDestroy")
    }

    override fun onPause() {
        super.onPause()

        LahanaLogger.logAOB("AOB DEMO onPause")
    }

    override fun onResume() {
        super.onResume()

        LahanaLogger.logAOB("AOB DEMO onResume")
    }
}