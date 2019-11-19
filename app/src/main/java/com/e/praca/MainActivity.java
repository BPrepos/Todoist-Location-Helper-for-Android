package com.e.praca;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    JsonHelper jh;
    String labelLink = "https://api.todoist.com/rest/v1/labels?token=7d152384d04c1126b48d96a173162edc81e67d01";

    Button add_data;
    EditText add_name;

    ListView labelList;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        listItem = new ArrayList<>();

        labelList = findViewById(R.id.labels_list);

        AsyncJsonLabelTask asyncJsonLabelTask = new AsyncJsonLabelTask(MainActivity.this);
        asyncJsonLabelTask.execute();

        labelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = labelList.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void viewData(){
        Cursor cursor = db.viewData();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            labelList.setAdapter(adapter);
        }
    }

    public class AsyncJsonLabelTask extends AsyncTask<Void,Void,Void>{


        Activity activity;


        public AsyncJsonLabelTask(Activity activity){
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            labelList = (ListView)findViewById(R.id.labels_list);
        }

        @Override
        protected Void doInBackground(Void... params) {
            String resultString = null;
            JsonHelper jhl = new JsonHelper();
            resultString = jhl.getJSON("https://api.todoist.com/rest/v1/labels?token=7d152384d04c1126b48d96a173162edc81e67d01");
            ArrayList<Label> arrayList= jhl.getLabelsFromJSONArray(resultString);
            db.addLabelArraytoDatabase(arrayList);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewData();
        }
    }
}
