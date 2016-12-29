package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qiao.rlj.myapplication.R;

import java.util.ArrayList;

import bean.NewsItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by renlijie on 16/12/28.
 */

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsItemViewHolder>{

    private ArrayList<NewsItem> itemList = new ArrayList<>();
    private Context context;
    private int ViewType = 0; //0. 大图 1.小图左 2.小图右

    public NewsItemAdapter(Context context){
        this.context = context;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (ViewType){
            case 0:
                return new NewsItemViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.news_item03,parent,false));
            case 1:
                return new NewsItemViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.news_item01,parent,false));
            case 2:
                return new NewsItemViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.news_item02,null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {

        NewsItem item = itemList.get(position);

        Uri uri = Uri.parse(item.thumbnailPics03);
        holder.sdv.setImageURI(uri);

        holder.date.setText(item.date);
        holder.author.setText(item.authorName);
        holder.title.setText(item.title);

    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {

        ViewType = itemList.get(position).showType;

        return ViewType;
    }

    /**
     * 添加数据集
     * @param arrayList
     */
    public void addItems(ArrayList<NewsItem> arrayList){
        itemList.addAll(arrayList);
        notifyDataSetChanged();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.sdv)
        SimpleDraweeView sdv;
        @BindView(R.id.news_data)
        TextView date;
        @BindView(R.id.news_author)
        TextView author;
        @BindView(R.id.news_title)
        TextView title;
        public NewsItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
