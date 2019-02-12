package com.app2tap.myproject;

/**
 * Project: MyProject.
 * Author: HUNG CODER.
 * Created on: 1/10/2017
 */

public class Song {
    private String mTitle;
    private String mAlbum;
    private String mArtist;
    private String mGenre;
    private String mSource;
    private String mImage;
    private int mTrackNumber;
    private int mTotalTrackCount;
    private int mDuration;
    private String mSite;

    public Song(String title, String album, String artist, String genre, String source,
                String image, int trackNumber, int totalTrackCount, int duration, String site) {
        mTitle = title;
        mAlbum = album;
        mArtist = artist;
        mGenre = genre;
        mSource = source;
        mImage = image;
        mTrackNumber = trackNumber;
        mTotalTrackCount = totalTrackCount;
        mDuration = duration;
        mSite = site;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getGenre() {
        return mGenre;
    }

    public String getSource() {
        return mSource;
    }

    public String getImage() {
        return mImage;
    }

    public int getTrackNumber() {
        return mTrackNumber;
    }

    public int getTotalTrackCount() {
        return mTotalTrackCount;
    }

    public int getDuration() {
        return mDuration;
    }

    public String getSite() {
        return mSite;
    }
}
