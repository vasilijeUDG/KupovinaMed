package com.example.kupovinameda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registracija extends AppCompatActivity {
private EditText ime,prezime,adresa,broj_telefona,username,password;
private Button dugme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        ime = findViewById(R.id.ime);
        prezime = findViewById(R.id.prezime);
        adresa = findViewById(R.id.adresa);
        broj_telefona = findViewById(R.id.broj_telefona);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        dugme = findViewById(R.id.dugme);

        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String a =  provjera();
               if(a.equals("1")) {
                   Registruj();
               }else{
                   Toast.makeText(registracija.this, "Morate popuniti sva polja", Toast.LENGTH_SHORT).show();
               }
            }
        });








    }



    public String provjera(){
        String NewIme = ime.getText().toString();
        String NewPrezime = prezime.getText().toString();
        String NewUsername = username.getText().toString();
        String NewPassword = password.getText().toString();
        String NewAdresa = adresa.getText().toString();
        String NewBroj = broj_telefona.getText().toString();

        if(NewIme.length() >=4 && NewPrezime.length() >=4 && NewUsername.length() >=4 && NewPassword.length() >=4 && NewAdresa.length() >=4 && NewBroj.length() >=4){
return "1";
        }else{

            return "0";
        }

    }



    public void Registruj(){
        String url = "https://diplomskikupovinameda.000webhostapp.com/Register.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.trim().equals("0")){
                    Toast.makeText(registracija.this, "Doslo je do greske kod Servera", Toast.LENGTH_SHORT).show();
                }else if(response.trim().equals("1")){
                    Toast.makeText(registracija.this, "Vec postoji taj username u bazi", Toast.LENGTH_SHORT).show();
                }else if(response.trim().equals("2")){
                    Toast.makeText(registracija.this, "Uspjesna registracija!", Toast.LENGTH_SHORT).show();
                    ime.setText("");
                    prezime.setText("");
                    username.setText("");
                    password.setText("");
                    adresa.setText("");
                    broj_telefona.setText("");
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(registracija.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Ime",ime.getText().toString().trim());
                params.put("Prezime",prezime.getText().toString().trim());
                params.put("UserName",username.getText().toString().trim());
                params.put("Password",password.getText().toString().trim());
                params.put("Adresa",adresa.getText().toString().trim());
                params.put("Broj_telefona",broj_telefona.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }
}
