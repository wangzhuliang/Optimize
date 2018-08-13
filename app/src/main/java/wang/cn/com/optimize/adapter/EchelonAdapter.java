package wang.cn.com.optimize.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import wang.cn.com.optimize.R;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-13
 * @time: 17:30
 */
public class EchelonAdapter extends RecyclerView.Adapter<EchelonAdapter.ViewHolder>{

    private Context mContext;
    private int[] icons;
    private int[] bgs;
    private String[] nickeNames;
    private String[] descs;

    public EchelonAdapter(Context context, int[] icons, int[] bgs, String[] nickNames,
                          String[] descs){
        mContext = context;
        this.icons = icons;
        this.bgs = bgs;
        this.nickeNames = nickNames;
        this.descs = descs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_echelon,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.icon.setImageResource(icons[position%4]);
        holder.nickName.setText(nickeNames[position%4]);
        holder.desc.setText(descs[position%4]);
        holder.bg.setImageResource(bgs[position%4]);
    }

    @Override
    public int getItemCount() {
        return 60;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        ImageView bg;
        TextView nickName;
        TextView desc;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.img_icon);
            bg = itemView.findViewById(R.id.img_bg);
            nickName = itemView.findViewById(R.id.tv_nickname);
            desc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
