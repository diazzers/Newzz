package com.diazzers.newzz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diazzers.newzz.OnItemClickListener;
import com.diazzers.newzz.R;
import com.diazzers.newzz.pojo.Article;
import com.diazzers.newzz.pojo.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private OnItemClickListener listener;

    private ArrayList<Category> categoriesList = new ArrayList<>();

    public void addData(ArrayList<Category> categoriesList) {
        this.categoriesList.clear();
        this.categoriesList.addAll(categoriesList);
        notifyDataSetChanged();
    }

    public void addOnClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTextView;
        ImageView categoryImageView;
        LinearLayout categoryLinearLayout;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.category_text_view);
            categoryImageView = itemView.findViewById(R.id.category_image_view);
            categoryLinearLayout = itemView.findViewById(R.id.category_linear_layout);

        }


        public void bind(final Category category, final OnItemClickListener listener, final int position) {
            categoryTextView.setText(category.getCategoryName());
            Picasso.get().load(category.getCategoryImage()).into(categoryImageView);

            categoryLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onClick(position);


                }
            });

        }
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoriesAdapter.CategoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item_view, parent, false));

    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        holder.bind(categoriesList.get(position),listener, position);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();

    }
}
