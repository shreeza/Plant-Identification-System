package com.example.shailee.camo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_Details extends AppCompatActivity {

    public static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String commonname = getIntent().getExtras().getString("common_name");
        String benifits = getIntent().getExtras().getString("benifits");
        String familyName = getIntent().getExtras().getString("familyName");
        String genus = getIntent().getExtras().getString("genus");
        byte[] image = getIntent().getExtras().getByteArray("image");
        String scientific_name = getIntent().getExtras().getString("scientific_name");

        TextView tv_details = findViewById(R.id.tv_details);
        ImageView imgView = findViewById(R.id.imgView_plant);

        String str = "<b>" + commonname + "</b><br><b>Scientific Name: </b> " + scientific_name + "<br>"
                + "<b>Family Name: </b> " + familyName + "<br>"
                + "<b>Genus: </b> " + genus + "<br>"
                + "<b>Benifits: </b> " + benifits + "<br>";

        tv_details.setText(Html.fromHtml(str));

        Bitmap imgBitmap = toBitmap(image);
        imgView.setImageBitmap(imgBitmap);

    }
}
