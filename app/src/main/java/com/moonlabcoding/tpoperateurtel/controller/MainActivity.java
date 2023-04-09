package com.moonlabcoding.tpoperateurtel.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moonlabcoding.tpoperateurtel.R;
import com.moonlabcoding.tpoperateurtel.model.User;
import com.moonlabcoding.tpoperateurtel.model.UserProvider;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText _mEdtUserName;
    private EditText _mEdtPassword;
    private Button _mBtnLoginPlay;

    private UserProvider mUserProvider;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private UserProvider generateUsers(){
        return new UserProvider(Arrays.asList(new User("ben","Bfba22995544")));
    }

    private void login(String username , String password){
        User loggedUser = mUserProvider.logUser(username,password);
        if(loggedUser!=null){
            Intent NexIntent = new Intent(MainActivity.this,OperatorDialActivity.class);
            NexIntent.putExtra("username", loggedUser.getUserName());
            startActivity(NexIntent);
        }else {
            Log.d("myTag", "User not found");
            Toast.makeText(MainActivity.this,"User not found",Toast.LENGTH_LONG).show();
        }
    }

    private void connectViews(){
        _mEdtUserName = (EditText) findViewById(R.id.username);
        _mEdtPassword = (EditText) findViewById(R.id.password);
        _mBtnLoginPlay = (Button) findViewById(R.id.login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectViews();

        mUserProvider = generateUsers();

        _mEdtUserName.addTextChangedListener(mTextWatcher);
        _mEdtPassword.addTextChangedListener(mTextWatcher);

        _mBtnLoginPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(_mEdtUserName.getText());
                String pwd = String.valueOf(_mEdtPassword.getText());
                login(name,pwd);
            }
        });
    }


}