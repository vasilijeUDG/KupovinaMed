package com.example.kupovinameda;

import android.content.Intent;
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

public class pcelar_login extends AppCompatActivity {
private Button dugme;
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcelar_login);

        dugme = findViewById(R.id.dugme);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
LoginNovi();
            }
        });



    }

    private void LoginNovi(){


        String url = "https://diplomskikupovinameda.000webhostapp.com/LoginPcelar.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = Integer.parseInt(response.trim());

                if(id >=1){

                    Intent inte = new Intent(pcelar_login.this,pcelar_panel.class);
                    inte.putExtra("UserName",username.getText().toString().trim());
                    startActivity(inte);
                }else{
                    Toast.makeText(pcelar_login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(pcelar_login.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("UserName",username.getText().toString().trim());
                params.put("Password",password.getText().toString().trim());


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }



}
