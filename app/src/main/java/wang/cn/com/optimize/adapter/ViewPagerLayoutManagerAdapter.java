package wang.cn.com.optimize.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import wang.cn.com.optimize.R;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-16
 * @time: 14:55
 */
public class ViewPagerLayoutManagerAdapter extends RecyclerView.Adapter<ViewPagerLayoutManagerAdapter.ViewHolder>{

    private int[] imgs;
    private int[] videos;
    private Context context;

    public ViewPagerLayoutManagerAdapter(Context context, int[] imgs, int[] videos){
        this.context = context;
        this.imgs = imgs;
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_view_pager_layout_manager, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.img_thumb.setImageResource(imgs[position%2]);
        holder.videoView.setVideoURI(Uri.parse("android.resource://"+context.getPackageName()+"/"+ videos[position%2]));
    }

    @Override
    public int getItemCount() {
        return imgs.length*10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_thumb;
        VideoView videoView;
        ImageView img_play;
        RelativeLayout rootView;
        public ViewHolder(View itemView) {
            super(itemView);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            videoView = itemView.findViewById(R.id.video_view);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);
        }
    }
}
