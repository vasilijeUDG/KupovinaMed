package com.example.kupovinameda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
private Button pcelari,history,poruka,kupovinaMeda;
String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");


        }

        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();



        pcelari = findViewById(R.id.pcelari);
        history = findViewById(R.id.history);
        poruka = findViewById(R.id.poruka);
        kupovinaMeda = findViewById(R.id.kupovinaMeda);


        pcelari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,SviPcelari.class);
                Menu.this.startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt = new Intent(Menu.this,Istorija.class);
                intentt.putExtra("UserName",username);
                Menu.this.startActivity(intentt);
            }
        });


        poruka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPoruka = new Intent(Menu.this,poruke.class);
                intentPoruka.putExtra("UserName",username);
                Menu.this.startActivity(intentPoruka);
            }
        });


        kupovinaMeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKupovina = new Intent(Menu.this,kupovinaMeda.class);
                intentKupovina.putExtra("UserName",username);
                Menu.this.startActivity(intentKupovina);
            }
        });


    }
}
