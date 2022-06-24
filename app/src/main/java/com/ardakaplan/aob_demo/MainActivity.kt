package com.ardakaplan.aob_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ardakaplan.aob_demo.databinding.ActivityMainBinding


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

            sendBroadcast("BROADCAST GÖNDERİLDİ")
        }
    }


    private fun sendBroadcast(broadcastMessage: String) {

        val broadcastIntent = Intent(Constants.GENII_BROADCAST_LISTENER)
        broadcastIntent.setPackage(Constants.GENII_DEMO_PACKAGE_NAME);
        broadcastIntent.putExtra(Constants.BROADCAST_DATA_KEY, broadcastMessage)

        LahanaLogger.logAOB("BROADCAST GÖNDERİLDİ , mesaj ->" + broadcastMessage)

        sendBroadcast(broadcastIntent)
    }

    private fun openGeniiDemo() {

        val launchIntent = packageManager.getLaunchIntentForPackage(Constants.GENII_DEMO_PACKAGE_NAME)

        launchIntent?.let {

            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            it.putExtra("DATA", "OPEN GENII DEMO")

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