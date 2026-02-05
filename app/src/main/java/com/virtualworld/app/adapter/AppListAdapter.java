package com.virtualworld.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.virtualworld.app.R;
import com.virtualworld.core.app.VirtualAppInfo;

import java.util.ArrayList;
import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppViewHolder> {

    private final List<VirtualAppInfo> apps = new ArrayList<>();

    public void submitList(List<VirtualAppInfo> newApps) {
        apps.clear();
        if (newApps != null) {
            apps.addAll(newApps);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_virtual_app, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        VirtualAppInfo appInfo = apps.get(position);
        holder.name.setText(appInfo.getDisplayName());
        holder.packageName.setText(appInfo.getPackageName());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView packageName;

        AppViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.app_name);
            packageName = itemView.findViewById(R.id.app_package);
        }
    }
}
