package edu.tecii.android.appthreads;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    ProgressBar progressBar, progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //subProcess();
                TareaAsync tarea = new TareaAsync();
                tarea.execute();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TareaAsync2 tarea = new TareaAsync2();
                tarea.execute();
            }
        });
    }

    class TareaAsync extends AsyncTask<Integer, Integer, Void> {
        public TareaAsync() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        //Aqui van las tareas del hilo
        @Override
        protected Void doInBackground(Integer... integers) {

            for (int i=0; i<15; i++) {
                try {
                    Thread.sleep(1000);
                    progressBar.setProgress(i*10);
                    //update(i*10);
                } catch (Exception e) {}
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Tiempo de ejecucion", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return null;
        }
    }

    void update(int i) {
        progressBar.setProgress(i);
    }

    void subProcess() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<15; i++) {
                    try {
                        Thread.sleep(1000);
                        progressBar.setProgress(i*10);
                        //update(i*10);
                    } catch (Exception e) {}
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Tiempo de ejecucion", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    class TareaAsync2 extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar2.setBackgroundColor(Color.BLUE);
            progressBar2.setMax(100);
            progressBar2.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar2.setProgress(0);
            progressBar2.setBackgroundColor(Color.WHITE);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            for(int i=1; i<11;i++){
                try{
                    Thread.sleep(1000);
                    //progressBar2.setProgress(i*10);
                    update(i*10);
                } catch(Exception e) {}
            }
            return null;
        }
    }
    void update2 (int i) {
        progressBar2.setProgress(i);
    }
}
