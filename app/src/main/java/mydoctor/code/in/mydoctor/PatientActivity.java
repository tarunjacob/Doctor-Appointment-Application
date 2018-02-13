package mydoctor.code.in.mydoctor;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PatientActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    RadioGroup r;
    RadioButton r1;
    Button bt, bt1;
    PatientDataBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        this.ed1 = (EditText) findViewById(R.id.editText8);
        this.ed2 = (EditText) findViewById(R.id.editText9);
        this.ed3 = (EditText) findViewById(R.id.editText10);
        this.ed4 = (EditText) findViewById(R.id.editText11);
        this.ed5 = (EditText) findViewById(R.id.editText12);
        this.ed6 = (EditText) findViewById(R.id.editText13);

        this.r = (RadioGroup) findViewById(R.id.radio);

        this.db = new PatientDataBaseAdapter(PatientActivity.this);
        db = db.open();

        this.bt = (Button) findViewById(R.id.button3);
        this.bt1 = (Button) findViewById(R.id.button5);

        final SharedPreferences pref = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);

        final Calendar c = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                ed5.setText(sdf.format(c.getTime()));
            }

        };

        ed5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PatientActivity.this, date, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        ed6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PatientActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ed6.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, DiseaseActivity.class);
                startActivity(intent);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.open();


                int id1 = r.getCheckedRadioButtonId();
                r1 = (RadioButton) findViewById(id1);

                String s1 = ed1.getText().toString();
                String s2 = ed2.getText().toString();
                String s3 = ed3.getText().toString();
                String s4 = ed4.getText().toString();
                String s5 = ed5.getText().toString();
                String s6 = ed6.getText().toString();
                String s7 = r1.getText().toString();

                db.insertEntry(ed1.getText().toString(),ed2.getText().toString(), ed3.getText().toString(),ed4.getText().toString() ,ed5.getText().toString() ,ed6.getText().toString() ,r1.getText().toString());

                SharedPreferences.Editor editor = pref.edit();

                editor.putString("name", s1);
                editor.putString("age", s2);
                editor.putString("weight", s3);
                editor.putString("blood", s4);
                editor.putString("date", s5);
                editor.putString("time", s6);
                editor.putString("gender", s7);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();


            }
        });

        db.close();
    }
}


