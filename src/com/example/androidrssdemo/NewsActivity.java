package com.example.androidrssdemo;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;


public class NewsActivity extends ListActivity {
	ArrayList<NewsEntry> news = new ArrayList<NewsEntry>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		NewsDataDownloader downloader = new NewsDataDownloader(this);
		downloader.execute("http://www.hs.fi/uutiset/rss/");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_news, menu);
		return true;
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(news.get(position).getLink()));
		startActivity(intent);
    }

	
	public void setNews(ArrayList<NewsEntry> news) {
		this.news = news;
	}
	
}


