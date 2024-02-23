package com.example.a21110939;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img1 = (ImageView) findViewById(R.id.imageView1);
        img1.setImageResource(R.drawable.sau_rieng_0003);

        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.constraintLayout1);
        bg.setBackgroundColor(Color.BLUE);
        bg.setBackgroundResource(R.drawable.tao_0034);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.tao_0001);
        arrayList.add(R.drawable.tao_0034);
        arrayList.add(R.drawable.tao_0046);
        arrayList.add(R.drawable.tao_0050);

        Random random = new Random();

        ImageButton randomBtn = (ImageButton) findViewById(R.id.imageButton1);
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = random.nextInt(arrayList.size());
                bg.setBackgroundResource(arrayList.get(index));
            }
        });
    }


}