package com.example.androidrssdemo;

import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;
import android.os.AsyncTask;


public class NewsDataDownloader extends AsyncTask<String, Void, String> {
	NewsAdapter newsAdapter;
	NewsActivity newsActivity;

	public NewsDataDownloader(NewsActivity activity) {
		newsActivity = activity;
	}
	
	@Override
	protected String doInBackground(String... params) {
		try {
			return new NewsDownloaderClass().download(params[0]);
		} catch(IOException e) {
			return "DownloadError";
		}
	}
	
	@Override
    protected void onPostExecute(String result) {
		if ( result != "DownloadError" ) {
			
			ArrayList<ArrayList<String>> newsData = new ArrayList<ArrayList<String>>();
			NewsXMLParser xmlParser = new NewsXMLParser();
			
			try {
				newsData = xmlParser.parseNewsXml(result);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			
			ArrayList<NewsEntry> news = new ArrayList<NewsEntry>();
			
			for ( int i = 0; i < newsData.get(0).size(); i++ ) {
				news.add( new NewsEntry( newsData.get(0).get(i), newsData.get(1).get(i), newsData.get(2).get(i) ) );
			}
			newsAdapter = new NewsAdapter(newsActivity, R.layout.row, news);
			newsActivity.setListAdapter( newsAdapter );
			newsActivity.setNews(news);
		} else {
			ArrayList<NewsEntry> news = new ArrayList<NewsEntry>();
			news.add( new NewsEntry( "Download error", "Could not download news data.", "http://www.google.com/" ) );
			newsAdapter = new NewsAdapter(newsActivity, R.layout.row, news);
			newsActivity.setListAdapter( newsAdapter );
			newsActivity.setNews(news);
		}
	}

}
