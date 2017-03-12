package ru.maseymail.mosiychuk_aleksandr_grid3x4;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class AppViewAdapter extends RecyclerView.Adapter<AppViewAdapter.ViewHolder> {
    private PackageManager pm;
    private List<ApplicationInfo> packages;


    public AppViewAdapter (PackageManager pm,List<ApplicationInfo> packages){
        this.pm=pm;
        this.packages=packages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.app_text_view);
            image=(ImageView) v.findViewById(R.id.app_item_image);
        }
    }

    @Override
    public AppViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_view_items, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AppViewAdapter.ViewHolder holder, int position) {
        ApplicationInfo app = packages.get(position);

        holder.text.setText(app.loadLabel(pm).toString());
        holder.image.setImageDrawable(app.loadIcon(pm));
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }


}
