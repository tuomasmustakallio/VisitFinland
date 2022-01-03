package com.example.visitfinland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
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
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        cityList = (ListView) findViewById(R.id.cityListView);
        cities = res.getStringArray(R.array.cities);
        distances = res.getStringArray(R.array.distances);

        ItemAdapter itemAdapter = new ItemAdapter(this, cities, distances, mImageIds);
        cityList.setAdapter(itemAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailActivity = new Intent(getApplicationContext(), CityActivity.class);
                showDetailActivity.putExtra("visitfinland.ITEM_INDEX", position);
                startActivity(showDetailActivity);
            }
        });

    }

    private int getImg(int index) {
        switch (index){
            case 0: return R.drawable.kouvola;
            case 1: return R.drawable.helsinki;
            case 2: return R.drawable.tampere;
            case 3: return R.drawable.oulu;
            case 4: return R.drawable.lappeenranta;
            case 5: return R.drawable.jyvaskyla;
            case 6: return R.drawable.rovaniemi;
            case 7: return R.drawable.turku;
            default: return -1;
        }
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