package com.e.praca;


import java.util.ArrayList;

public class AsyncTask extends android.os.AsyncTask<Void,Void,Void> {

    private static final String TASKS_LINKS = "https://api.todoist.com/rest/v1/tasks?token=7d152384d04c1126b48d96a173162edc81e67d01";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}

