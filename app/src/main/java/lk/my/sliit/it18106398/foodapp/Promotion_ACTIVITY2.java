package lk.my.sliit.it18106398.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.time.Instant;
import java.util.ArrayList;

public class Promotion_ACTIVITY2 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelViewPromotion> resViewpromotions;
    //Button btn_promo_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion__activity2);



        recyclerView = findViewById(R.id.promoRecyclerView);

        resViewpromotions = new ArrayList<>();

        resViewpromotions.add(new ModelViewPromotion(R.drawable.pic1, "Promo 1"));
        resViewpromotions.add(new ModelViewPromotion(R.drawable.img6, "Promo 2"));
        resViewpromotions.add(new ModelViewPromotion(R.drawable.img7, "Promo 3"));
        resViewpromotions.add(new ModelViewPromotion(R.drawable.img10, "Promo 4"));
        resViewpromotions.add(new ModelViewPromotion(R.drawable.img11, "Promo 5"));

        ResViewPromotionAdapter adapter = new ResViewPromotionAdapter(this, resViewpromotions);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //btn_promo_update = (Button) findViewById(R.id.btnpromo_update);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        btn_promo_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Promotion_ACTIVITY2.this, Add_Promotions.class);
//                startActivity(intent);
//            }
//        });
    }
}
