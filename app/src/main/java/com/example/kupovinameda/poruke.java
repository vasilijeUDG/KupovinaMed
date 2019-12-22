package com.example.kupovinameda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class poruke extends AppCompatActivity {
String username = "";
String proizvodjac = "";

int pozicija = 0;


private EditText proizvod,poruka;
private Button dugme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poruke);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");


        }

        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        proizvod = findViewById(R.id.proizvod);
        poruka = findViewById(R.id.poruka);


        dugme = findViewById(R.id.dugme);






        final Spinner dropdown = findViewById(R.id.spinner1);

        //create a list of items for the spinner.
         final String[] items = new String[]{"Vasilije Radunović", "Marko Marković", "Fedor Gregović"};
        final String[] items3 = new String[]{"1", "2", "3"};
        final String[] items2 = new String[]{"Livadski med", "Med", "Med"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);



        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String bb = items2[i];

               proizvod.setText(bb);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });



        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pozicija = dropdown.getSelectedItemPosition();

                proizvodjac = items3[pozicija];

                unesi();

            }
        });


    }

    public void unesi(){
        String url = "https://diplomskikupovinameda.000webhostapp.com/poruke.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.trim().equals("0")){
                    Toast.makeText(poruke.this, "Doslo je do greske kod Servera", Toast.LENGTH_SHORT).show();
                }
                else if(response.trim().equals("2")){
                    Toast.makeText(poruke.this, "Uspjesna ste unijeli poruku!", Toast.LENGTH_SHORT).show();

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(poruke.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Pcelar",proizvodjac);
                params.put("Poruka",poruka.getText().toString());
                params.put("UserName",username);


                return params;
            }
        };
        requestQueue.add(stringRequest);

    }




}
