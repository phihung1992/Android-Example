package com.hung.mymusic;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HUNG on 16/5/2017.
 */

public class MyActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {
    private ListView lvList;
    private ImageView ivRepeat, ivBack, ivPlay, ivNext, ivStop, ivShuffle;
    private ArrayList<Song> mListSong;
    private TextView tvSongName, tvArtist, tvOrder, tvDuration;
    private SeekBar sbSeekBar;
    private MediaManager mediaManager;
    private SongAdapter adapter;
    private static final int LEVEL_PLAY = 0;
    private static final int LEVEL_PAUSE = 1;
    private static final int LEVEL_ON = 1;
    private static final int LEVEL_OFF = 0;
    private int progess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initData();
        initView();
    }

    private void initData() {
        mediaManager = new MediaManager(this);
        mListSong = mediaManager.getListSong();
        adapter = new SongAdapter(this, mListSong);
    }

    private void initView() {

        lvList = (ListView) findViewById(R.id.lv_list);
        lvList.setOnItemClickListener(this);
        lvList.setAdapter(adapter);
        tvSongName = (TextView) findViewById(R.id.tv_song_name);
        tvArtist = (TextView) findViewById(R.id.tv_artist);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        tvOrder = (TextView) findViewById(R.id.tv_order);

        ivRepeat = (ImageView) findViewById(R.id.iv_repeat);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivNext = (ImageView) findViewById(R.id.iv_next);
        ivPlay = (ImageView) findViewById(R.id.iv_play);
        ivStop = (ImageView) findViewById(R.id.iv_stop);
        ivShuffle = (ImageView) findViewById(R.id.iv_shuffle);

        ivRepeat.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivPlay.setOnClickListener(this);
        ivShuffle.setOnClickListener(this);
        ivStop.setOnClickListener(this);

        sbSeekBar = (SeekBar) findViewById(R.id.sb_seek);
        sbSeekBar.setMax(mediaManager.getCurrentSong().getDuration());
        sbSeekBar.setOnSeekBarChangeListener(this);
    }

    public void updateSong() {
        Song song = mediaManager.getCurrentSong();
        setInfoSong(song);
        new UpdateSeekBar().execute();
    }

    private void setInfoSong(Song song) {
        tvSongName.setText(song.getName());
        tvArtist.setText(song.getArtist());
        tvOrder.setText(String.valueOf(mediaManager.getIndex()) + "/" + String.valueOf(mediaManager.getListSong().size()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_repeat:
                doRepeat();
                break;
            case R.id.iv_back:
                doBack();
                break;
            case R.id.iv_play:
                doPlay();
                break;
            case R.id.iv_next:
                doNext();
                break;
            case R.id.iv_stop:
                doStop();
                break;
            case R.id.iv_shuffle:
                doShuffle();
                break;
            default:
                break;
        }
    }

    private void doShuffle() {
        if (mediaManager.isShuffle()) {
            mediaManager.setShuffle(false);
            ivShuffle.setImageLevel(LEVEL_OFF);
            Toast.makeText(this, "Shuffle Off", Toast.LENGTH_SHORT).show();
        } else {
            mediaManager.setShuffle(true);
            ivShuffle.setImageLevel(LEVEL_ON);
            Toast.makeText(this, "Shuffle On", Toast.LENGTH_SHORT).show();
        }
    }

    private void doStop() {
        mediaManager.stop();
    }

    private void doNext() {
        if (mediaManager.next()) {
            ivPlay.setImageLevel(LEVEL_PLAY);
            updateSong();
        }
    }

    private void doBack() {
        if (mediaManager.back()) {
            ivPlay.setImageLevel(LEVEL_PLAY);
            updateSong();
        }
    }

    private void doRepeat() {
        if (mediaManager.getRepeatMode() == MediaManager.REPEAT_OFF) {
            setRepeat(MediaManager.REPEAT_ONE);
        } else if (mediaManager.getRepeatMode() == MediaManager.REPEAT_ONE) {
            setRepeat(MediaManager.REPEAT_ALL);
        } else {
            setRepeat(MediaManager.REPEAT_OFF);
        }
    }

    private void setRepeat(int repeatMode) {
        mediaManager.setRepeatMode(repeatMode);
        ivRepeat.setImageLevel(repeatMode);
        Toast.makeText(this, "Mode: " + repeatMode, Toast.LENGTH_SHORT).show();
    }

    private void doPlay() {
        if (mediaManager.play()) {
            ivPlay.setImageLevel(LEVEL_PLAY);
            updateSong();
        } else {
            ivPlay.setImageLevel(LEVEL_PAUSE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mediaManager.play(position);
        updateSong();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.progess = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaManager.seek(progess);
        seekBar.setProgress(seekBar.getProgress());
    }

    private class UpdateSeekBar extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            while (mediaManager.isPlaying()) {
                try {
                    Thread.sleep(1000);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            tvDuration.setText(mediaManager.getTimeText());
            sbSeekBar.setProgress(mediaManager.getCurrentTime());
        }
    }
}
