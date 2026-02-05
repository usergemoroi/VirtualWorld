package com.virtualworld.app;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.virtualworld.core.VirtualCore;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch rootToggle = findViewById(R.id.root_toggle);
        rootToggle.setChecked(VirtualCore.get().getFileSystem().isRootEnabled());

        rootToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            VirtualCore.get().getFileSystem().setRootEnabled(isChecked);
            Toast.makeText(this, isChecked ? R.string.root_enabled : R.string.root_disabled, Toast.LENGTH_SHORT).show();
        });
    }
}
