package com.ingresos.user.studentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OTP_Activity extends AppCompatActivity {

    EditText eT_otp;
    Button Otp_submit;
    String str_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        eT_otp = (EditText)findViewById(R.id.otp);


        Otp_submit = (Button)findViewById(R.id.otp_submit);
        Otp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_otp = eT_otp.getText().toString();

                if (str_otp.length() == 0) {
                    eT_otp.setError("Please Enter Your OTP");
                    eT_otp.requestFocus();
                    return;
                }
                else {

                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);

                    eT_otp.setText("");
                }

            }
        });


    }
}
