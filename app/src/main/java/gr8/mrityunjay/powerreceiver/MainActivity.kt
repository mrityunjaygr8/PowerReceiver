package gr8.mrityunjay.powerreceiver

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.View

class MainActivity : AppCompatActivity() {

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "gr8.mrityunjay.powerreceiver.ACTION_CUSTOM_RECEIVER"
    }

    private val mReceiver: CustomReceiver = CustomReceiver()

    lateinit var mPackageManager: PackageManager
    lateinit var mCompomentName: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        mCompomentName = ComponentName(this, CustomReceiver::class.java)
        mPackageManager = packageManager

        //  Power charging received registered
        this.registerReceiver(CustomReceiver(), filter)
        //  Custom receiver registered
        this.registerReceiver(mReceiver, IntentFilter(gr8.mrityunjay.powerreceiver.MainActivity.ACTION_CUSTOM_BROADCAST))
    }

    override fun onStart() {
        super.onStart()
        mPackageManager.setComponentEnabledSetting(mCompomentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
    }

    override fun onStop() {
        super.onStop()
        mPackageManager.setComponentEnabledSetting(mCompomentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
    }

    fun sendCustomBroadcast(v: View){
        val customIntent = Intent(ACTION_CUSTOM_BROADCAST)
        this.sendBroadcast(customIntent)
    }
}
