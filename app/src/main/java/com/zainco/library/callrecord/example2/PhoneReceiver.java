/* Copyright (c) 2012 Kobi Krasnoff
 *
 * This file is part of Call recorder For Android.
 *
 * Call recorder For Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Call recorder For Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Call recorder For Android.  If not, see <http://www.gnu.org/licenses/>
 */
package com.zainco.library.callrecord.example2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class PhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED) &&
                !action.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            Log.e(Constants.TAG, "PhoneReceiver: Received unexpected intent: " + action);
            return;
        }

        UserPreferences.init(context);
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        String extraState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d(Constants.TAG, "PhoneReceiver phone number: " + phoneNumber);

        if (!FileHelper.isStorageWritable(context))
            return;

        if (extraState != null) {
            dispatchExtra(context, intent, phoneNumber, extraState);
        } else if (phoneNumber != null) {
            context.startService(new Intent(context, RecordService.class)
                    .putExtra("commandType", Constants.STATE_INCOMING_NUMBER)
                    .putExtra("phoneNumber", phoneNumber));
        }
    }

    private void dispatchExtra(Context context, Intent intent,
                               String phoneNumber, String extraState) {
        if (extraState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            context.startService(new Intent(context, RecordService.class)
                    .putExtra("commandType", Constants.STATE_CALL_START));
        } else if (extraState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            context.startService(new Intent(context, RecordService.class)
                    .putExtra("commandType", Constants.STATE_CALL_END));
        } else if (extraState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            if (phoneNumber == null)
                phoneNumber = intent.getStringExtra(
                        TelephonyManager.EXTRA_INCOMING_NUMBER);

            context.startService(new Intent(context, RecordService.class)
                    .putExtra("commandType", Constants.STATE_INCOMING_NUMBER)
                    .putExtra("phoneNumber", phoneNumber));
        }
    }
}
