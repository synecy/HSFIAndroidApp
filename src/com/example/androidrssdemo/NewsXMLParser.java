package com.example.androidrssdemo;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class NewsXMLParser {

	public ArrayList<ArrayList<String>> parseNewsXml( String xmlStringData ) throws IOException, XmlPullParserException {
		ArrayList<String> newsArticleTitles = new ArrayList<String>();
		ArrayList<String> newsArticleDescriptions = new ArrayList<String>();
		ArrayList<String> newsArticleLinks = new ArrayList<String>();
		ArrayList<ArrayList<String>> newsArticles = new ArrayList<ArrayList<String>>();
		
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(false);
        XmlPullParser xpp = factory.newPullParser();
		xpp.setInput(new StringReader(xmlStringData));
        int eventType = xpp.getEventType();
        boolean itemTagFound = false;
        
        while (eventType != XmlPullParser.END_DOCUMENT) {
        	if (eventType == XmlPullParser.START_TAG) {
        		if ( !itemTagFound ) {
	        		if (xpp.getName().equals("item")) {
	        			itemTagFound = true;
	        		}
	        	} else {
	        		if (xpp.getName().equals("title")) {
	        			newsArticleTitles.add(xpp.nextText());
	        		}
	        		if (xpp.getName().equals("link")) {
	        			newsArticleLinks.add(xpp.nextText());
	        		}
	        		if (xpp.getName().equals("pubDate")) {
	        			newsArticleDescriptions.add(xpp.nextText());
	        		}
	        	}
        	}
        	eventType = xpp.next();
        }
        newsArticles.add(newsArticleTitles);
        newsArticles.add(newsArticleDescriptions);
        newsArticles.add(newsArticleLinks);
        return newsArticles;
	}
	
}


