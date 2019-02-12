package com.hung.mymusic;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by HUNG on 16/5/2017.
 */

public class MediaManager implements MediaPlayer.OnCompletionListener {
    private static final int IDLE = 0;
    private static final int PLAYING = 1;
    private static final int PAUSED = 2;
    private static final int STOPPED = 4;
    public static final int REPEAT_OFF = 0;
    public static final int REPEAT_ONE = 1;
    public static final int REPEAT_ALL = 2;
    private int state = IDLE;
    private MediaPlayer mPlayer;
    private Context mContext;
    private ArrayList<Song> mListSong;
    private int mIndex;
    private boolean isShuffle;
    private int repeatMode = REPEAT_OFF;

    public MediaManager(Context context) {
        mContext = context;
        initData();
    }

    private void initData() {
        mPlayer = new MediaPlayer();
        mListSong = new ArrayList<>();

        Uri audioUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String projection[] = new String[]{
                MediaStore.Audio.AudioColumns.TITLE,
                MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.ARTIST,
                MediaStore.Audio.AudioColumns.DURATION
        };

        String where = MediaStore.Audio.AudioColumns.DISPLAY_NAME + " LIKE '%.mp3'";

        Cursor c = mContext.getContentResolver().query(audioUri, projection, where, null, null);
        if (c == null) {
            return;
        }

        c.moveToFirst();

        int indexTitle = c.getColumnIndex(projection[0]);
        int indexData = c.getColumnIndex(projection[1]);
        int indexAlbum = c.getColumnIndex(projection[2]);
        int indexArtist = c.getColumnIndex(projection[3]);
        int indexDuration = c.getColumnIndex(projection[4]);

        String name, path, album, artist;
        int duration;

        while (!c.isAfterLast()) {
            name = c.getString(indexTitle);
            path = c.getString(indexData);
            album = c.getString(indexAlbum);
            artist = c.getString(indexArtist);
            duration = c.getInt(indexDuration);

            mListSong.add(new Song(name, path, album, artist, duration));

            c.moveToNext();
        }

        c.close();
    }

    public boolean play() {
        try {
            if (state == IDLE || state == STOPPED) {
                Song song = mListSong.get(mIndex);
                mPlayer.setDataSource(song.getPath());
                mPlayer.setOnCompletionListener(this);
                mPlayer.prepare();
                mPlayer.start();
                state = PLAYING;
                return true;
            } else if (state == PLAYING) {
                mPlayer.pause();
                state = PAUSED;
                return false;
            } else {
                mPlayer.start();
                state = PLAYING;
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isShuffle() {
        return isShuffle;
    }

    public void setShuffle(boolean shuffle) {
        isShuffle = shuffle;
    }

    public void play(int position) {
        mIndex = position;
        stop();
        play();
    }

    public void stop() {
        if (state == PLAYING || state == PAUSED) {
            mPlayer.stop();
            mPlayer.reset();
            state = STOPPED;
        }
    }

    public boolean back() {
        if (mIndex == 0) {
            mIndex = mListSong.size();
        }
        mIndex--;
        stop();
        return play();
    }

    public boolean next() {
        if (isShuffle) {
            mIndex = new Random().nextInt(mListSong.size());
        } else {
            mIndex = (mIndex + 1) % mListSong.size();
        }
        stop();
        return play();
    }

    public ArrayList<Song> getListSong() {
        return mListSong;
    }

    public int getRepeatMode() {
        return repeatMode;
    }

    public void setRepeatMode(int repeatMode) {
        this.repeatMode = repeatMode;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        switch (repeatMode) {
            case REPEAT_OFF:
                if (mIndex < (mListSong.size() - 1)) {
                    mIndex++;
                    stop();
                    play();
                }
                break;
            case REPEAT_ONE:
                stop();
                play();
                break;
            case REPEAT_ALL:
                mIndex++;
                if (mIndex == mListSong.size()) {
                    mIndex = 0;
                }
                stop();
                play();
                break;
            default:
                break;
        }
    }

    public Song getCurrentSong() {
        return getListSong().get(mIndex);
    }

    public int getIndex() {
        return mIndex;
    }

    public boolean isPlaying() {
        return state == PLAYING || state == PAUSED;
    }

    public String getTimeText() {
        int currentTime = mPlayer.getCurrentPosition();
        int totalTime = mListSong.get(mIndex).getDuration();
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(currentTime) + "/" + dateFormat.format(totalTime);
    }

    public int getCurrentTime() {
        return mPlayer.getCurrentPosition();
    }

    public void seek(int progress) {
        mPlayer.seekTo(progress);
    }
}
