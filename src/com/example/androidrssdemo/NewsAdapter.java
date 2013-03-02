package com.example.androidrssdemo;

import java.util.ArrayList;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class NewsAdapter extends ArrayAdapter<NewsEntry> {
	
	private ArrayList<NewsEntry> newsEntries;
	
	public NewsAdapter(Context context, int textViewResourceId, ArrayList<NewsEntry> objects) {
		super(context, textViewResourceId, objects);
		this.newsEntries = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View targetView = convertView;
		if(targetView == null) {
			LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			targetView = li.inflate(R.layout.row, null);
		}
		
		NewsEntry news = newsEntries.get(position);
		if(news != null) {
			TextView dataTextView = (TextView)targetView.findViewById(R.id.newsTitle);
			dataTextView.setText( news.getTitle() );
			dataTextView = (TextView)targetView.findViewById(R.id.newsDesc);
			dataTextView.setText( news.getDescription() );
			ImageView newsSourceIcon = (ImageView)targetView.findViewById(R.id.icon);
			Resources iconResource = getContext().getResources();
			Drawable icon = iconResource.getDrawable(R.drawable.hsfi_icon);
			newsSourceIcon.setImageDrawable(icon);
		}
		return targetView;
	}

	public ArrayList<NewsEntry> getNewsEntries() {
		return newsEntries;
	}
	
	
}






