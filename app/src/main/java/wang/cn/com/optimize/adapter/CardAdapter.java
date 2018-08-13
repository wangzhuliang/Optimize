package wang.cn.com.optimize.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import wang.cn.com.optimize.R;
import wang.cn.com.optimize.bean.CardBean;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-07-17
 * @time: 16:55
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder>{

    private List<CardBean> mCardBeanList;
    private RequestOptions mRequestOptions;

    public CardAdapter(List<CardBean> cardBeanList) {
        mCardBeanList = cardBeanList;
        mRequestOptions = new RequestOptions();
        mRequestOptions.placeholder(R.mipmap.card_default_film_bg).
                error(R.mipmap.card_default_film_bg).diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_tan_tan_txt, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardHolder holder, int position) {
        final CardBean bean = mCardBeanList.get(position);
        Glide.with(holder.itemView).load(bean.getUrl()).apply(mRequestOptions).into(holder.img);
        holder.text.setText(bean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "click " + bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"点击了 img",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardBeanList.size();
    }

    class CardHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;

        public CardHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.card_img);
            text = itemView.findViewById(R.id.card_txt);
        }
    }
}
