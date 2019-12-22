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

public class Istorija extends AppCompatActivity {

    ListView listView;
    private RequestQueue mQueue;
    TextView naslov;

    String mTittle[];
    String mDescription[];
    String mDatumOd[];
    String mDatumDo[];
    int images[];
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istorija);

        listView = findViewById(R.id.listView);
        naslov = findViewById(R.id.naslov);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            username =(String) b.get("UserName");

            naslov.setText("Username: " + username);
        }

        mQueue = Volley.newRequestQueue(this);

        jsonParse();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });




    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String rTittle[];
        String rDescription[];
        String rDatumOd[];
        String rDatumDo[];
        int rImgs[];

        MyAdapter(Context c,String tittle[],String description[],int imgs[],String datumOd[],String datumDo[]){
            super(c,R.layout.row,R.id.textView1,tittle);
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

            View row = layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTittle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);
            TextView myDatumOd = row.findViewById(R.id.textView3);
            TextView myDatumDo = row.findViewById(R.id.textView4);

            images.setImageResource(rImgs[position]);
            myTittle.setText("ID Kupovine: " + rTittle[position]);
            myDescription.setText("Datum Kupovine: " + rDescription[position]);
            myDatumOd.setText("Kupac: " + username);




            return row;
        }




    }




    private void jsonParse(){
        String url = "https://diplomskikupovinameda.000webhostapp.com/json.php?username=" + username;


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

                        String tip_sobe =employee.getString("id_brojac_kupovine");
                        String sprat = employee.getString("datum");
                        String datumOd = employee.getString("kolicina");
                        String datumDo = employee.getString("id_proizvod");

                        mTittle[i] = tip_sobe;
                        mDescription[i] = sprat;
                        mDatumOd[i] = datumOd;
                        mDatumDo[i] = datumDo;
                        images[i] = R.drawable.med;






                    }

                    MyAdapter adapter = new MyAdapter(Istorija.this,mTittle,mDescription,images,mDatumOd,mDatumDo);
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
