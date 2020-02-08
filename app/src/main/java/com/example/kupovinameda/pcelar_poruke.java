package com.example.kupovinameda;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pcelar_poruke extends AppCompatActivity {
    private Button pcelari;
    private RequestQueue mQueue;
    ListView listView;
    String mTittle[];
    String mDescription[];
    String mDatumOd[];
    String mDatumDo[];
    int images[];
    String username = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcelar_poruke);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");


        }

        listView = findViewById(R.id.listView);

pcelari = findViewById(R.id.pcelari);

pcelari.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent2 = new Intent(pcelar_poruke.this,pcelar_pozivi.class);
        intent2.putExtra("UserName",username);
        startActivity(intent2);
    }
});



        mQueue = Volley.newRequestQueue(this);

        jsonParse();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(pcelar_poruke.this,mTittle[position],Toast.LENGTH_SHORT).show();
            }
        });


    }



    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTittle[];
        String rDescription[];
        String rDatumOd[];
        String rDatumDo[];
        int rImgs[];

        MyAdapter(Context c,String tittle[],String description[],int imgs[],String datumOd[],String datumDo[]){
            super(c,R.layout.red,R.id.textView1,tittle);
            this.context = c;
            this.rTittle = tittle;
            this.rDescription = description;
            this.rImgs = imgs;
            this.rDatumOd = datumOd;
            this.rDatumDo = datumDo;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View red = layoutInflater.inflate(R.layout.red,parent,false);
            ImageView images = red.findViewById(R.id.image);
            TextView myTittle = red.findViewById(R.id.textView1);
            TextView myDescription = red.findViewById(R.id.textView2);
            TextView myDatumOd = red.findViewById(R.id.textView3);
            TextView myDatumDo = red.findViewById(R.id.textView4);

            images.setImageResource(rImgs[position]);
            myTittle.setText(rTittle[position]);
            myDescription.setText(rDescription[position]);





            return red;
        }




    }





    private void jsonParse(){
        String url = "https://diplomskikupovinameda.000webhostapp.com/jsonPcelar.php?username=" + username;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("rezervacije");

                    int brojac = jsonArray.length();

                    mTittle = new String[brojac];
                    mDescription = new String[brojac];
                    mDatumOd = new String[brojac];
                    mDatumDo = new String[brojac];
                    images = new int[brojac];


                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject employee = jsonArray.getJSONObject(i);

                        String tip_sobe =employee.getString("ime");
                        String sprat = employee.getString("poruka");
                        String datumOd = employee.getString("datum_poruke");
                        String datumDo = employee.getString("poruka");

                        mTittle[i] = tip_sobe;
                        mDescription[i] = sprat;
                        mDatumOd[i] = datumOd;
                        mDatumDo[i] = datumDo;
                        images[i] = R.drawable.user;






                    }

                   pcelar_poruke.MyAdapter adapter = new pcelar_poruke.MyAdapter(pcelar_poruke.this,mTittle,mDescription,images,mDatumOd,mDatumDo);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);



    }





}