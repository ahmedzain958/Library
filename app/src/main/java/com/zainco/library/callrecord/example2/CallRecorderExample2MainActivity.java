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

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.zainco.library.R;

import java.util.ArrayList;
import java.util.List;

public class CallRecorderExample2MainActivity extends Activity {
    private ListView recordingsView;
    private ScrollView noRecordingsView;
    private ScrollView unusableView;
    private TextView unusableText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        UserPreferences.init(this);

        recordingsView = findViewById(R.id.recording_list);
        noRecordingsView = findViewById(R.id.no_recordings_view);
        unusableView = findViewById(R.id.storage_unusable_view);
        unusableText = findViewById(R.id.storage_unusable_text);

        if (!UserPreferences.getWelcomeSeen()) {
            //noinspection deprecation
            new AlertDialog.Builder(this)
                    .setTitle(R.string.welcome_title)
                    .setMessage(Html.fromHtml(getString(R.string.welcome_message)))
                    .setPositiveButton(R.string.close, (dialog, i) -> {
                        dialog.dismiss();
                        UserPreferences.setWelcomeSeen();
                    })
                    .create()
                    .show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkPerms();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPerms() {
        String[] perms = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.PROCESS_OUTGOING_CALLS
        };
        List<String> requestingPerms = new ArrayList<>();
        for (String perm : perms) {
            if (checkSelfPermission(perm) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestingPerms.add(perm);
            }
        }
        if (requestingPerms.size() > 0) {
            requestPermissions(requestingPerms.toArray(new String[requestingPerms.size()]), 0);
        }
    }

    @Override
    protected void onResume() {
        noRecordingsView.setVisibility(ScrollView.GONE);
        recordingsView.setVisibility(ListView.GONE);
        unusableView.setVisibility(ScrollView.GONE);
        if (!FileHelper.isStorageReadable(this)) {
            unusableView.setVisibility(ScrollView.VISIBLE);
            unusableText.setText(R.string.unreadable_message);
            super.onResume();
            return;
        }
        if (!FileHelper.isStorageWritable(this)) {
            unusableView.setVisibility(ScrollView.VISIBLE);
            unusableText.setText(R.string.read_only_message);
        }

        final List<Recording> listDir = FileHelper.listRecordings(this);

        if (listDir == null || listDir.isEmpty()) {
            noRecordingsView.setVisibility(TextView.VISIBLE);
            recordingsView.setVisibility(ScrollView.GONE);
            super.onResume();
            return;
        }
        noRecordingsView.setVisibility(TextView.GONE);
        recordingsView.setVisibility(ScrollView.VISIBLE);

        final RecordingsAdapter adapter = new RecordingsAdapter(this, listDir);

        recordingsView.setOnItemClickListener((parent, view, position, id) ->
                adapter.showContextMenu(listDir.get(position).getFileName(), position)
        );

        adapter.sort((arg0, arg1) -> -arg0.compareTo(arg1));

        recordingsView.setAdapter(adapter);

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean enabled = UserPreferences.getEnabled();

        MenuItem menuToggleRecord = menu.findItem(R.id.menu_toggle_record);

        if (enabled) {
            menuToggleRecord.setTitle(R.string.menu_disable_record)
                    .setIcon(R.drawable.ic_record_disable);
        } else {
            menuToggleRecord.setTitle(R.string.menu_enable_record)
                    .setIcon(R.drawable.ic_record_enable);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast toast;
        switch (item.getItemId()) {
            case R.id.menu_toggle_record:
                boolean enabled = UserPreferences.getEnabled();
                UserPreferences.setEnabled(!enabled);
                int toastText = enabled ? R.string.record_disabled :
                        R.string.record_enabled;
                toast = Toast.makeText(this, this.getString(toastText),
                        Toast.LENGTH_SHORT);
                toast.show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                    invalidateOptionsMenu();
                break;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.menu_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.menu_delete_all:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.dialog_delete_all_title)
                        .setMessage(R.string.dialog_delete_all_content)
                        .setPositiveButton(R.string.yes, (dialog, id) -> {
                            FileHelper.deleteAllRecords(CallRecorderExample2MainActivity.this);
                            onResume();
                            dialog.cancel();
                        })
                        .setNegativeButton(R.string.no, (dialog, id) -> {
                            dialog.cancel();
                        })
                        .show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
