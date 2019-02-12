package com.app2tap.mycontact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class ContactActivitiy extends AppCompatActivity implements TextWatcher, AdapterView.OnItemClickListener {
    private ContactManager mContactManager;
    private List<Contact> mListContact;
    private ListView mLvContact;
    private ContactAdapter mAdapter;
    private EditText mEdtSearch;
    private ImageView mIvSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initView();
    }

    private void initView() {
        mContactManager = new ContactManager(this);

        mListContact = mContactManager.getListContact();
        Collections.sort(mListContact);

        mLvContact = (ListView) findViewById(R.id.lv_contact);
        mAdapter = new ContactAdapter(this, mListContact);
        mLvContact.setAdapter(mAdapter);
        mLvContact.setOnItemClickListener(this);

        mEdtSearch = (EditText) findViewById(R.id.edt_search);
        mIvSearch = (ImageView) findViewById(R.id.iv_search);
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSearchContent();
            }
        });
        mEdtSearch.addTextChangedListener(this);
    }

    private void clearSearchContent() {
        if (!mEdtSearch.getText().toString().isEmpty()) {
            mEdtSearch.setText("");
            mIvSearch.setImageLevel(0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mContactManager.searchResult(mEdtSearch.getText().toString());
        mAdapter.setListContact(mContactManager.getListContact());
        mAdapter.notifyDataSetChanged();

        if (mEdtSearch.getText().toString().isEmpty()) {
            mIvSearch.setImageLevel(0);
            return;
        }
        mIvSearch.setImageLevel(1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
