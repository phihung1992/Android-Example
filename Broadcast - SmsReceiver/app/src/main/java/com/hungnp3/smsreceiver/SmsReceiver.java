package com.hungnp3.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;

/**
 * Created by HUNG on 1/7/2017.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            return;
        }
        Bundle bundle = intent.getExtras();
        Object[] data = (Object[]) bundle.get("pdus");

        String body = "";

        for (int i = 0; i < data.length; ++i) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) data[i]);
            body += smsMessage.getMessageBody();
        }
        String phone = SmsMessage.createFromPdu((byte[]) data[0]).getOriginatingAddress();
        long sentTime = SmsMessage.createFromPdu((byte[]) data[0]).getTimestampMillis();
        Toast.makeText(context, phone + "\n" +
                convertLongToTimeString(sentTime) + "\n" + body, Toast.LENGTH_LONG).show();
    }

    private String convertLongToTimeString(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        return dateFormat.format(time);
    }
}
