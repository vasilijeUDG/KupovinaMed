package com.example.kupovinameda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class kupovinaMeda extends AppCompatActivity {
private Button dugme1,dugme2,dugme11,dugme22,dugme111,dugme222,dodaj1,dodaj2,dodaj3;
private ImageView kupovina;
private TextView tekst1,tekst2,tekst3;
    ArrayList<String> lista1 = new ArrayList<String>();
    ArrayList<String> lista2 = new ArrayList<String>();
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupovina_meda);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");


        }

        lista1.add("1");
        lista1.add("2");
       lista1.add("3");


        lista2.add("0");
        lista2.add("0");
        lista2.add("0");


        dugme1 = findViewById(R.id.dugme1);
        dugme2 = findViewById(R.id.dugme2);

        dugme11 = findViewById(R.id.dugme11);
        dugme22 = findViewById(R.id.dugme22);

        dugme111 = findViewById(R.id.dugme111);
        dugme222 = findViewById(R.id.dugme222);

        tekst1 = findViewById(R.id.tekst1);
        tekst2 = findViewById(R.id.tekst2);
        tekst3 = findViewById(R.id.tekst3);

        dodaj1 = findViewById(R.id.dodaj1);
        dodaj2 = findViewById(R.id.dodaj2);
        dodaj3 = findViewById(R.id.dodaj3);

        kupovina = findViewById(R.id.kupovina);

        kupovina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(kupovinaMeda.this,shopping.class);
                intent.putStringArrayListExtra("lista2",lista2);
                intent.putExtra("UserName",username);
                kupovinaMeda.this.startActivity(intent);
            }
        });


        dodaj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = tekst1.getText().toString();
                if(h.equals("0")){
                    Toast.makeText(kupovinaMeda.this, "Morate izabrati količinu", Toast.LENGTH_SHORT).show();
                }else{
                    lista2.set(0, h);
                    Toast.makeText(kupovinaMeda.this, "Usjpešno dodato u listu " + h + " kg", Toast.LENGTH_SHORT).show();
                }
            }
        });



        dodaj2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = tekst2.getText().toString();
                if(h.equals("0")){
                    Toast.makeText(kupovinaMeda.this, "Morate izabrati količinu", Toast.LENGTH_SHORT).show();
                }else{
                    lista2.set(1, h);
                    Toast.makeText(kupovinaMeda.this, "Usjpešno dodato u listu " + h + " kg", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dodaj3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h = tekst3.getText().toString();
                if(h.equals("0")){
                    Toast.makeText(kupovinaMeda.this, "Morate izabrati količinu", Toast.LENGTH_SHORT).show();
                }else{
                    lista2.set(2, h);
                    Toast.makeText(kupovinaMeda.this, "Usjpešno dodato u listu " + h + " kg", Toast.LENGTH_SHORT).show();
                }
            }
        });







        dugme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stanje = tekst1.getText().toString();
               int broj = Integer.parseInt(stanje);

               if(broj == 0){

               }else{
                   int novi = broj - 1;
                   String stanjeNovo = Integer.toString(novi);
                   tekst1.setText(stanjeNovo);
               }
            }
        });


        dugme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stanje = tekst1.getText().toString();
                int broj = Integer.parseInt(stanje);


                    int novi = broj + 1;
                    String stanjeNovo = Integer.toString(novi);
                    tekst1.setText(stanjeNovo);

            }
        });


        dugme11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stanje = tekst2.getText().toString();
                int broj = Integer.parseInt(stanje);

                if(broj == 0){

                }else{
                    int novi = broj - 1;
                    String stanjeNovo = Integer.toString(novi);
                    tekst2.setText(stanjeNovo);
                }
            }
        });


        dugme22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stanje = tekst2.getText().toString();
                int broj = Integer.parseInt(stanje);


                    int novi = broj + 1;
                    String stanjeNovo = Integer.toString(novi);
                    tekst2.setText(stanjeNovo);

            }
        });


        dugme111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stanje = tekst3.getText().toString();
                int broj = Integer.parseInt(stanje);

                if(broj == 0){

                }else{
                    int novi = broj - 1;
                    String stanjeNovo = Integer.toString(novi);
                    tekst3.setText(stanjeNovo);
                }
            }
        });

        dugme222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stanje = tekst3.getText().toString();
                int broj = Integer.parseInt(stanje);


                    int novi = broj + 1;
                    String stanjeNovo = Integer.toString(novi);
                    tekst3.setText(stanjeNovo);

            }
        });


    }
}
