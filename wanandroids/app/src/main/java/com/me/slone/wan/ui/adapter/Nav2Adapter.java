package com.me.slone.wan.ui.adapter;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.me.slone.wan.R;
import com.me.slone.wan.bean.Navi;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

/**
 * Author：diankun
 * Time：20-7-23 下午4:05
 * Description:
 */
public class Nav2Adapter extends BaseQuickAdapter<Navi, BaseViewHolder> {

    @BindView(R.id.rv_tree_flow)
    TagFlowLayout mFlowLayout;


    public Nav2Adapter(int layoutResId, @Nullable List<Navi> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, @NonNull Navi item) {
        ButterKnife.bind(this, helper.itemView);
        if (item.getName() != null) {
            helper.setText(R.id.rv_tree_tv_title, item.getName());
        }
        if (item.getArticles() != null) {
            String[] titleName = new String[item.getArticles().size()];
            for (int i = 0; i < item.getArticles().size(); i++) {
                titleName[i] = item.getArticles().get(i).getTitle();
            }
            final LayoutInflater mInflater = LayoutInflater.from(helper.itemView.getContext());
//            mFlowLayout.setAdapter(new TagAdapter<String>(titleName) {
//                @Override
//                public View getView(FlowLayout parent, int position, String s) {
//                    TextView tv = (TextView) mInflater.inflate(R.layout.flow_tv_item,
//                            mFlowLayout, false);
//                    tv.setText(s);
//                    return tv;
//                }
//            });
//            mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//                @Override
//                public boolean onTagClick(View view, int position, FlowLayout parent) {
//                    flowTagCallBack.getTreeLink(item.getArticles().get(position).getLink());
//                    return true;
//                }
//            });
        }


    }

}
