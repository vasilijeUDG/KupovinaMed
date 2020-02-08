package com.example.kupovinameda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pcelar_proizvod extends AppCompatActivity {
String username = "";
private EditText naziv,cijena,lokacija;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcelar_proizvod);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");


        }

        naziv = findViewById(R.id.naziv);
        cijena = findViewById(R.id.cijena);
        lokacija = findViewById(R.id.lokacija);


        Donesi();


    }


    private void Donesi(){


        String url = "https://diplomskikupovinameda.000webhostapp.com/DonesiProizvod.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                List<String> list = new ArrayList<String>(Arrays.asList(response.split(",")));
               naziv.setText(list.get(0));
                cijena.setText(list.get(1));
                lokacija.setText(list.get(2));



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(pcelar_proizvod.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("ID",username);


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
