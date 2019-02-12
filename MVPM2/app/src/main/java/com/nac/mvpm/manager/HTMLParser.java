package com.nac.mvpm.manager;

import android.util.Log;

import com.nac.mvpm.model.NewsEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 13/05/2017.
 */
public class HTMLParser {
    private static final String TAG = HTMLParser.class.getName();
    private static HTMLParser instance;

    //for singleton pattern
    private HTMLParser() {
    }

    public static HTMLParser getInstance() {
        if (instance == null) {
            instance = new HTMLParser();
        }
        return instance;
    }

    public List<NewsEntity> getNews(String link) {
        try {
            Document doc = Jsoup.connect(link).get();
            Elements imgFloat = doc.getElementsByClass("imgFloat");
            if (imgFloat == null) {
                Log.e(TAG, "not get data");
                return null;
            }
            List<NewsEntity> listData = new ArrayList<>();
            for (Element item : imgFloat) {
                //Log.i(TAG, item.toString());
                String title = item.getElementsByTag("a").get(0).
                        getElementsByAttribute("title").attr("title");

                String linkImg = item.getElementsByTag("a").get(0).
                        getElementsByAttribute("src").attr("src");

                String linkNews = "http://www.24h.com.vn"
                        + item.getElementsByTag("a").get(0).
                        getElementsByAttribute("href").attr("href");

                Log.i(TAG, "Title: " + title);
                Log.i(TAG, "LinkImg: " + linkImg);
                Log.i(TAG, "LinkNews: " + linkNews);
                Log.i(TAG, "-----------------------------");
                listData.add(new NewsEntity(linkImg, title, "Xem them", linkNews));
            }
            if (listData.size() > 0) return listData;

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
