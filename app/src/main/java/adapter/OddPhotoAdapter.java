package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiao.rlj.myapplication.R;

import java.util.ArrayList;

import bean.OddPhoto;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by renlijie on 17/1/3.
 */

public class OddPhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    final int ITEM_VIEW = 0;
    final int FOOTER_VIEW = 1;
    Context context;
    ArrayList<OddPhoto> oddPhotos = new ArrayList<>();


    public OddPhotoAdapter(Context context){

        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case FOOTER_VIEW:
                return new LoadMoreViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.load_more,parent,false));
            case ITEM_VIEW:
                return new OddPhotoViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.fragment_odd_photo_item,parent,false));
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OddPhotoViewHolder){
            OddPhoto oddPhoto = oddPhotos.get(position);
            ((OddPhotoViewHolder) holder).publicTime.setText(oddPhoto.updatetime);
            ((OddPhotoViewHolder) holder).title.setText(oddPhoto.content);
            if(oddPhoto.url.endsWith(".gif")){
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(Uri.parse(oddPhoto.url))
                        .build();
                ((OddPhotoViewHolder) holder).sdv.setController(controller);
            }else{
                ((OddPhotoViewHolder) holder).sdv.setImageURI(oddPhoto.url);
                PhotoViewAttacher attacher = new PhotoViewAttacher(((OddPhotoViewHolder) holder).sdv);
                attacher.update();

            }

        }

    }

    @Override
    public int getItemCount() {
        return oddPhotos.size() == 0 ?0:oddPhotos.size()+1;
    }

    /**
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if(position + 1 == getItemCount()){
            return FOOTER_VIEW;
        }else {
            return ITEM_VIEW;
        }
    }

    /**
     * 添加数据
     * @param oddPhotos
     */
    public void addItems(ArrayList<OddPhoto> oddPhotos){
        boolean b = this.oddPhotos.removeAll(oddPhotos);
        this.oddPhotos.addAll(oddPhotos);
        notifyDataSetChanged();

    }

    /**
     * footerView
     */
    class LoadMoreViewHolder extends RecyclerView.ViewHolder{

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * itemView
     */
    class OddPhotoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.odd_photo_title)
        TextView title;
        @BindView(R.id.odd_photo_sdv)
        SimpleDraweeView sdv;
        @BindView(R.id.publish_time)
        TextView publicTime;

        public OddPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
