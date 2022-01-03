package com.example.visitfinland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class CityActivity extends AppCompatActivity {

    String[] cities;
    String[] distances;
    Integer[] mImageIds = {
            R.drawable.kouvola,
            R.drawable.helsinki,
            R.drawable.tampere,
            R.drawable.oulu,
            R.drawable.lappeenranta,
            R.drawable.jyvaskyla,
            R.drawable.rovaniemi,
            R.drawable.turku,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        Intent in = getIntent();
        int index =  in.getIntExtra("com.example.module3.ITEM_INDEX", -1);

        if (index > -1) {
            int pic = mImageIds[index];
            ImageView img = (ImageView) findViewById(R.id.biggerImageView);
            img.setImageResource(mImageIds[index]);
        }

        Resources res = getResources();
        cities = res.getStringArray(R.array.cities);
        distances = res.getStringArray(R.array.distances);

    }

    private void scaleImg(ImageView img, int pic) {

        Display screen =  getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth) {
            int ratio = Math.round( (float)imgWidth / (float)screenWidth);
            options.inSampleSize = ratio;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);

    }
}