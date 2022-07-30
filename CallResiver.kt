package com.example.farmbee

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.view.Gravity
import android.widget.Toast

class CallResiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent!!.getStringExtra(TelephonyManager.EXTRA_STATE)== TelephonyManager.EXTRA_STATE_RINGING){

        }

    }


}

