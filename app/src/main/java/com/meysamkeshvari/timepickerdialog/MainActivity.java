package com.meysamkeshvari.timepickerdialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Calendar calendar = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Button openTimePickerDialog = findViewById(R.id.button);
        openTimePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1 ;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d("DBG",year + " " + month + " " + day + " ");
    }

    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is", "" + newVal);
    }

    public void show() {

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Number Picker");
        dialog.setContentView(R.layout.dialog_time);
        Button setButton = dialog.findViewById(R.id.button1);
        Button setCancel = dialog.findViewById(R.id.button2);
        final NumberPicker numberPickerHour = dialog.findViewById(R.id.numberPickerHour);
        final NumberPicker numberPickerMinute = dialog.findViewById(R.id.numberPickerMinute);
        //Hour
        numberPickerHour.setMaxValue(23);
        numberPickerHour.setMinValue(0);
        numberPickerHour.setWrapSelectorWheel(true);
        //Minute
        numberPickerMinute.setMaxValue(59);
        numberPickerMinute.setMinValue(0);
        numberPickerMinute.setWrapSelectorWheel(true);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hour = numberPickerHour.getValue();
                int minute = numberPickerMinute.getValue();

                if (hour == 24  && minute == 0) {
                    textView.setText("00" + ":" + "00");
                } else if (hour == 0  && minute == 0) {
                    textView.setText("00" + ":" + "00");
                } else if (hour == 24 && minute > 9  ) {
                    textView.setText("00" + ":" + minute);
                } else if (hour == 24 && minute < 10){
                    textView.setText("00" + ":" + "0" + minute);
                }else if (hour < 10 && minute > 9){
                    textView.setText("0" + hour + ":" + minute);
                }else if (hour > 9 && minute < 10){
                    textView.setText(hour + ":" + "0" + minute);
                }else if (hour < 10 && minute < 10){
                    textView.setText("0" + hour + ":" + "0" + minute);
                } else {
                    textView.setText(hour + ":" + minute);
                }
                dialog.dismiss();
            }
        });
        setCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
