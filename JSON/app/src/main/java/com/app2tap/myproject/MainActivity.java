package com.app2tap.myproject;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: MyProject.
 * Author: HUNG CODER.
 * Created on: 1/10/2017
 */

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<Song> mListSong;
    private ListView mLvListSong;
    private ServiceConnection serviceConnection;
    private MusicService musicService;
    private boolean isServiceConnected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mListSong = new ArrayList<>();
        mLvListSong = (ListView) findViewById(R.id.lv_list_song);
        mLvListSong.setOnItemClickListener(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSon().execute("https://storage.googleapis.com/automotive-media/music.json");
            }
        });
    }


    private String readFromURL(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!isServiceConnected) {
            return;
        }

        musicService.playSong("https://storage.googleapis.com/automotive-media/" + mListSong.get(position).getSource());
    }

    private void connectMusicService() {
        Intent intent = new Intent(this, MusicService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MusicService.MusicBinder musicBinder = (MusicService.MusicBinder) service;
                musicService = musicBinder.getService();
                isServiceConnected = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
    }

    private void disconnectMusicService() {
        unbindService(serviceConnection);
    }

    private class ReadJSon extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readFromURL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject listSong = new JSONObject(s);
                JSONArray jsonArray = listSong.getJSONArray("music");
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject song = jsonArray.getJSONObject(i);
                    mListSong.add(new Song(
                            song.getString("title"),
                            song.getString("album"),
                            song.getString("artist"),
                            song.getString("genre"),
                            song.getString("source"),
                            song.getString("image"),
                            song.getInt("trackNumber"),
                            song.getInt("totalTrackCount"),
                            song.getInt("duration"),
                            song.getString("site")
                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            mLvListSong.setAdapter(new SongAdapter(getApplicationContext(), mListSong));
            connectMusicService();
        }
    }

    @Override
    protected void onDestroy() {
        disconnectMusicService();
        super.onDestroy();
    }
}
