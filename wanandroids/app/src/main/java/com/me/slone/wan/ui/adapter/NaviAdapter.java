package com.me.slone.wan.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.me.slone.wan.R;
import com.me.slone.wan.base.MyAdapter;
import com.me.slone.wan.bean.Article;
import com.me.slone.wan.bean.Navi;
import com.me.slone.wan.ui.inter.TagNaviClickListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-21 上午10:39
 * Description:
 */
public class NaviAdapter extends MyAdapter<Navi> {

    private TagNaviClickListener tagNaviClickListener;

    public NaviAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleHolder();
    }

    final class ArticleHolder extends NaviAdapter.ViewHolder {

        @BindView(R.id.rv_tree_flow)
        TagFlowLayout tagFlowLayout;
        @BindView(R.id.rv_tree_tv_title)
        TextView tagTitle;


        public ArticleHolder() {
            super(R.layout.rv_navi_item);
        }

        @Override
        public void onBindView(int position) {
            Navi navi = getItem(position);
            tagTitle.setText(navi.getName());
            String[] tagNames = new String[navi.getArticles().size()];
            for (int i = 0; i < navi.getArticles().size(); i++) {
                tagNames[i] = navi.getArticles().get(i).getTitle();
            }
            tagFlowLayout.setAdapter(new TagAdapter<String>(tagNames) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tv_item, tagFlowLayout, false);
                    tv.setText(s);
                    return tv;
                }
            });
            tagFlowLayout.setOnTagClickListener((view, position1, parent) -> {
                if (tagNaviClickListener != null) {
                    //TODO remove
                    //Article article = getArticle(navi, tagNames[position1]);
                    //String link = article == null ? "" : article.getLink();
                    tagNaviClickListener.onNaviClicklink(navi.getArticles().get(position).getLink());
                }
                return false;
            });
        }
    }

    private Article getArticle(Navi navi, String title) {
        Article article = null;
        List<Article> articles = navi.getArticles();
        if (articles == null || articles.isEmpty()) {
            return null;
        }
        for (Article article1 : articles) {
            if (article1.getTitle().equals(title)) {
                article = article1;
                break;
            }
        }
        return article;
    }

    public void setTagNaviClickListener(TagNaviClickListener tagNaviClickListener) {
        this.tagNaviClickListener = tagNaviClickListener;
    }

    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        return linearLayoutManager;
    }
}
