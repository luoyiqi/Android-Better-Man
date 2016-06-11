package net.liang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.liang.R;
import net.liang.base.BaseRecyclerAdapter;
import net.liang.bean.News;

/**
 * Created by lenovo on 2016/6/7.
 * 资讯适配器
 */
public class NewsAdapter extends BaseRecyclerAdapter<News> implements View.OnClickListener {
    Context context;

    public NewsAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.list_item_news,parent,false);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Holder holder = (Holder) viewHolder;

        holder.tv_title.setText(getItemData(position).getTitle());
        holder.tv_body.setText(getItemData(position).getBody());
    }

    @Override
    public void onClick(View v) {

    }

    private class Holder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_body;
        public Holder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_body = (TextView) view.findViewById(R.id.tv_body);
        }
    }
}
