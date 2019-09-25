package lk.my.sliit.it18106398.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResViewPromotionAdapter extends RecyclerView.Adapter<ResViewPromotionAdapter.myViewHolder> {
    private Context mContext;
    private ArrayList<ModelViewPromotion> mList;

    private ArrayList<String> promoNo;
    private ArrayList<String> name;
    private ArrayList<String> desc;
    private OnPromoClickListener mListener;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
   /* public ResViewPromotionAdapter(promo_list promo_list, ArrayList<Add_Promotions> promoList2) {
    }
*/
    public interface OnPromoClickListener{
        void onPromoClick(int position);
        //void onDeleteClick(int position);

    }

    public void setOnPromoClickListener(OnPromoClickListener listener){
        mListener = listener;
    }
    public ResViewPromotionAdapter(Context context, ArrayList<String> promoNo, ArrayList<String> name, ArrayList<String> desc) {
        mContext = context;
        this.promoNo = promoNo;
        this.name = name;
        this.desc = desc;
    }

    public ResViewPromotionAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View V = inflater.inflate(R.layout.restaurant_viewpromotion, parent, false);

        myViewHolder viewHolder = new myViewHolder(V, mContext, promoNo,name,desc);

        return viewHolder;
    }

    public void onBindViewHolder(myViewHolder holder, int position) {
        ImageView promoImage = holder.promo_img;
        TextView promoNumber = holder.promotionNo;
        TextView foodName = holder.food_name;
        TextView Description = holder.food_desc;


        promoNumber.setText(promoNo.get(position));
        foodName.setText(name.get(position));
        Description.setText(desc.get(position));
    }

    @Override
    public int getItemCount() {
        return promoNo.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener
        //ImageButton promo_img;
        ImageView promo_img;
        TextView promotionNo;
        TextView food_name;
        TextView food_desc;
        Button update_promo;
        Button delete_promo;

        Context mContext;
        ArrayList<ModelViewPromotion> mList;


        public myViewHolder(View itemView, Context context, final ArrayList<String> promoNo, ArrayList<String> name, ArrayList<String> desc) {
            super(itemView);

            promo_img = itemView.findViewById(R.id.promo_image);
            promotionNo  = itemView.findViewById(R.id.promo_text);
            food_name = itemView.findViewById(R.id.food_txt);
            food_desc = itemView.findViewById(R.id.desc_txt);
            update_promo = itemView.findViewById(R.id.btnpromo_update);
            delete_promo = itemView.findViewById(R.id.btnpromo_delete);

            itemView.setOnClickListener(this);
            //itemView.setOnCreateContextMenuListener(this);
            update_promo.setOnClickListener(this);
            delete_promo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Delete Clicked", Toast.LENGTH_SHORT).show();
                    db.child("PromotionTable").orderByChild("foodName").equalTo(food_name.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            });
            /*update_promo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onPromoClick(position);
                        }
                    }
                }
            });*/
//            delete_promo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //deletePromo(promotionNo);
//                    db.child("PromotionTable").addValueEventListener(new ValueEventListener(){
//
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if(dataSnapshot.hasChild("promo1")){
//                                db = FirebaseDatabase.getInstance().getReference().child("PromotionTable").child("promo1");
//                                for(DataSnapshot dss : dataSnapshot.getChildren()) {
//
//                                    String food = dss.child("foodName").getValue(String.class);
//                                    String des = dss.child("description").getValue(String.class);
//                                    String item = dss.child("itemNo").getValue(String.class);
//                                    String proNo = dss.child("promoNo").getValue(String.class);
//                                    String quanty= dss.child("qty").getValue(String.class);
//                                    Toast.makeText(mContext,"Deleted successfully.",Toast.LENGTH_SHORT).show();
//                                }
//
//                            }else{
//                                Toast.makeText(mContext,"No Source",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//            });

            mContext = context;
            //mList = list;
        }

        @Override
        public void onClick(View view) {
            //openAddPromotions();
            Intent intent = new Intent(mContext,Add_Promotions.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.putExtra("promoNo",mList.get(getAdapterPosition()).getPromoNumber());
            //intent.putExtra("foodName", mList.get(getAdapterPosition()).getFoodName());
            //intent.putExtra("description", mList.get(getAdapterPosition()).getDescription());
            if(mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    mListener.onPromoClick(position);
                }
            }
            mContext.startActivity(intent);
        }

        /*@Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (mListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    switch (menuItem.getItemId()){
                        case 1:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select Action");
            MenuItem delete = contextMenu.add(Menu.NONE, 1, 1, "Delete");

            delete.setOnMenuItemClickListener(this);
        }
*/
        /*private void deletePromo(String promoNo){
            DatabaseReference dbPT = FirebaseDatabase.getInstance().getReference("PromotionTable").child(promoNo);

            dbPT.removeValue();

            Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
        }*/
        /*private void openAddPromotions() {
            Intent intent = new Intent(mContext,Add_Promotions.class);

            mContext.startActivity(intent);
        }*/

    }
}