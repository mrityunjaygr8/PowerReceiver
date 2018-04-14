package gr8.mrityunjay.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_POWER_CONNECTED
import android.content.Intent.ACTION_POWER_DISCONNECTED
import android.util.Log
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "gr8.mrityunjay.powerreceiver.ACTION_CUSTOM_RECEIVER"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        lateinit var toastMessage: String

        fun handlePowerConnected() {
            toastMessage = context.getString(R.string.power_connected)
        }
        fun handlePowerDisconnected() {
            toastMessage = context.getString(R.string.power_disconnected)
        }

        fun handleCustomAction() {
            toastMessage = context.getString(R.string.custom_intent_received)
        }

        when(intent.action){
            ACTION_POWER_CONNECTED -> handlePowerConnected()
            ACTION_POWER_DISCONNECTED -> handlePowerDisconnected()
            ACTION_CUSTOM_BROADCAST -> handleCustomAction()
        }

        Log.d("POWER_BROADCAST", "toastMessage:     $toastMessage")
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
