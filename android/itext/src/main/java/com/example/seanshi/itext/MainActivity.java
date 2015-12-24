package com.example.seanshi.itext;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //   _text.setText("button is clicked");
                Toast.makeText(MainActivity.this, "Instant Run rocks...222.!", Toast.LENGTH_SHORT).show();
                test();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void test()
    {
        String html = "<html>\n" +
                "<Head>\n" +
                "</Head>\n" +
                "<body>\n" +
                "    <div id=\"Location Details\" style=\"Width:400\">\n" +
                "        <p>Cypress Creek Headquarters</p>\n" +
                "        <p>aaaafjasfdakdskj</p>\n" +
                "        <p></p>\n" +
                "        <p>Cypress  </p>\n" +
                "        <p>Texas   </p>\n" +
                "        <p>United States</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        convertHtmlToPdf(html);
    }

    private void convertHtmlToPdf(String html)
    {
        System.out.println(".... step 1");
        String filename = Environment.getExternalStorageDirectory() + "/receipt.pdf";
        try {
            // step 1
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            // step 3
            document.open();
            // step 4
            StringBuilder sb = new StringBuilder();
            sb.append(html);

            PdfPTable table = new PdfPTable(1);
            PdfPCell cell = new PdfPCell();
            ElementList list = XMLWorkerHelper.parseToElementList(sb.toString(), null);
            for (Element element : list) {
                cell.addElement(element);
            }
            table.addCell(cell);
            document.add(table);

            // step 5
            document.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
