package com.example.qingchen.advertisementview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qingchen
 */
public class MainActivity extends AppCompatActivity {

    AdvertisementView advertisementView;
    List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("恭喜尾号5465用户变帅了！");
        list.add("恭喜尾号5465用户变帅了！");
        list.add("恭喜尾号5465用户变帅了！");
        list.add("恭喜尾号5465用户变帅了！");
        list.add("恭喜尾号5465用户变帅了！");
        list.add("恭喜尾号5465用户变帅了！");
        advertisementView=findViewById(R.id.my_view);
        advertisementView.setList(list);
        advertisementView.start();
    }
}
