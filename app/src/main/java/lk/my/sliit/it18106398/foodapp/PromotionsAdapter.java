package lk.my.sliit.it18106398.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.myViewHolder> {
    private Context mContext;
    private ArrayList<String> name;
    //private ArrayList<ModelPromotions> mList;

    public PromotionsAdapter(Context Context, ArrayList<String> name) {
        mContext = Context;
        this.name = name;
    }

    public PromotionsAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.list_promotions, parent, false);

        myViewHolder viewHolder = new myViewHolder(v,mContext,name);
        return viewHolder;
    }
    public void onBindViewHolder(myViewHolder holder, int position) {
        //ModelOrder order = mList.get(position);

        ImageView promoImg = holder.promo_img;
        TextView promoName = holder.promo_txt;

        //orderImage.setImageResource(mList.get(position).getImage());

        promoName.setText(name.get(position));

    }
    @Override
    public int getItemCount() {

        return name.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView promo_img;
        TextView promo_txt;

        Context mContext;
        ArrayList<ModelPromotions> mList;

        public myViewHolder(@NonNull View itemView, Context context, ArrayList<String> name) {
            super(itemView);

            promo_img = itemView.findViewById(R.id.promoImage);
            promo_txt = itemView.findViewById(R.id.promoText);

            itemView.setOnClickListener(this);

            mContext = context;
            //mList = list;
        }

        public void onClick(View view) {
            Intent intent = new Intent(mContext,Promotion_ACTIVITY3.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.putExtra("image",mList.get(getAdapterPosition()).getPromoImage());
            intent.putExtra("name", mList.get(getAdapterPosition()).getPromoName());

            mContext.startActivity(intent);
        }
    }
}