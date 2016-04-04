package com.example.seanshi.teststorage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView _text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _text = (TextView) findViewById(R.id.text);

        String state = Environment.getExternalStorageState();

        _text.append("\n["+state+"]\n");

        System.out.println(".............["+state+"]");

        if (Environment.MEDIA_MOUNTED.equals(state)){
            String FileName = Environment.getExternalStorageDirectory() + File.separator + "iQmetrixPOSlog.txt";
            append(FileName);
        }
        else{
            System.out.println("false");
        }

        displayExternalFilesDirs(Environment.DIRECTORY_DOWNLOADS);
        displayExternalFilesDirs(Environment.DIRECTORY_DCIM);
        displayExternalFilesDirs("external");
        displayExternalFilesDirs(null);

        append("\n");
        String[] str = getExtSdCardPaths(this);
        for(String s : str){
            append(s);
        }

    }

    private void displayExternalFilesDirs(String type)
    {
        append("\n"+type+":");
        File[] dirs = getExternalFilesDirs(type);
        append(""+dirs.length);
        for(File dir : dirs){
            if(dir==null){
                append("dir is null");
                continue;
            }
            append("["+dir.getAbsolutePath()+"]    ["+dir.getName()+"]");
        }
    }

    private void append(String str)
    {
        _text.append(str+"\n");
        System.out.println(str);
    }

    /**
     * Get a list of external SD card paths. (Kitkat or higher.)
     *
     * @return A list of external SD card paths.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String[] getExtSdCardPaths(Context context) {
        List<String> paths = new ArrayList<String>();
        for (File file : context.getExternalFilesDirs("external")) {
            if (file != null && !file.equals(context.getExternalFilesDir("external"))) {
                int index = file.getAbsolutePath().lastIndexOf("/Android/data");
                if (index < 0) {
                    Log.w("AmazeFileUtils", "Unexpected external file dir: " + file.getAbsolutePath());
                }
                else {
                    String path = file.getAbsolutePath().substring(0, index);
                    try {
                        path = new File(path).getCanonicalPath();
                    }
                    catch (IOException e) {
                        // Keep non-canonical path.
                    }
                    paths.add(path);
                }
            }
        }
        if(paths.isEmpty())paths.add("/storage/sdcard1");
        return paths.toArray(new String[0]);
    }
}
