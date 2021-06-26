package com.zainco.library.broadcastreceiver.pluralsight.withpermissions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {

    // The receiver will be recreated whenever android feels like it. We need a
    // static variable to remember data between instantiations
    static PhonecallStartEndDetector listener;
    String contactName;
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (listener == null) {
            listener = new PhonecallStartEndDetector();

        }


        // The other intent tells us the phone state changed. Here we set a
        // listener to deal with it
        TelephonyManager telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

    }


    // Deals with actual events
    public class PhonecallStartEndDetector extends PhoneStateListener {
        int lastState = TelephonyManager.CALL_STATE_IDLE;
        boolean isIncoming;
        boolean isIncomingPicked;
        String savedNumber; // because the passed incoming is only valid in ringing

        public PhonecallStartEndDetector() {

        }


        // Incoming call- goes from IDLE to RINGING when it rings, to OFFHOOK
        // when it's answered, to IDLE when its hung up
        // Outgoing call- goes from IDLE to OFFHOOK when it dials out, to IDLE
        // when hung up
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            // state is changed
            if (lastState == state) {
                return;
            }

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    //an incoming call has been started

                    isIncoming = true;
                    savedNumber = incomingNumber;

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:

                    // Transition of ringing->offhook are pickups of incoming calls.
                    // Nothing down on them
                    if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                        //if pickup but device didn't ring..it means was an outgoing call
                        isIncoming = false;
                        isIncomingPicked = false;

                    } else if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                        // if pickup and device rang..it means an incoming call has been picked

                        isIncoming = true;
                        isIncomingPicked = true;
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:

                    // Went to idle- this is the end of a call. What type depends on
                    // previous state(s)
                    if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                        // Ring but no pickup- a miss
                        contactName = getContactName(savedNumber, context);


                    } else if (isIncoming || isIncomingPicked) {
                        //incoming call ended
                        contactName = getContactName(savedNumber, context);

                    }
                    break;
            }

        }

        //method to retrieve contact name

        private String getContactName(String number, Context context) {
            String contactNumber = "";

            // // define the columns I want the query to return
            String[] projection = new String[]{
                    ContactsContract.PhoneLookup.DISPLAY_NAME,
                    ContactsContract.PhoneLookup.NUMBER,
                    ContactsContract.PhoneLookup.HAS_PHONE_NUMBER};

            // encode the phone number and build the filter URI
            Uri contactUri = Uri.withAppendedPath(
                    ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                    Uri.encode(number));

            // query time
            Cursor cursor = context.getContentResolver().query(contactUri,
                    projection, null, null, null);
            // querying all contacts = Cursor cursor =
            // context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
            // projection, null, null, null);

            if (cursor.moveToFirst()) {
                contactName = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }
            cursor.close();
            return contactNumber.equals("") ? number : contactName;

        }

    }
}
