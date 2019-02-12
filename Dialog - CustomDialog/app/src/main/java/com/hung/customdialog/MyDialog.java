package com.hung.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

/**
 * Created by HUNG on 28/4/2017.
 */

public class MyDialog extends Dialog {
    private EditText edtName;

    public MyDialog(@NonNull Context context) {
        super(context);
        initView();
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_information);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        edtName = (EditText) findViewById(R.id.edt_name);
        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here and dismiss dialog
                dismiss();
            }
        });
    }
}
