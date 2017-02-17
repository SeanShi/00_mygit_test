package com.example.seanshi.testbitmp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private final int LABEL_WIDTH = 765;
    private final int LABEL_HEIGHT = 350;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.receipt);
        System.out.println("bm.width "+bitmap.getWidth()+"    bm.height "+bitmap.getHeight());


        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        System.out.println("      bitmap.width "+bitmap.getWidth()+"    bitmap.height "+bitmap.getHeight());

        if(LABEL_HEIGHT - height <50){
            int newHeight = LABEL_HEIGHT - 50;
            width = width * newHeight / height;
            height = newHeight;

            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
/*                    Matrix m = new Matrix();
                    m.setRectToRect(new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight()), new RectF(0, 0, width, height), Matrix.ScaleToFit.CENTER);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                    */
        }
        int leftTopX = (LABEL_WIDTH-width)/2;
        int leftTopY = (LABEL_HEIGHT-height)/2;

        int rightBottomX = leftTopX + width;
        int rightBottomY = leftTopY + height;

        System.out.println("leftTopX  "+leftTopX+"   leftTopY "+leftTopY+"      rightBottomX "+rightBottomX+"    rightBottomY "+rightBottomY);
        System.out.println("      bitmap.width "+bitmap.getWidth()+"    bitmap.height "+bitmap.getHeight());

        WriteBitmapToFile(bitmap, Environment.getExternalStorageDirectory() + "/receipt_resized.png", Bitmap.CompressFormat.PNG);
    }

    public static void WriteBitmapToFile(Bitmap bm, String path, Bitmap.CompressFormat compressFormat) {
        OutputStream stream = null;
        try {
            try {
                stream = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bm.compress(compressFormat, 80, stream);
            if (stream != null) stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
