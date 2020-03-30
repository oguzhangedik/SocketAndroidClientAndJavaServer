package com.example.androidsocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SocketToPc().execute(editText.getText().toString());
            }
        });
    }

    class SocketToPc extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Socket socket;
            InputStreamReader isr;
            BufferedReader bufferedReader;
            PrintWriter printWriter;
            String result;
            try {
                //server socket'in bulunduğu makinanın ip si ve server socket'in açıldığı port bilgisi
                String SOCKET_SERVER_LOCAL_PC_ADRES = "192.168.1.5";
                int SOCKET_SERVER_OPENNED_PORT = 5555;
                socket = new Socket(SOCKET_SERVER_LOCAL_PC_ADRES,SOCKET_SERVER_OPENNED_PORT);
                isr = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(isr);
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(strings[0]);
                printWriter.flush();

                result = bufferedReader.readLine();

                socket.close();
                isr.close();
                bufferedReader.close();
                printWriter.close();

                return result;

            } catch (Exception exc) {
                exc.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }

}
