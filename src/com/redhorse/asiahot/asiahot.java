package com.redhorse.asiahot;

import java.io.File;
import java.util.Iterator;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class asiahot extends Activity implements OnGestureListener {
	private ViewFlipper flipper;
	private GestureDetector detector;

	private static final int STARTALL_REQUEST = 0;
	private static final int STARTCONFIG_REQUEST = 1;
	private static final int STARTLIST_REQUEST = 2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		detector = new GestureDetector(this);
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);
		
		ImageView iv = (ImageView) this.findViewById(R.id.ImageView01);
		iv.setImageDrawable(Drawable.createFromPath("/sdcard/asiahot.jpg"));
		ImageView iv1 = (ImageView) this.findViewById(R.id.ImageView02);
		iv1.setImageDrawable(Drawable.createFromPath("/sdcard/asiahot.jpg"));
		ImageView iv2 = (ImageView) this.findViewById(R.id.ImageView03);
		iv2.setImageDrawable(Drawable.createFromPath("/sdcard/asiahot.jpg"));
		
//		ImageView iv1 = new ImageView(this);
//		flipper.addView(addImageView("/sdcard/asiahot.jpg"));
		
		Button button = (Button) findViewById(R.id.Button01);
		button.setOnClickListener(Button01Listener);
		button = (Button) findViewById(R.id.Button02);
		button.setOnClickListener(Button02Listener);
		button = (Button) findViewById(R.id.Button03);
		button.setOnClickListener(Button03Listener);
	}

    public View addImageView(String filenamepath){  
        ImageView iv = new ImageView(this);  
//		iv.setImageDrawable(Drawable.createFromPath(filenamepath));        
		iv.setImageURI(Uri.fromFile(new File(filenamepath)));        
        return iv;
    }
        
    private View addTextView(String text) {
		TextView tv = new TextView(this);
		tv.setText(text);
		tv.setGravity(1);
		return tv;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > 120) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.left_out));
			this.flipper.showNext();
			return true;
		} else if (e1.getX() - e2.getX() < -120) {
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.right_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.right_out));
			this.flipper.showPrevious();
			return true;
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	private OnClickListener Button01Listener = new OnClickListener() {
		public void onClick(View v) {
			Intent setting = new Intent();
			setting.setClass(asiahot.this, galleryview.class);
			startActivityForResult(setting,STARTLIST_REQUEST);
		}
	};
	
	private OnClickListener Button02Listener = new OnClickListener() {
		public void onClick(View v) {
			Intent setting = new Intent();
			setting.setClass(asiahot.this, gridview.class);
			startActivity(setting);
		}
	};
	
	private OnClickListener Button03Listener = new OnClickListener() {
		public void onClick(View v) {
			Intent setting = new Intent();
//			setting.setClass(asiahot.this, pickgallery.class);
			setting.setClass(asiahot.this, peoplelist.class);
			startActivity(setting);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("REQUEST", "result");
		Intent it = new Intent();
		switch (requestCode) {
		case STARTLIST_REQUEST:
			switch (resultCode) {
			case RESULT_OK:
				Bundle b = data.getExtras();
				String msg = b.getString("msg");
				Log.e("STARTLIST_REQUEST", msg);
				break;
			default:
				break;
			}
			break;
		default:
			finish();
			break;
		}
	}
	
}