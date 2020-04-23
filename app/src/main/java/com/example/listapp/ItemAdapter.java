package com.example.listapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.widget.Filter;
import android.widget.Filterable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

        List<ItemModal> items;
    List<ItemModal> itemSearchs;
        Myfilter filter;
        boolean showFavorite;


        String[] colors = new String[] {
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B",
        };


        public ItemAdapter(List<ItemModal> items,List<ItemModal> itemSearchs, boolean showFavorite){
            this.items = items;
            this.showFavorite = showFavorite;
            this.itemSearchs =  itemSearchs;
            filter = new Myfilter(this);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
            return new EmailViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            EmailViewHolder viewHolder = (EmailViewHolder) holder;
            ItemModal item = itemSearchs.get(position);


            String c = colors[item.getFullName().length() % colors.length].toUpperCase();

            int color = Color.parseColor(c);

            viewHolder.imageAvatar.setImageResource(item.getImageAvatar());
            viewHolder.textAvatar.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            viewHolder.fullName.setText(item.getFullName());
            viewHolder.textAvatar.setText(item.getFullName().substring(0,1));
            viewHolder.description.setText(item.getDescription());
            viewHolder.date.setText(item.getDate());

            if(item.isSelected){
                viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_selected);
            }else{
                viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_normal);
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

    @Override
    public Filter getFilter() {
        return filter;
    }

    class EmailViewHolder extends RecyclerView.ViewHolder {

            ImageView imageAvatar;
            TextView fullName;
            TextView textAvatar;
            ImageView imageFavorite;
            TextView description;
            TextView date;

            public EmailViewHolder(@NonNull View itemView) {
                super(itemView);

                imageAvatar = itemView.findViewById(R.id.image_avatar);
                fullName = itemView.findViewById(R.id.full_name);
                imageFavorite = itemView.findViewById(R.id.image_favorite);
                textAvatar = itemView.findViewById(R.id.text_avatar);
                description = itemView.findViewById(R.id.description);
                date = itemView.findViewById(R.id.date);


                imageFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isChecked = items.get(getAdapterPosition()).isSelected;
                        items.get(getAdapterPosition()).setSelected(!isChecked);
                        notifyDataSetChanged();
                    }
                });

            }
        }
    class Myfilter extends Filter{
        public ItemAdapter adapter;

        public Myfilter(ItemAdapter adapter) {
            super();
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (showFavorite == true){
                itemSearchs.clear();
                final FilterResults results = new FilterResults();
                if(constraint.length() == 0 || constraint == null){
                    itemSearchs.addAll(items);
                }else{
                    final String filterPattern = constraint.toString().toLowerCase().trim();
                    for(ItemModal e : items){
                        if(e.getFullName().toLowerCase().contains(filterPattern)){
                            itemSearchs.add(e);
                            continue;
                        }
                        if(e.getDescription().toLowerCase().contains(filterPattern)){
                            itemSearchs.add(e);
                            continue;
                        }
                    }
                }
                results.values = itemSearchs;
                results.count = itemSearchs.size();
                return results;
            }
            else{
                itemSearchs.clear();
                final FilterResults results = new FilterResults();
                {
                    for(ItemModal e : items){
                        if(e.isSelected()){
                            itemSearchs.add(e);
                        }
                    }
                }
                results.values = itemSearchs;
                results.count = itemSearchs.size();
                return results;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.adapter.notifyDataSetChanged();
        }
    }
}

