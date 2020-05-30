package net.mobilcoder.notebook;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public static EditText username;
    public static EditText password;
    public static Button login;
    public static Button kayit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            activity_main.xml dosyasindaki gerekli bilesenleri burada cekip atamalarini yapiyorum.

         */

        username = (EditText) findViewById(R.id.editusername);
        password = (EditText) findViewById(R.id.editpassword);
        login    = (Button)   findViewById(R.id.login);
        kayit=(Button)findViewById(R.id.kayit);
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kayitOl=new Intent(MainActivity.this,RecordActivity.class);
                MainActivity.this.startActivity(kayitOl);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ad = username.getText().toString();
                String sifre = password.getText().toString();

                    //Yeni activity'e gecis saglansin
                    Intent newInt = new Intent(MainActivity.this, Notebook.class);

                    MainActivity.this.startActivity(newInt);

            }
        });

    }
}
