package com.zainco.library.callrecord.example2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;

import com.zainco.library.R;

class UserPreferences {
    private static Context context = null;
    private static SharedPreferences prefs = null;
    private static Uri default_storage = null;

    public static void init(Context ctx) {
        context = ctx;
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        default_storage = Uri.fromFile(context.getFilesDir());
    }

    private static void setString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit()
                .putString(key, value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
            editor.apply();
        else
            editor.commit();
    }

    private static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit()
                .putBoolean(key, value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD)
            editor.apply();
        else
            editor.commit();
    }

    public static Uri getStorageUri() {
        String str = prefs.getString("storage_location", null);
        return str == null ? default_storage : Uri.parse(str);
    }

    public static void setStorageUri(Uri uri) {
        setString("storage_location", uri.toString());
    }

    public static boolean getEnabled() {
        return prefs.getBoolean("enabled", false);
    }

    public static void setEnabled(boolean enabled) {
        setBoolean("enabled", enabled);

        context.startService(new Intent(context, RecordService.class)
                .putExtra("commandType", enabled ? Constants.RECORDING_DISABLED
                        : Constants.RECORDING_ENABLED)
                .putExtra("enabled", enabled));
    }

    public static boolean getWelcomeSeen() {
        return prefs.getBoolean("welcome_seen", false);
    }

    public static void setWelcomeSeen() {
        setBoolean("welcome_seen", true);
    }
}
