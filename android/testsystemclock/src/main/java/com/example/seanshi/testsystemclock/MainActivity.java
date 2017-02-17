package com.example.seanshi.testsystemclock;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView _text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("...... "+ SystemClock.elapsedRealtime());

        _text = (TextView) findViewById(R.id.text);
        _text.setText("...... "+ SystemClock.elapsedRealtime()+"\n");

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                _text.append("...... "+ SystemClock.elapsedRealtime()+"\n");
            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
    }


    private void sleep(int ms)
    {
        try{
            Thread.sleep(ms);
        }catch(Exception ex){

        }
    }

    private class MyTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i = 0; i<10; i++) {
                publishProgress(i);
                sleep(1000);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            _text.append(""+progress[0]+"...... " + SystemClock.elapsedRealtime() + "\n");
        }


        @Override
        public void onPostExecute(Void result) {
            _text.append("...... " + SystemClock.elapsedRealtime() + "\n");
        }
    }
}
