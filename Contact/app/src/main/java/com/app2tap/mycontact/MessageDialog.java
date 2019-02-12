package com.app2tap.mycontact;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Project: MyContact.
 * Author: HUNG CODER.
 * Created on: 27/9/2017
 */

public class MessageDialog extends Dialog {
    private EditText mEdtSmsContent;

    public MessageDialog(@NonNull Context context, String phoneNumber) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_sms);

        initView(phoneNumber);
    }

    private void initView(final String phoneNumber) {
        TextView tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvPhone.setText("Gửi đến: " + phoneNumber);
        mEdtSmsContent = (EditText) findViewById(R.id.edt_sms_content);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, mEdtSmsContent.getText().toString(), null, null);
                dismiss();
            }
        });
    }
}
