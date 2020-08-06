package com.me.slone.wan.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyAdapter;
import com.me.slone.wan.bean.Article;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-8-6 下午6:42
 * Description: 内容适配器
 */
public class ContentAdapter extends MyAdapter<Article> {

    public ContentAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContentHolder();
    }



    final class ContentHolder extends ContentAdapter.ViewHolder {

        @BindView(R.id.rv_article_title)
        TextView title;
        @BindView(R.id.rv_article_author)
        TextView author;
        @BindView(R.id.rv_article_super_chapter)
        TextView superChapter;
        @BindView(R.id.rv_article_chapter)
        TextView chapter;
        @BindView(R.id.rv_article_date)
        TextView date;
        @BindView(R.id.rv_article_author_title)
        TextView authorName;
        @BindView(R.id.rv_article_chapter_title)
        TextView chapterName;
        @BindColor(R.color.white)
        int whiteBg;

        ContentHolder() {
            super(R.layout.rv_article_normal);
            authorName.setText("作者:");
            chapterName.setText("分类:");
        }

        @Override
        public void onBindView(int position) {
            Article article = getItem(position);
            title.setText(article.getTitle());
            author.setText(article.getAuthor());
            superChapter.setText(article.getSuperChapterName());
            chapter.setText("/"+article.getChapterName());
            date.setText(article.getNiceDate());

            title.setBackgroundColor(whiteBg);
            author.setBackgroundColor(whiteBg);
            superChapter.setBackgroundColor(whiteBg);
            chapterName.setBackgroundColor(whiteBg);
            authorName.setBackgroundColor(whiteBg);
            date.setBackgroundColor(whiteBg);
            chapter.setBackgroundColor(whiteBg);
        }
    }

    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        return linearLayoutManager;
    }

}
