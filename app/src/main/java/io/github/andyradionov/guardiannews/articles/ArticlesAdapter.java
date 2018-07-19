package io.github.andyradionov.guardiannews.articles;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.github.andyradionov.guardiannews.R;
import io.github.andyradionov.guardiannews.data.dto.Article;

/**
 * RecyclerView Adapter
 *
 * @author Andrey Radionov
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private static final String TAG = ArticlesAdapter.class.getSimpleName();

    private static final DateFormat dateFormat =
            new SimpleDateFormat("dd-MMM-yyyy, HH:mm", Locale.getDefault());
    private static final String SECTION_FORMAT = "%s (%s)";

    private List<Article> articles;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(String articleUrl);
    }

    public ArticlesAdapter(OnItemClickListener listener) {
        Log.d(TAG, "ArticlesAdapter constructor call");

        this.articles = Collections.emptyList();
        this.clickListener = listener;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");

        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article_card, parent, false);
        return new ArticleViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setData(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardView;

        ArticleViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            Log.d("ArticleViewHolder", "bind");

            Article article = articles.get(position);

            if (article.hasThumbNail()) {
                ImageView articleThumbnail = cardView.findViewById(R.id.iv_article_image);
                Picasso.with(cardView.getContext())
                        .load(article.getThumbnail())
                        .into(articleThumbnail);
            }

            TextView articleTitle = cardView.findViewById(R.id.tv_article_title);
            articleTitle.setText(article.getWebTitle());

            TextView articleSection = cardView.findViewById(R.id.tv_article_section);
            articleSection.setText(formatSectionAndDate(article));

            if (article.hasTrailText()) {
                TextView articleTrailText = cardView.findViewById(R.id.tv_article_trail_text);
                articleTrailText.setText(stripHtml(article.getTrailText()));
            }
        }

        @Override
        public void onClick(View view) {
            Log.d("ArticleViewHolder", "onClick");

            int adapterPosition = getAdapterPosition();
            String articleUrl = articles.get(adapterPosition).getWebUrl();

            clickListener.onItemClick(articleUrl);
        }

        private String formatSectionAndDate(Article article) {
            return String.format(
                    SECTION_FORMAT,
                    article.getSectionName(),
                    dateFormat.format(article.getWebPublicationDate()));
        }

        private String stripHtml(String html) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                return String.valueOf(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY));
            } else {
                return String.valueOf(Html.fromHtml(html));
            }
        }
    }
}
