package com.app2tap.mycontact;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: MyContact.
 * Author: HUNG CODER.
 * Created on: 27/9/2017
 */

public class ContactManager {
    private List<Contact> mListContact;
    private List<Contact> mListResult;
    private Context mContext;

    public ContactManager(Context context) {
        mContext = context;
        getContactData();

        mListResult = new ArrayList<>();
        for (int i = 0; i < mListContact.size(); ++i) {
            mListResult.add(mListContact.get(i));
        }
    }

    private void getContactData() {
        mListContact = new ArrayList<>();

        String projections[] = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI};
        Cursor phones = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projections, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(projections[0]));
            String phoneNumber = phones.getString(phones.getColumnIndex(projections[1]));
            String avatarUri = phones.getString(phones.getColumnIndex(projections[2]));
            mListContact.add(new Contact(name, phoneNumber, getContactBitmap(avatarUri)));
        }
        phones.close();
    }

    private Bitmap getContactBitmap(String uri) {
        if (uri != null) {

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.parse(uri));
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Contact> getListContact() {
        return mListResult;
    }

    public void searchResult(String searchKey) {
        mListResult.clear();
        for (int i = 0; i < mListContact.size(); ++i) {
            if (mListContact.get(i).getName().toLowerCase().contains(searchKey.toLowerCase()) ||
                    mListContact.get(i).getPhoneNumber().toLowerCase().contains(searchKey.toLowerCase())) {
                mListResult.add(mListContact.get(i));
            }
        }
    }

    public void updateContact() {
    }
}
