package com.example.kupovinameda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shopping extends AppCompatActivity {
TableLayout tabela;
TableRow row1,row2,row3;
TextView kolicina1,kolicina2,kolicina3;
ImageView artikle1,artikle2,artikle3,ocistiSve;
String username;
Button izvrsi;
    ArrayList<String> lista2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);


         lista2=getIntent().getExtras().getStringArrayList("lista2");




        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");


        }

        tabela = findViewById(R.id.tabela);

kolicina1 = findViewById(R.id.kolicina1);
kolicina2 = findViewById(R.id.kolicina2);
kolicina3 = findViewById(R.id.kolicina3);

artikle1 = findViewById(R.id.artikle1);
artikle2 = findViewById(R.id.artikle2);
artikle3 = findViewById(R.id.artikle3);

row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);


        kolicina1.setText(lista2.get(0));
        kolicina2.setText(lista2.get(1));
        kolicina3.setText(lista2.get(2));

        izvrsi = findViewById(R.id.izvrsi);

        izvrsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lista2.get(0).equals("0") && lista2.get(1).equals("0") && lista2.get(2).equals("0")){
                    Toast.makeText(shopping.this, "Morate izabrati željeni med", Toast.LENGTH_SHORT).show();
                }else {
                    izvrsiKupovinu();

                }
            }
        });


        ocistiSve = findViewById(R.id.ocistiSve);

        ocistiSve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row1.setVisibility(View.GONE);
                lista2.set(0,"0");

                row2.setVisibility(View.GONE);
                lista2.set(1,"0");

                row3.setVisibility(View.GONE);
                lista2.set(2,"0");
            }
        });




artikle1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        row1.setVisibility(View.GONE);
        lista2.set(0,"0");

    }
});

artikle2.setOnClickListener(new View.OnClickListener() {
    @Override public void onClick(View v) {
        row2.setVisibility(View.GONE);
        lista2.set(1,"0");
            }
        });

artikle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row3.setVisibility(View.GONE);
                lista2.set(2,"0");
            }
        });



        if(lista2.get(0).equals("0")){
            row1.setVisibility(View.GONE);
        }

        if(lista2.get(1).equals("0")){
            row2.setVisibility(View.GONE);
        }

        if(lista2.get(2).equals("0")){
            row3.setVisibility(View.GONE);
        }






    }



    private void izvrsiKupovinu(){


        String url = "http://diplomskikupovinameda.000webhostapp.com/izvrsiKupovinu.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("0") || response.equals("00") || response.equals("000")){
                    Toast.makeText(shopping.this, "Opss...Došlo je do greške", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(shopping.this, "Uspješna kupovina!!!!", Toast.LENGTH_SHORT).show();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(shopping.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("kupac",username);


                params.put("kolicina1", lista2.get(0));


                params.put("kolicina2",lista2.get(1));


                params.put("kolicina3",lista2.get(2));



                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
