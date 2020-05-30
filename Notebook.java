package net.mobilcoder.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Notebook extends AppCompatActivity {
    public static  final String DEBUGTAG="JUP";
    public static  final String TEXTFILE="notesquirrel.txt";
    public static  final String FILESAVED="fileSaved";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);
        addSaveButtonListener();

        SharedPreferences prefs=getPreferences(MODE_PRIVATE);
        boolean fileSaved=prefs.getBoolean(FILESAVED,false);
        if (fileSaved) {
            loadSavedFile();
        }
    }

    private void loadSavedFile(){
        try {
            FileInputStream fis =  openFileInput(TEXTFILE);
            BufferedReader reader=new BufferedReader(
                    new InputStreamReader(new DataInputStream(fis)));
            EditText editText=findViewById(R.id.text);
            String yazi;
            while (null != (yazi = reader.readLine()))
            {
                editText.append(yazi);
                editText.append("\n");
            }
            fis.close();
        } catch (Exception e) {
            Log.d(DEBUGTAG,"Unable to save file");
        }
    }

    private void addSaveButtonListener(){
        Button saveBtn=(Button) findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                EditText editText=findViewById(R.id.text);
                String text= editText.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(TEXTFILE, Context.MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.close();

                    SharedPreferences prefs =getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor= prefs.edit();
                    editor.putBoolean(FILESAVED,true);
                    editor.commit();


                    Toast.makeText(Notebook.this,getString
                            (R.string.toast_cont_save),Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.d(DEBUGTAG,"Dosya kaydedilemiyor");

                }



            }

        });
    }
}