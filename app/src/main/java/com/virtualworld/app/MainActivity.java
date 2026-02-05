package com.virtualworld.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.virtualworld.app.adapter.AppListAdapter;
import com.virtualworld.core.VirtualCore;
import com.virtualworld.core.app.VirtualAppInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppListAdapter adapter;
    private TextView emptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VirtualCore.init(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.virtual_app_list);
        emptyState = findViewById(R.id.empty_state);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AppListAdapter();
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.add_app_button);
        Button settingsButton = findViewById(R.id.settings_button);

        addButton.setOnClickListener(view -> startActivity(new Intent(this, AddAppActivity.class)));
        settingsButton.setOnClickListener(view -> startActivity(new Intent(this, SettingsActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<VirtualAppInfo> apps = VirtualCore.get().getAppManager().getInstalledApps();
        adapter.submitList(apps);
        if (apps.isEmpty()) {
            emptyState.setText(R.string.empty_apps);
        } else {
            emptyState.setText("");
        }
    }
}
