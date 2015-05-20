package com.tests.omar.androidsample;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tests.omar.androidsample.business.WSAccess;
import com.tests.omar.androidsample.entities.Info;
import com.tests.omar.androidsample.ui.CustomAdapter;
import com.tests.omar.androidsample.ui.DetailedInfoActivity;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView lvData;
    private String url = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";
    private Context context;
    private CustomAdapter adapter;
    private MyAsync myAsync;
    protected Bundle mSavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_main);

        context = this;
        lvData = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        myAsync = new MyAsync();
        myAsync.execute();

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Info obj = (Info)parent.getItemAtPosition(position);
                Intent Intent = new Intent(MainActivity.this, DetailedInfoActivity.class);
                Intent.putExtra("MAGNITUDE", obj.getMagnitude());
                Intent.putExtra("TIME", obj.getTime());
                Intent.putExtra("PLACE", obj.getPlace());
                Intent.putExtra("GEOMETRY", obj.getCoordinates());
                startActivity(Intent);
            }
        });
    }

    public class MyAsync extends AsyncTask<String,Void,Void>{

        String json;
        ArrayList resul;

        @Override
        protected Void doInBackground(String... params) {
            WSAccess ws = new WSAccess();
            json = ws.makeCall(url);
            if(json!=null){
                resul = ws.parseQuakeParse(json);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(resul != null){
                adapter = new CustomAdapter(context,resul);
                lvData.setAdapter(adapter);
            }
        }
    }

}
