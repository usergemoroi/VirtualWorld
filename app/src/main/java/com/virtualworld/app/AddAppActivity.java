package com.virtualworld.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.virtualworld.core.VirtualCore;
import com.virtualworld.core.app.VirtualAppInfo;

public class AddAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app);

        EditText apkPathInput = findViewById(R.id.apk_path_input);
        Button installButton = findViewById(R.id.install_button);

        installButton.setOnClickListener(view -> {
            String apkPath = apkPathInput.getText().toString().trim();
            if (apkPath.isEmpty()) {
                Toast.makeText(this, R.string.apk_path_required, Toast.LENGTH_SHORT).show();
                return;
            }
            VirtualAppInfo info = VirtualCore.get().getAppManager().installPackage(apkPath);
            Toast.makeText(this, getString(R.string.apk_installed, info.getDisplayName()), Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
