package com.ingresos.user.studentapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ingresos.user.studentapp.DataBase.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText login_email, login_password;
    Button login;
    String str_loginemail, str_loginpassword;
    ImageView login_profile_image;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);
        login_profile_image = (ImageView) findViewById(R.id.login_profile_image);
        login = (Button) findViewById(R.id.login);

        databaseHelper = new DatabaseHelper(this);

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        byte[] imageAsBytes = Base64.decode(preferences.getString("userphoto", "").getBytes(), Base64.DEFAULT);
        login_profile_image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_loginemail = login_email.getText().toString();
                str_loginpassword = login_password.getText().toString();

                if (str_loginemail.length() == 0) {
                    login_email.setError("Please Enter Email Id");
                    login_email.requestFocus();
                    return;
                }

                if (emailValidator(login_email.getText().toString())) {
                } else {
                    login_email.setError("Please Enter Correct Email Id");
                    login_email.requestFocus();
                    return;

                }

                if (str_loginpassword.length() == 0) {
                    login_password.setError("Please Enter Password");
                    login_password.requestFocus();



                }

                if(!databaseHelper.checkUser(str_loginemail,str_loginpassword)){

                    Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_LONG).show();

                }
                else {

                    Intent intent = new Intent(getApplicationContext(), OTP_Activity.class);
                    startActivity(intent);

                    login_email.setText("");
                    login_password.setText("");
                }
            }
        });

    }

    public static boolean emailValidator(final String mailAddress) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailAddress);
        return matcher.matches();
    }

    public void onBackPressed() {
        // your code.
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
