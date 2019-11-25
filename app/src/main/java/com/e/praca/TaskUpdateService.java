package com.e.praca;

import android.app.IntentService;
import android.content.Intent;

import java.util.ArrayList;


public class TaskUpdateService extends IntentService {

    DatabaseHelper dh;

    public TaskUpdateService(DatabaseHelper dh)
    {
        super("TaskUpdateService");
        this.dh = dh;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

    public class AsyncTaskTask extends AsyncTask {

        private static final String TASKS_LINKS = "https://api.todoist.com/rest/v1/tasks?token=7d152384d04c1126b48d96a173162edc81e67d01";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String resultString = null;
            JsonHelper jhl = new JsonHelper();
            resultString = jhl.getJSON(TASKS_LINKS);
            ArrayList<Task> al = jhl.getTasksFromJSONArray(resultString);
            ArrayList<LabelTask> al2 = jhl.getLabelTaskFromJSONArray(resultString);
            dh.addTaskArraytoDatabase(al);
            dh.addTasksLabelsArraytoDatabase(al2);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
