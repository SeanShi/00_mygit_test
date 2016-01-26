package com.example.seanshi.android;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Created by Sean.Shi on 1/26/2016.
 */
public class TestVolley {
    public static void test(Activity activity)
    {
        new TestVolley().fun(activity);
    }

    public void fun(Activity activity)
    {
        new DownloadFilesTask(activity).execute();
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long>
    {
        private Activity _activity;

        public DownloadFilesTask(Activity activity)
        {
            _activity = activity;
        }

        protected Long doInBackground(URL... urls)
        {
            ImageRequest ir = new ImageRequest("https://amsintegration.iqmetrix.net/images/cd0e2138-f93b-4f64-86cd-e700b882ad40/preview/350/550",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        System.out.println("............["+response.getWidth()+"]  ["+response.getHeight()+"]");

                        saveImageToInternalStorage(response, _activity);
                }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(".... error ["+error.getMessage()+"]");
                    }
            });

            MySingleton.getInstance(_activity).addToRequestQueue(ir);
            return new Long(100);
        }

        protected void onProgressUpdate(Integer... progress) {
            System.out.println(progress[0]);
        }

        protected void onPostExecute(Long result) {
            System.out.println("Downloaded " + result + " bytes");
        }
    }

    public String saveImageToInternalStorage(Bitmap bitmapImage, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        // Create imageDir

        String filename = Environment.getExternalStorageDirectory() + "/receipt.png";
        File mypath = new File(filename);

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to
            // the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("file is saved ["+mypath.getPath()+"]");
        return mypath.getPath();
    }

}
