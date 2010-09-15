package com.redhorse.asiahot;

import android.widget.GridView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;

public class gridview extends Activity{
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        GridView gridview=(GridView)findViewById(R.id.gridview);//找到main.xml中定义gridview 的id
        gridview.setAdapter(new ImageAdapter(this));//调用ImageAdapter.java
        gridview.setOnItemClickListener(new OnItemClickListener(){//监听事件
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
         {
          Toast.makeText(gridview.this, ""+position,Toast.LENGTH_SHORT).show();//显示信息;
         }
        });
    }

}