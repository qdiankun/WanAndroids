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
import com.me.slone.wan.bean.Tree;
import com.me.slone.wan.ui.inter.TagTreeClickListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;

/**
 * Author：diankun
 * Time：20-7-23 下午4:59
 * Description:
 */
public class TreeAdapter extends MyAdapter<Tree> {

    private TagTreeClickListener tagTreeClickListener;

    public TreeAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TreeHolder();
    }

    final class TreeHolder extends TreeAdapter.ViewHolder {

        @BindView(R.id.rv_tree_flow)
        TagFlowLayout tagFlowLayout;
        @BindView(R.id.rv_tree_tv_title)
        TextView tagTitle;

        public TreeHolder() {
            super(R.layout.rv_tree_item);
        }

        @Override
        public void onBindView(int position) {
            Tree tree = getItem(position);
            tagTitle.setText(tree.getName());
            String[] tagName = new String[tree.getChildren().size()];
            for (int i = 0; i < tree.getChildren().size(); i++) {
                tagName[i] = tree.getChildren().get(i).getName();
            }
            tagFlowLayout.setAdapter(new TagAdapter<String>(tagName) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_tv_item, tagFlowLayout, false);
                    tv.setText(s);
                    return tv;
                }
            });
            tagFlowLayout.setOnTagClickListener((view, position1, parent) -> {
                if(tagTreeClickListener!=null){
                    tagTreeClickListener.onTreeClickListener(tree);
                }
                return false;
            });
        }
    }

    public void setTagTreeClickListener(TagTreeClickListener tagTreeClickListener) {
        this.tagTreeClickListener = tagTreeClickListener;
    }

    @Override
    protected RecyclerView.LayoutManager generateDefaultLayoutManager(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        return linearLayoutManager;
    }
}
