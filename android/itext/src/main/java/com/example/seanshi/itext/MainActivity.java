package com.example.seanshi.itext;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import gui.ava.html.image.generator.HtmlImageGenerator;

public class MainActivity extends AppCompatActivity {

    ImageView iconView;
    ImageView imageView;
    WebView webview;

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

        String date = "";

//        Locale locale = new Locale("fr", "FR");
        Locale locale = Locale.getDefault();
        Date currentDate = new Date();
        date = new SimpleDateFormat("MMMM dd, yyyy", locale).format(currentDate) + " at " + new SimpleDateFormat("hh:mm a", locale).format(currentDate).toLowerCase(locale);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a");
//        date = dateFormat.format(new java.util.Date());
        System.out.println(".......................................["+date+"]");
        button.setText(date);

        iconView = (ImageView)findViewById(R.id.imageView1);
        imageView = (ImageView)findViewById(R.id.imageView2);
        webview = (WebView)findViewById(R.id.webView1);

        new SendEmailTask().execute();
//        test();
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
        String html = "";
/*
        html = "<html>\n" +
                "<Head>\n" +
                "</Head>\n" +
                "<body>\n" +
                "    <div id=\"Location Details\" style=\"Width:400\">\n" +
                "        <p>Cypress Creek Headquarters</p>\n" +
                "        <p>aaaafjasfdakdskj</p>\n" +
                "        <p></p>\n" +
                "        <p>Cypress 222 </p>\n" +
                "        <p>Texas   </p>\n" +
                "        <p>United States</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
*/

        html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<Head>\n" +
                "    <Style>\n" +
                "        body {\n" +
                "        width:100px;\n" +
                "        font-size:10px;\n" +
                "        margin:10px;\n" +
                "        }\n" +
                "    </Style>\n" +
                "</Head>\n" +
                "<body>\n" +
                "<center>\n" +
                "    \n" +
                "    <img src=\"file:///data/data/com.iqmetrix.platformpos.full/app_imageDir/receiptImage.png\" alt=\"file:///data/data/com.iqmetrix.platformpos.full/app_imageDir/receiptImage.png\" style=\"Width:400\"/>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div id=\"Location Details\" style=\"Width:400\">\n" +
                "        <p>Cypress Creek Headquarters</p>\n" +
                "        <p>5555551234</p>\n" +
                "        <p></p>\n" +
                "        <p>Cypress </p>\n" +
                "        <p>Texas  </p>\n" +
                "        <p>United States</p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div>\n" +
                "        <p>Sales Associate: 0</p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div>\n" +
                "        <p>Tue Dec 29 14:46:34 CST 2015</p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <Div>\n" +
                "        <p>Billl Def</p>\n" +
                "        <p>(635) 999-8888 </p>\n" +
                "        <p>Office</p>\n" +
                "        <p>Buugjuu </p>\n" +
                "        <p>TexasBjjbhjhjb</p>\n" +
                "        <p>United States </p>\n" +
                "        <p>fjgujctggfthdsg@cghghfghft.com</p>\n" +
                "    </Div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <Div>\n" +
                "        <h3>Invoice #: 2F128A6CF5</h3>\n" +
                "        <div style='font-style:Barcode39' id=\"Invoice Barcode\">\n" +
                "            <h3>*2F128A6CF5*</h3>\n" +
                "            <h3>*YUY9S03*</h3>\n" +
                "        </div>\n" +
                "    </Div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div id=\"Discounts\" align=right>\n" +
                "        <p>DISCOUNTREASON DISCOUNTAMOUNT</p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div id=\"Total\" align=right>\n" +
                "        <h3> Invoice Total: TOTAL</h3>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div>\n" +
                "        <p></p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div>\n" +
                "        <p></p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div>\n" +
                "        <p></p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div>\n" +
                "        <p></p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "    <div>\n" +
                "        <p><a> WEBSITELINK>WEBSITELABEL</a></p>\n" +
                "    </div>\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "    \n" +
                "\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>";
//        convertHtmlToPdf(html);
        convertHtmlToBmp(html);
//        convertHtmlToBmp2(html);

    }

    private void convertHtmlToBmp2(String html)
    {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml("html");
        imageGenerator.saveAsImage(Environment.getExternalStorageDirectory() + "/receipt1.png");
    }

    private void convertHtmlToBmp(String html)
    {

        final WebView v = webview; // new WebView(this);
//        DisplayMetrics displayMetrics = PlatformPOSApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics();

        v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        v.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        v.setInitialScale(150);
        v.getSettings().setLoadWithOverviewMode(true);
        v.loadDataWithBaseURL("", html, "text/html", "utf-8", "");
        v.setMinimumWidth(100);
        v.setMinimumHeight(500);

        v.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
                if(progress==100)
                    new Background().execute();
            }
        });


