package com.example.kupovinameda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class pcelar_panel extends AppCompatActivity {
private ImageView poruke,med,pcelar;
String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcelar_panel);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");


        }
        poruke = findViewById(R.id.poruke);

      pcelar = findViewById(R.id.pcelar);

      med = findViewById(R.id.med);

        poruke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(pcelar_panel.this,pcelar_poruke.class);
                intent2.putExtra("UserName",username);
                startActivity(intent2);
            }
        });


        pcelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(pcelar_panel.this,pcelar_informacije.class);
                intent4.putExtra("UserName",username);
                startActivity(intent4);
            }
        });


        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(pcelar_panel.this,pcelar_proizvod.class);
                intent3.putExtra("UserName",username);
                startActivity(intent3);
            }
        });
    }
}
