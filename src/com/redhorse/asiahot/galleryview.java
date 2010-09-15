package com.redhorse.asiahot;  
  
import android.app.Activity;  
import android.content.Context;  
import android.content.res.TypedArray;  
import android.os.Bundle;  
import android.view.View;  
import android.view.ViewGroup;  
import android.view.animation.AnimationUtils;  
import android.widget.AdapterView;  
import android.widget.BaseAdapter;  
import android.widget.Gallery;  
import android.widget.ImageSwitcher;  
import android.widget.ImageView;  
import android.widget.AdapterView.OnItemSelectedListener;  
import android.widget.Gallery.LayoutParams;  
import android.widget.ViewSwitcher.ViewFactory;  
  
public class galleryview extends Activity implements OnItemSelectedListener,  
        ViewFactory  
{  
    private Gallery gallery;  
    private ImageSwitcher imageSwitcher;  
    private ImageAdapter imageAdapter;  
  
    private int[] resIds = new int[]  
    { R.drawable.asiahot1, R.drawable.asiahot2, R.drawable.asiahot3};  
  
    public class ImageAdapter extends BaseAdapter  
    {  
        int mGalleryItemBackground;  
        private Context mContext;  
  
        public ImageAdapter(Context context)  
        {  
            mContext = context;  
            TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);  
            mGalleryItemBackground = typedArray.getResourceId(  
                    R.styleable.Gallery_android_galleryItemBackground, 0);                        
        }  
        // 第1点改进，返回一个很大的值，例如，Integer.MAX_VALUE  
        public int getCount()  
        {  
            return Integer.MAX_VALUE;  
        }  
  
        public Object getItem(int position)  
        {  
            return position;  
        }  
  
        public long getItemId(int position)  
        {  
            return position;  
        }  
   
        public View getView(int position, View convertView, ViewGroup parent)  
        {  
            ImageView imageView = new ImageView(mContext);  
                // 第2点改进，通过取余来循环取得resIds数组中的图像资源ID  
            imageView.setImageResource(resIds[position % resIds.length]);  
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);  
            imageView.setLayoutParams(new Gallery.LayoutParams(163, 106));  
            imageView.setBackgroundResource(mGalleryItemBackground);  
            return imageView;  
        }  
    }  
  
    @Override  
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id)  
    {  
        // 选中Gallery中某个图像时，在ImageSwitcher组件中放大显示该图像  
        imageSwitcher.setImageResource(resIds[position % resIds.length]);  
  
    }  
    @Override  
    public void onNothingSelected(AdapterView<?> parent)  
    {  
    }  
  
    @Override  
    // ImageSwitcher组件需要这个方法来创建一个View对象（一般为ImageView对象）  
    //  来显示图像  
    public View makeView()  
    {  
        ImageView imageView = new ImageView(this);  
        imageView.setBackgroundColor(0xFF000000);  
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);  
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(  
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));  
        return imageView;  
    }  
  
    @Override  
    public void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.galleryview);  
        gallery = (Gallery) findViewById(R.id.gallery);  
        imageAdapter = new ImageAdapter(this);  
        gallery.setAdapter(imageAdapter);  
        gallery.setOnItemSelectedListener(this);  
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);  
        // 设置ImageSwitcher组件的工厂对象  
        imageSwitcher.setFactory(this);  
        // 设置ImageSwitcher组件显示图像的动画效果  
    imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,  
                android.R.anim.fade_in));       imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,  
                android.R.anim.fade_out));  
  
    }  
}  