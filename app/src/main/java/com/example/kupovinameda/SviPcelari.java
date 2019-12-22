package com.example.kupovinameda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SviPcelari extends AppCompatActivity {
private ImageView pcelar1,pcelar2,pcelar3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svi_pcelari);

        pcelar1 = findViewById(R.id.pcelar1);
        pcelar2 = findViewById(R.id.pcelar2);
        pcelar3 = findViewById(R.id.pcelar3);


        pcelar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(SviPcelari.this,pcelar.class);
                intent1.putExtra("ID","1");
                SviPcelari.this.startActivity(intent1);


            }
        });


        pcelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SviPcelari.this,pcelar.class);
                intent2.putExtra("ID","2");
                SviPcelari.this.startActivity(intent2);

            }
        });

        pcelar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(SviPcelari.this,pcelar.class);
                intent3.putExtra("ID","3");
                SviPcelari.this.startActivity(intent3);

            }
        });
    }
}
