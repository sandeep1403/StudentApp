package com.ingresos.user.studentapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ingresos.user.studentapp.DataBase.DatabaseHelper;
import com.ingresos.user.studentapp.Model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements Imageutils.ImageAttachmentListener {

    EditText full_name, email, password, mobile_no;
    Button signup;
    ImageView profile_image;
    String str_fullname, str_email, str_password, str_mobile_no;

    Imageutils imageutils;
    private Bitmap bitmap;
    String file_name;
    String img_str;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkFirstLogin();
        prepareForm();

        databaseHelper = new DatabaseHelper(this);
        user = new User();


        signup = (Button) findViewById(R.id.signup);
        imageutils = new Imageutils(this);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageutils.imagepicker(1);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_fullname = full_name.getText().toString();
                str_email = email.getText().toString();
                str_password = password.getText().toString();
                str_mobile_no = mobile_no.getText().toString();

                if (str_fullname.length() == 0) {
                    full_name.setError("Please Enter Name");
                    full_name.requestFocus();
                    return;
                }
                if (str_email.length() == 0) {
                    email.setError("Please Enter Email Id");
                    email.requestFocus();
                    return;
                }

                if (emailValidator(email.getText().toString())) {
                } else {
                    email.setError("Please Enter Correct Email Id");
                    email.requestFocus();
                    return;

                }
                if (str_password.length() == 0) {
                    password.setError("Please Enter Password");
                    password.requestFocus();

                    return;

                }
                if (str_mobile_no.length() == 0) {
                    mobile_no.setError("Please Enter Mobile No");
                    mobile_no.requestFocus();

                } else {

                    user.setName(str_fullname);
                    user.setEmail(str_email);
                    user.setPassword(str_password);
                    user.setMobileno(str_mobile_no);

                    databaseHelper.addUser(user);

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);


                    full_name.setText("");
                    email.setText("");
                    password.setText("");
                    mobile_no.setText("");

                }

            }

        });

    }


    private void prepareForm() {

        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        full_name = (EditText) findViewById(R.id.full_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        mobile_no = (EditText) findViewById(R.id.mobileno);
        profile_image = (ImageView) findViewById(R.id.profile_image);

      // If value for key not exist then return second param value - In this case "..."
        full_name.setText(preferences.getString("username", "..."));
        mobile_no.setText(preferences.getString("userphone", "..."));
        email.setText(preferences.getString("useremail", "..."));
        img_str = preferences.getString("userphoto", "");
        if (!img_str.equals("")) {
            //decode string to image
            byte[] imageAsBytes = Base64.decode(img_str.getBytes(), Base64.DEFAULT);
            profile_image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        }

    }

    private void checkFirstLogin() {
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        // If value for key not exist then return second param value - In this case true
        if (preferences.getBoolean("firstLogin", true)) {
            initProfile();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstLogin", false);
            editor.apply();
        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

    }

    private void initProfile() {
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "");
        editor.putString("userphone", "");
        editor.putString("useremail", "");
        editor.apply();
    }

    public static boolean emailValidator(final String mailAddress) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mailAddress);
        return matcher.matches();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageutils.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        imageutils.request_permission_result(requestCode, permissions, grantResults);
    }

    @Override
    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        this.bitmap = file;
        this.file_name = filename;
        profile_image.setImageBitmap(file);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image = stream.toByteArray();
        //System.out.println("byte array:"+image);
        //final String img_str = "data:image/png;base64,"+ Base64.encodeToString(image, 0);
        //System.out.println("string:"+img_str);
        String img_str = Base64.encodeToString(image, 0);

        String path = Environment.getExternalStorageDirectory() + File.separator + "ImageAttach" + File.separator;
        SharedPreferences preferences = getSharedPreferences("myprefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userphoto", img_str);
        editor.apply();
        imageutils.createImage(file, filename, path, false);

    }

}

