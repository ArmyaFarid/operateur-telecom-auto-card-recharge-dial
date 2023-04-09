package com.moonlabcoding.tpoperateurtel.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moonlabcoding.tpoperateurtel.R;
import com.moonlabcoding.tpoperateurtel.model.Operator;
import com.moonlabcoding.tpoperateurtel.model.OperatorProvider;

import java.util.Arrays;

public class OperatorDialActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText _mEdtTypePhone;
    private EditText _mEdtTypeCode;
    private EditText _mEdtFullCodeAutoFill;
    private EditText _mEdtSoldeCodeAutoFill;

    private TextView _mTxtOperatorName;
    private TextView _mTxtTypeCodeLabel;

    private TextView _mTxtLoginInfo;

    private Button _mBtnRechargePlay;
    private Button _mBtnCheckSoldePlay;

    private OperatorProvider mOperatorProvider;
    private String mPhoneNumber;

    private void setEditTextMaxLength(EditText edtView,int length) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        edtView.setFilters(filterArray);
    }

    private void setTextViewTextColor(TextView textView , String colorHexaString){
        textView.setTextColor(Color.parseColor(colorHexaString));
    }

    private void setBackgroundColor(TextView textView,String colorHexaString){
        textView.setBackgroundColor(Color.parseColor(colorHexaString));
    }

    private void disableSomeViews(){
        _mEdtTypeCode.setEnabled(false);
        _mBtnRechargePlay.setEnabled(false);
        _mBtnCheckSoldePlay.setEnabled(false);
    }

    private void updateLayoutByOperator(Operator operator){

        setTextViewTextColor(_mTxtOperatorName,operator.getOperatorHexaColor());
        setBackgroundColor(_mEdtFullCodeAutoFill,operator.getOperatorHexaColor());
        setBackgroundColor(_mEdtSoldeCodeAutoFill,operator.getOperatorHexaColor());

        String operatorLabelText= "Votre operateur est "+operator.getOperatorName();
        _mTxtOperatorName.setText(operatorLabelText);

        String fullCodeGenerated = "*"+operator.getOperatorFormule()+"*"+_mEdtTypeCode.getText()+"#";
        _mEdtFullCodeAutoFill.setText(fullCodeGenerated);

        String rechargeCodeLabel= "Entrez votre code de recharge"+"("+operator.getOperatorCodeLength()+")";
        _mTxtTypeCodeLabel.setText(rechargeCodeLabel);

        String consulterSoldeFormule = "*"+operator.getOperatorSoldeFormule()+"#";
        _mEdtSoldeCodeAutoFill.setText(consulterSoldeFormule);
    }

    private void resetLayout(){
        _mTxtOperatorName.setText("");
        _mEdtFullCodeAutoFill.setText("");
        _mTxtTypeCodeLabel.setText("Entrez votre code de recharge");
        disableSomeViews();
    }


    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            View activeView= getCurrentFocus();
            String firstDigitsOfPhoneNumber;
            mPhoneNumber = String.valueOf(_mEdtTypePhone.getText());

            if(mPhoneNumber.length() >=2){
                firstDigitsOfPhoneNumber = mPhoneNumber.substring(0,2);
                Operator operator = mOperatorProvider.getOperator(firstDigitsOfPhoneNumber);
                if(operator != null){
                    _mBtnCheckSoldePlay.setEnabled(true);
                    updateLayoutByOperator(operator);
                    setEditTextMaxLength(_mEdtTypeCode,operator.getOperatorCodeLength());
                    if(_mEdtTypeCode.getText().length()>= operator.getOperatorCodeLength()){
                        _mBtnRechargePlay.setEnabled(true);
                    }else {
                        _mBtnRechargePlay.setEnabled(false);
                    }
                }else{
                    if(mPhoneNumber.length() < 5){
                        Toast.makeText(getApplicationContext(), "This operator not register in our database", Toast.LENGTH_SHORT).show();
                    }
                    _mBtnCheckSoldePlay.setEnabled(false);
                }

//                if (mPhoneNumber.length()==2){
//                    Operator operator = mOperatorProvider.getOperator(mPhoneNumber);
//                    if(operator != null){
//                        updateLayoutByOperator(operator);
//                        Toast.makeText(getApplicationContext(), operator.getOperatorName(), Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(getApplicationContext(), "This operator not register in our database", Toast.LENGTH_SHORT).show();
//                    }
//                }
                if(mPhoneNumber.length()==8){
                    _mEdtTypeCode.setEnabled(true);
                }else{
                    _mEdtTypeCode.setEnabled(false);
                }
            }else{
                resetLayout();
            }
        }
    };

    @Override
    public void onClick(View view) {
        if (view == _mBtnCheckSoldePlay) {
            //do something
            String code = String.valueOf(_mEdtSoldeCodeAutoFill.getText());
            code = code.replace("#", "%23");
            String uri = "tel:"+code;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        } else if (view == _mBtnRechargePlay) {
            String code = String.valueOf(_mEdtFullCodeAutoFill.getText());
            code = code.replace("#", "%23");
            String uri = "tel:"+code;
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));

//            intent.setData(Uri.parse(uri));
            startActivity(intent);
            //do something
        } else {
            throw new IllegalStateException("Unknown clicked view: " + view);
        }
    }

    public OperatorProvider getOperatorProvider(){
        Operator orange = new Operator("Orange","31","100","111","#ff8040",14);
        Operator oredoo = new Operator("Ooredoo","23","101","100","#FF0000",14);
        Operator tt = new Operator("Tunisie Telecom","97","123","122","#0000FF",13);
        return new OperatorProvider(Arrays.asList(orange,oredoo,tt));
    }

    private void connectViews() {
        _mEdtTypePhone = (EditText) findViewById(R.id.edt_type_phone);

        _mEdtTypeCode = (EditText) findViewById(R.id.edt_type_code);

        _mEdtFullCodeAutoFill = (EditText) findViewById(R.id.edt_fullCodeFill);

        _mEdtSoldeCodeAutoFill = (EditText) findViewById(R.id.edt_soldeCodeAutoFill);

        _mTxtOperatorName = (TextView) findViewById(R.id.txt_operatorName);

        _mTxtTypeCodeLabel = (TextView) findViewById(R.id.txt_type_code_label);

        _mTxtLoginInfo = (TextView) findViewById(R.id.txt_loginInfo);

        _mBtnRechargePlay = (Button) findViewById(R.id.btn_play_recharge);

        _mBtnCheckSoldePlay = (Button) findViewById(R.id.btn_play_checkSolde);
    }

    private void setSharedOnClickListenerOnViews(View... views) {
        for (View view : views) {
            view.setOnClickListener(OperatorDialActivity.this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_dial);
        connectViews();
        Bundle extras = getIntent().getExtras();
        String loggedUserName = extras.getString("username");
        String greetUserText= "Hello "+loggedUserName;
        _mTxtLoginInfo.setText(greetUserText);
        mOperatorProvider = getOperatorProvider();
        disableSomeViews();
//        _mEdtTypeCode.setEnabled(false);
//        _mBtnRechargePlay.setEnabled(false);
//        _mBtnCheckSoldePlay.setEnabled(false);
        _mEdtTypePhone.addTextChangedListener(mTextWatcher);
        _mEdtTypeCode.addTextChangedListener(mTextWatcher);
        setSharedOnClickListenerOnViews(_mBtnRechargePlay,_mBtnCheckSoldePlay);
    }




}