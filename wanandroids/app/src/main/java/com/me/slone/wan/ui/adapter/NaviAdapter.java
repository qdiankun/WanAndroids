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
import com.me.slone.wan.bean.Navi;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-21 上午10:39
 * Description:
 */
public class NaviAdapter extends MyAdapter<Navi> {

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
            super(R.layout.rv_tree_item);
        }

        @Override
        public void onBindView(int position) {
            Navi navi = getItem(position);
            tagTitle.setText(navi.getName());
            String[] tagName = new String[navi.getArticles().size()];
            for (int i = 0; i < navi.getArticles().size(); i++) {
                tagName[i] = navi.getArticles().get(i).getTitle();
            }
//            tagFlowLayout.setAdapter(new TagAdapter<String>(tagName) {
//                @Override
//                public View getView(FlowLayout parent, int position, String s) {
//                    TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tv_item, tagFlowLayout, false);
//                    tv.setText(s);
//                    return tv;
//                }
//            });
            tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    return false;
                }
            });
        }
    }

    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        return linearLayoutManager;
    }
}
