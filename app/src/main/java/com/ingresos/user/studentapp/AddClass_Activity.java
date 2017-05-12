package com.ingresos.user.studentapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ingresos.user.studentapp.DataBase.DatabaseHelper;
import com.ingresos.user.studentapp.Model.Class_Model;

import java.util.Calendar;

public class AddClass_Activity extends AppCompatActivity {
    EditText addclass_name,addclass_location,addclass_payment,addclass_starttime,addclass_endtime,
            addclass_startdate,addclass_enddate;
    Button submit_details;
    String str_addclass_name,str_addclass_location,str_addclass_payment,str_addclass_starttime,
            str_addclass_endtime,str_addclass_startdate,str_addclass_enddate;
    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID1 = 1;
    int cur = 0;
    private DatabaseHelper databaseHelper;
    private Class_Model class_model;


    private int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        databaseHelper = new DatabaseHelper(this);
        class_model = new Class_Model();

        Calendar c=Calendar.getInstance();
        mYear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);

        addclass_name = (EditText)findViewById(R.id.addclass_name);
        addclass_location = (EditText)findViewById(R.id.addclass_location);
        addclass_payment = (EditText)findViewById(R.id.addclass_payment);
        addclass_starttime = (EditText)findViewById(R.id.addclass_starttime) ;
        addclass_endtime = (EditText)findViewById(R.id.addclass_endtime) ;
        addclass_startdate = (EditText)findViewById(R.id.addclass_startdate) ;
        addclass_enddate = (EditText)findViewById(R.id.addclass_enddate) ;


        submit_details = (Button) findViewById(R.id.submit_details);

        addclass_startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        addclass_enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID1);
            }
        });


        addclass_starttime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddClass_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        addclass_starttime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        addclass_endtime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddClass_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        addclass_endtime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        submit_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_addclass_name = addclass_name.getText().toString();
                str_addclass_location = addclass_location.getText().toString();
                str_addclass_payment = addclass_payment.getText().toString();
                str_addclass_starttime = addclass_starttime.getText().toString();
                str_addclass_endtime = addclass_endtime.getText().toString();
                str_addclass_startdate = addclass_startdate.getText().toString();
                str_addclass_enddate = addclass_enddate.getText().toString();




                if (str_addclass_name.length()==0){
                    addclass_name.setError("Enter Class No");
                    addclass_name.requestFocus();
                    return;
                }
                if (str_addclass_location.length()==0){
                    addclass_location.setError("Enter Class Location");
                    addclass_location.requestFocus();
                    return;
                }
                if (str_addclass_payment.length()==0){
                    addclass_payment.setError("Enter Class Payment");
                    addclass_payment.requestFocus();
                    return;
                }
                if (str_addclass_starttime.length()==0){
                    addclass_starttime.setError("Enter Class  Start Time");
                    addclass_starttime.requestFocus();
                    return;
                }
                if (str_addclass_endtime.length()==0){
                    addclass_endtime.setError("Enter Class  End time");
                    addclass_endtime.requestFocus();
                    return;
                }
                if (str_addclass_startdate.length()==0){
                    addclass_startdate.setError("Enter Class  Start date");
                    addclass_startdate.requestFocus();
                    return;
                }
                if (str_addclass_enddate.length()==0){
                    addclass_enddate.setError("Enter Class  End date");
                    addclass_enddate.requestFocus();

                }

                else {

                    class_model.setClass_no(str_addclass_name);
                    class_model.setClass_location(str_addclass_location);
                    class_model.setClass_payment(str_addclass_payment);
                    class_model.setClass_start_time(str_addclass_starttime);
                    class_model.setClass_end_time(str_addclass_endtime);
                    class_model.setClass_start_date(str_addclass_startdate);
                    class_model.setClass_end_date(str_addclass_enddate);


                    databaseHelper.addClasses(class_model);



                    Toast.makeText(getApplicationContext(),"Thanku....",Toast.LENGTH_LONG).show();
                    AddClass_Activity.this.finish();

                    addclass_name.setText("");
                    addclass_location.setText("");
                    addclass_payment.setText("");
                    addclass_starttime.setText("");
                    addclass_endtime.setText("");
                    addclass_startdate.setText("");
                    addclass_enddate.setText("");



                }



            }
        });


    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                cur = DATE_DIALOG_ID;
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);

            case DATE_DIALOG_ID1:
                cur = DATE_DIALOG_ID1;
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }

        return null;

    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            if(cur==DATE_DIALOG_ID){

                addclass_startdate.setText(new StringBuilder().append(mDay).append("/").append(mMonth+1).append("/").append(mYear));
            }
            else {
                addclass_enddate.setText(new StringBuilder().append(mDay).append("/").append(mMonth+1).append("/").append(mYear));
            }



        }

    };

}
