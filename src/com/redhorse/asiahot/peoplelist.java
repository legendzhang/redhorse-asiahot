/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.redhorse.asiahot;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A list view example where the data comes from a cursor.
 */
public class peoplelist extends Activity implements OnItemClickListener {

	private Cursor c;
	private ListView mList;

	private List<String> mContactURI;
	private List<String> mContactID;
	private List<String> mContact;
	private List<Bitmap> mContactPhoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadApps();
		setContentView(R.layout.peoplelist);
		mList = (ListView) findViewById(R.id.PeopleListView);
		mList.setAdapter(new AppsAdapter());
		mList.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent i = getIntent();
		Bundle b = new Bundle();
		b.putString("msg", mContact.get(arg2));
		i.putExtras(b);
		peoplelist.this.setResult(RESULT_OK, i);
		peoplelist.this.finish();
	}

	public class AppsAdapter extends BaseAdapter {
		public AppsAdapter() {
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			// 从layout文件生成list里面的内容
			convertView = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.peoplerow, null);

			TextView mTextView = (TextView) convertView
					.findViewById(R.id.imageTitle);
			mTextView.setText(mContact.get(position));
			ImageView mImageView = (ImageView) convertView
					.findViewById(R.id.imageView);
			mImageView.setImageBitmap(mContactPhoto.get(position));
			GradientDrawable grad = new GradientDrawable(
					Orientation.TOP_BOTTOM, new int[] { Color.DKGRAY,
							Color.BLACK });
			convertView.setBackgroundDrawable(grad);
			return convertView;

		}

		public final int getCount() {
			return mContact.size();
		}

		public final Object getItem(int position) {
			return mContact.get(position);
		}

		public final long getItemId(int position) {
			return position;
		}
	}

	private void loadApps() {
		mContactID = new ArrayList<String>();
		mContactURI = new ArrayList<String>();
		mContact = new ArrayList<String>();
		mContactPhoto = new ArrayList<Bitmap>();

		for (int k = 0; k < 5; k++) {
			String filename = "/sdcard/asiahot" + (k + 1) + "-50.jpg";
			mContactID.add("" + (k + 1));
			mContactURI.add("");
			mContact.add(filename);
			mContactPhoto.add(getImageFromFile(filename));
		}
		for (int k = 0; k < 5; k++) {
			String filename = "/sdcard/asiahot" + (k + 1) + "-50.jpg";
			mContactID.add("" + (k + 1));
			mContactURI.add("");
			mContact.add(filename);
			mContactPhoto.add(getImageFromFile(filename));
		}
		for (int k = 0; k < 5; k++) {
			String filename = "/sdcard/asiahot" + (k + 1) + "-50.jpg";
			mContactID.add("" + (k + 1));
			mContactURI.add("");
			mContact.add(filename);
			mContactPhoto.add(getImageFromFile(filename));
		}
		for (int k = 0; k < 5; k++) {
			String filename = "/sdcard/asiahot" + (k + 1) + "-50.jpg";
			mContactID.add("" + (k + 1));
			mContactURI.add("");
			mContact.add(filename);
			mContactPhoto.add(getImageFromFile(filename));
		}
		for (int k = 0; k < 5; k++) {
			String filename = "/sdcard/asiahot" + (k + 1) + "-50.jpg";
			mContactID.add("" + (k + 1));
			mContactURI.add("");
			mContact.add(filename);
			mContactPhoto.add(getImageFromFile(filename));
		}
	}

	private Bitmap getImageFromFile(String filename) {
		Bitmap image = null;
		try {
//			File file = new File(filename);
//			InputStream is = new BufferedInputStream(new FileInputStream(file));
			URL myURL = new URL("http://10.1.1.74/a"+filename);
			/*打开URL连接*/
			URLConnection ucon = myURL.openConnection();
			/*使用IputStreams，从URLConnection读取数据*/
			InputStream is = ucon.getInputStream();			
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
		}
//		Log.e("image-h:", ""+image.getHeight());
//		Log.e("image-w:", ""+image.getWidth());
		return image;
	}
}
