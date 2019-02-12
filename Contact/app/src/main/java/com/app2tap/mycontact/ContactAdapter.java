package com.app2tap.mycontact;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Project: MyContact.
 * Author: HUNG CODER.
 * Created on: 26/9/2017
 */

public class ContactAdapter extends BaseAdapter {
    private List<Contact> mListContact;
    private LayoutInflater mInflater;
    private Context mContext;

    public ContactAdapter(Context context, List<Contact> listContact) {
        mListContact = listContact;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setListContact(List<Contact> listContact) {
        mListContact = listContact;
    }

    @Override
    public int getCount() {
        return mListContact.size();
    }

    @Override
    public Contact getItem(int position) {
        return mListContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.itemc_contact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Contact contact = mListContact.get(position);

        viewHolder.tvPhone.setText(contact.getPhoneNumber());
        viewHolder.tvName.setText(contact.getName());
        if (contact.getAvatar() != null) {
            viewHolder.ivAvatar.setImageBitmap(contact.getAvatar());
        } else {
            viewHolder.ivAvatar.setImageResource(R.drawable.user);
        }

        convertView.findViewById(R.id.iv_calling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeACall(contact.getPhoneNumber());
            }
        });

        convertView.findViewById(R.id.iv_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAMessage(contact.getPhoneNumber());
            }
        });

        return convertView;
    }

    private void makeAMessage(String phoneNumber) {
        MessageDialog dialog = new MessageDialog(mContext, phoneNumber);
        dialog.show();
    }

    private void makeACall(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        mContext.startActivity(intent);
    }

    private class ViewHolder {
        ImageView ivAvatar;
        TextView tvName;
        TextView tvPhone;
    }
}
