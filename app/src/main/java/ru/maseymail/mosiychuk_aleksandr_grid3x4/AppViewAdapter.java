package ru.maseymail.mosiychuk_aleksandr_grid3x4;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AppViewAdapter extends RecyclerView.Adapter<AppViewAdapter.ViewHolder> implements Filterable {
    private PackageManager pm;
    private List<ApplicationInfo> packages;
    private List<ApplicationInfo> filtrPackages;
    private AppFilter appFilter = new AppFilter();

    public AppViewAdapter(PackageManager pm, List<ApplicationInfo> packages) {
        this.pm = pm;
        this.packages = packages;
        this.filtrPackages = packages;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.app_text_view);
            image = (ImageView) v.findViewById(R.id.app_item_image);
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
    public void onBindViewHolder(final AppViewAdapter.ViewHolder holder, int position) {
        final ApplicationInfo app = filtrPackages.get(position);

        holder.text.setText(app.loadLabel(pm).toString());
        holder.image.setImageDrawable(app.loadIcon(pm));


        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goActivity = holder.itemView.getContext().getPackageManager().getLaunchIntentForPackage(app.packageName);
                holder.itemView.getContext().startActivity(goActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filtrPackages.size();
    }


    @Override
    public Filter getFilter() {
        return appFilter;
    }

    class AppFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filtrPackages = new ArrayList<>();
            for (ApplicationInfo item : packages) {
                if (item.loadLabel(pm).toString().toLowerCase().contains(constraint.toString().toLowerCase())) {
                    filtrPackages.add(item);
                }
            }

            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    }
}
