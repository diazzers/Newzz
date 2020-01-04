package com.diazzerss.newsapp.java.article;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.diazzerss.newsapp.java.OnItemClickListener;
import com.diazzerss.newsapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private OnItemClickListener listener;
    private Context context;

    private ArrayList<Article_> articlesList = new ArrayList<>();

    public void addData(ArrayList<Article_> articlesList) {
        this.articlesList.clear();
        this.articlesList.addAll(articlesList);
        notifyDataSetChanged();
    }

    public void clearData() {
        articlesList.clear();
        notifyDataSetChanged();
    }

    public void addOnClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView publishedAtTextView;
        TextView sourceNameTextView;
        ImageView articleImageView;
        ImageView shareButton;
        ImageView bookmarkButton;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            publishedAtTextView = itemView.findViewById(R.id.published_at_text_view);
            articleImageView = itemView.findViewById(R.id.article_image_view);
            sourceNameTextView = itemView.findViewById(R.id.source_name);
            shareButton = itemView.findViewById(R.id.share_btn);
            bookmarkButton = itemView.findViewById(R.id.bookmark_btn);


        }

        public void bind(final Article_ article, final OnItemClickListener listener, final int position) {
            titleTextView.setText(article.getTitle());

            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("d MMM HH:mm");
            String formattedDate = sdf.format(article.getPublishedAt());
            publishedAtTextView.setText(formattedDate);

            sourceNameTextView.setText(article.getSourceName().sourceName);

            String articlePhotoUrl = article.getUrlToImage();

            if (articlePhotoUrl != null && !articlePhotoUrl.isEmpty()) {
                Picasso.get().load(articlePhotoUrl).placeholder(R.drawable.no_image_placeholder_24dp).into(articleImageView);
            } else {
                articleImageView.setImageResource(R.drawable.no_image_placeholder_24dp);
            }


            articleImageView.setOnClickListener(v -> listener.onClick(position));

            shareButton.setOnClickListener(v -> {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
                context.startActivity(Intent.createChooser(sendIntent, "Поделиться"));
            });
            bookmarkButton.setOnClickListener(v -> Toast.makeText(context, "Сохранено:)", Toast.LENGTH_SHORT).show());

        }
    }


    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(articlesList.get(position), listener, position);

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}