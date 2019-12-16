package com.diazzers.newzz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diazzers.newzz.OnItemClickListener;
import com.diazzers.newzz.R;
import com.diazzers.newzz.pojo.Article;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {

    private OnItemClickListener listener;

    private ArrayList<Article> articlesList = new ArrayList<>();

    public void addData(ArrayList<Article> articlesList) {
        this.articlesList.clear();
        this.articlesList.addAll(articlesList);
        notifyDataSetChanged();
    }
    public void clearData(){
        articlesList.clear();
        notifyDataSetChanged();
    }

    public void addOnClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView publishedAtTextView;
        TextView sourceNameTextView;
        ImageView articleImageView;

        public ArticlesViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            publishedAtTextView = itemView.findViewById(R.id.published_at_text_view);
            articleImageView = itemView.findViewById(R.id.article_image_view);
            sourceNameTextView = itemView.findViewById(R.id.source_name);

        }

        public void bind(final Article article, final OnItemClickListener listener, final int position) {
            titleTextView.setText(article.getTitle());

            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("d MMMM," + " в" + " HH:mm");
            String formattedDate = sdf.format(article.getPublishedAt());
            publishedAtTextView.setText(formattedDate);

            sourceNameTextView.setText(article.getSourceName());

            String articlePhotoUrl = article.getUrlToImage();

            if (articlePhotoUrl.isEmpty()) {
                articleImageView.setImageResource(R.drawable.no_image_placeholder);
            } else {
                Picasso.get().load(articlePhotoUrl).into(articleImageView);
            }


            articleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String url = article.getUrl();

                    listener.onClick(position);


                }
            });
        }
    }


    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticlesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticlesViewHolder holder, int position) {
        holder.bind(articlesList.get(position), listener, position);

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}