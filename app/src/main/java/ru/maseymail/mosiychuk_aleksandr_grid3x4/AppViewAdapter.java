package ru.maseymail.mosiychuk_aleksandr_grid3x4;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


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
    public void onBindViewHolder(AppViewAdapter.ViewHolder holder, int position) {
        ApplicationInfo app = filtrPackages.get(position);
        Log.i("my", app.loadLabel(pm).toString());
        holder.text.setText(app.loadLabel(pm).toString());

        holder.image.setImageDrawable(app.loadIcon(pm));
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
            filtrPackages = new List<ApplicationInfo>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @NonNull
                @Override
                public Iterator<ApplicationInfo> iterator() {
                    return null;
                }

                @NonNull
                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @NonNull
                @Override
                public <T> T[] toArray(@NonNull T[] a) {
                    return null;
                }

                @Override
                public boolean add(ApplicationInfo applicationInfo) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(@NonNull Collection<? extends ApplicationInfo> c) {
                    return false;
                }

                @Override
                public boolean addAll(int index, @NonNull Collection<? extends ApplicationInfo> c) {
                    return false;
                }

                @Override
                public boolean removeAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public boolean retainAll(@NonNull Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public boolean equals(Object o) {
                    return false;
                }

                @Override
                public int hashCode() {
                    return 0;
                }

                @Override
                public ApplicationInfo get(int index) {
                    return null;
                }

                @Override
                public ApplicationInfo set(int index, ApplicationInfo element) {
                    return null;
                }

                @Override
                public void add(int index, ApplicationInfo element) {

                }

                @Override
                public ApplicationInfo remove(int index) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<ApplicationInfo> listIterator() {
                    return null;
                }

                @NonNull
                @Override
                public ListIterator<ApplicationInfo> listIterator(int index) {
                    return null;
                }

                @NonNull
                @Override
                public List<ApplicationInfo> subList(int fromIndex, int toIndex) {
                    return null;
                }
            };
            for (ApplicationInfo item : packages) {
                if (item.loadLabel(pm).toString().toLowerCase().contains(constraint.toString().toLowerCase())) {
                    filtrPackages.add(item);
                }
            }

            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
        Log.i("my","notify");
            notifyDataSetChanged();
        }
    }
}
