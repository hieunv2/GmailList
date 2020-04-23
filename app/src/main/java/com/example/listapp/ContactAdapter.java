package com.example.listapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    public ContactAdapter(List<ItemModal> items) {
        this.items = items;
    }

    List<ItemModal> items;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

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


        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.imageAvatar = view.findViewById(R.id.image_avatar);
            viewHolder.fullName = view.findViewById(R.id.full_name);
            viewHolder.imageFavorite = view.findViewById(R.id.image_favorite);
            viewHolder.textAvatar = view.findViewById(R.id.text_avatar);
            viewHolder.description = view.findViewById(R.id.description);
            viewHolder.date = view.findViewById(R.id.date);
            view.setTag(viewHolder);

        }else viewHolder = (ViewHolder) view.getTag();

        final ItemModal item = items.get(i);

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



        viewHolder.imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = items.get(i).isSelected;
                items.get(i).setSelected(!isChecked);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    class ViewHolder {
        ImageView imageAvatar;
        TextView fullName;
        TextView textAvatar;
        ImageView imageFavorite;
        TextView description;
        TextView date;
    }
}
