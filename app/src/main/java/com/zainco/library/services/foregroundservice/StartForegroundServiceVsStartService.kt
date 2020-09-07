package com.zainco.library.services.foregroundservice

/**
 * Created by Ahmed Zain on 9/7/2020.
 */
/*
* From Oreo(API 26) on-wards you can no longer start a Service when your app is in the background.
* An example of this is a situation where you are starting a Service from a BroadcastReceiver.
* If your app is in the foreground startService will still work as expected,
* but if it’s not in the foreground the system will just stop your Service from starting – which is pretty inconvenient.*/