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

public class NewsItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<NewsItem> itemList = new ArrayList<>();
    private Context context;
    private int ViewType = 0; //0. 大图 1.小图左 2.小图右 3.loadMore

    public NewsItemAdapter(Context context){
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
            case 3:
                return new LoadMoreViewHolder(LayoutInflater.from(context)
                        .inflate(R.layout.load_more,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof NewsItemViewHolder){
            NewsItemViewHolder((NewsItemViewHolder) holder,position);
        }
    }



    public void NewsItemViewHolder(NewsItemViewHolder holder, int position) {

        NewsItem item = itemList.get(position);

        Uri uri = Uri.parse(item.thumbnailPics03);
        holder.sdv.setImageURI(uri);

        holder.date.setText(item.date);
        holder.author.setText(item.authorName);
        holder.title.setText(item.title);

    }

    @Override
    public int getItemCount() {

        return itemList.size() !=0 ? itemList.size()+1 :itemList.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(getItemCount() == position + 1){
            ViewType = 3;
        }else {
            ViewType = itemList.get(position).showType;
        }

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


    class LoadMoreViewHolder extends RecyclerView.ViewHolder{

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
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