/*
        v.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                addView(v, new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = createBitmapFromView(v);
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        imageView.setImageDrawable(drawable);
                        imageView.setVisibility(View.VISIBLE);
//                        removeAllViews();
//                        if (listener != null) listener.onComplete();
                    }
                }, 500);
            }
        });
*/
        v.setBackgroundColor(Color.TRANSPARENT);
        //v.getSettings().setJavaScriptEnabled(true);
        v.loadDataWithBaseURL("", html, "text/html", "utf-8", "");
        v.setHorizontalScrollBarEnabled(false);
        v.setVerticalScrollBarEnabled(false);

    }

    public Bitmap createBitmapFromView(View view) {
        //Pre-measure the view so that height and width don't remain null.
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        //Assign a size and position to the view and all of its descendants
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //Create the bitmap
        System.out.println("width ["+view.getMeasuredWidth()+"]  height["+view.getMeasuredHeight()+"]");

//        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Bitmap.createBitmap(200, 300, Bitmap.Config.ARGB_8888);

        //Create a canvas with the specified bitmap to draw into
        Canvas c = new Canvas(bitmap);

        //Render this view (and all of its children) to the given Canvas
        view.draw(c);
        return bitmap;
    }

    private static Bitmap pictureDrawable2Bitmap(Picture picture) {
        PictureDrawable pictureDrawable = new PictureDrawable(picture);
        Bitmap bitmap = Bitmap.createBitmap(pictureDrawable.getIntrinsicWidth(), pictureDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawPicture(pictureDrawable.getPicture());
        return bitmap;
    }

    class Background extends AsyncTask<Void, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Void... params)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e){}
            catch (Exception e){}
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap result)
        {
            Bitmap bitmap = Bitmap.createBitmap(webview.getWidth(), webview.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            webview.draw(canvas);
            imageView.setImageBitmap(bitmap);

            FileOutputStream out = null;
            try {
                out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/receipt.png");
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void convertHtmlToPdf(String html)
    {
        String filename = Environment.getExternalStorageDirectory() + "/receipt.pdf";
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            PdfPTable table = new PdfPTable(1);
            PdfPCell cell = new PdfPCell();

            ElementList list = XMLWorkerHelper.parseToElementList(html, null);
            for (Element element : list) {
                cell.addElement(element);
            }
            table.addCell(cell);
            document.add(table);

            document.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private class SendEmailTask extends AsyncTask<Void, Integer, String> {
        protected String doInBackground(Void... urls)
        {
            testEmail();
            return "";
        }

        protected void onProgressUpdate(Integer... progress) {
            // setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
            System.out.println("sending completed ");
        }
    }

    public void testEmail()
    {
        try {
            sendEmail("seans@iqmetrix.com", "Your receipt", "this is a test");
         //   sendEmail();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendEmail(String to, String subject, String content) throws Exception
    {
/*
        final String from = "possum.noreply@gmail.com";
        final String password = "possum123";
        String host = "smtp.gmail.com";
*/

        final String from = "noreply-pos@iqmetrix.com";
        final String password = "Gen2Toast4!";
        String host = "smtp.office365.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.timeout", "60000");
        properties.put("mail.smtp.writetimeout", "60000");
        properties.put("mail.smtp.connectiontimeout", "60000");

        System.out.println("................................................... session ");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });


        Transport t = null;
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart textBodyPart = new MimeBodyPart();

//            String htmlText = readFile(getExternalFilesDir(null) +"/assets/template.html");

            String htmlText = "<html><head>\n" +
                    "\n" +
                    "\t<style>\n" +
                    "\t\timage { width: 600; max-width: 600;}\n" +
                    "\t</style>\n" +
                    "\n" +
                    "<!--[if gte mso 9]>\n" +
                    "<style type=\"text/css\">\n" +
                    "img { width: 600; } \n" +
                    "</style>\n" +
                    "<![endif]--></head>\n<body><H1>Hello, this is  a test.</H1><img src=\"cid:image\" width=\"600\"></body></html>";

            textBodyPart.setContent(htmlText, "text/html");
            multipart.addBodyPart(textBodyPart);

            String filename = Environment.getExternalStorageDirectory() + "/icon.png";
            File file = new File(filename);
            if(file.exists()){
                System.out.println("...........................................file is already there");
            }else{
                System.out.println("...........................................Not find file ");
            }

            MimeBodyPart imageBodyPart = new MimeBodyPart();
//            DataSource fds = new FileDataSource(filename);

//            imageBodyPart.setDataHandler(new DataHandler(fds));
            imageBodyPart.attachFile(filename);
            imageBodyPart.setHeader("Content-ID", "<image>");
            imageBodyPart.setHeader("Content-Type", "image/jpg");
            imageBodyPart.setDisposition(MimeBodyPart.INLINE);

            multipart.addBodyPart(imageBodyPart);

            // Send the actual HTML message, as big as you like
            //message.setContent(content, "text/html");
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent message successfully...."+to);
        } catch (Exception ex) {
            System.out.println("Failed to send message to "+to + ".  ["+ex.getMessage()+"]");
            ex.printStackTrace();
        } finally {
            try {
                if (t != null) {
                    t.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public void sendEmail() throws Exception
    {
        String host = "smtp.gmail.com";
        String address = "possum.noreply@gmail.com";
        String from = "possum.noreply@gmail.com";
        String pass = "possum123";
        String to="seans@iqmetrix.com";
        Multipart multiPart;
        String finalString="";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", address);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        System.out.println("Check "+ "done pops");
        Session session = Session.getDefaultInstance(props, null);
        DataHandler handler=new DataHandler(new ByteArrayDataSource(finalString.getBytes(),"text/plain" ));
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setDataHandler(handler);
        System.out.println("Check "+ "done sessions");

        multiPart=new MimeMultipart();
        InternetAddress toAddress;
        toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        System.out.println("Check "+ "added recipient");
        message.setSubject("Send Auto-Mail");
        message.setContent(multiPart);
        message.setText("Demo For Sending Mail in Android Automatically");
        System.out.println("check "+ "transport");
        Transport transport = session.getTransport("smtp");
        System.out.println("check "+ "connecting");
        transport.connect(host,address , pass);

        System.out.println("check "+ "wana send");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("check "+ "sent");
    }

    private String readFile(String filename)
    {
        File file = new File(filename);

        //Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(text.toString());

        return text.toString();
    }
}
