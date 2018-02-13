package mydoctor.code.in.mydoctor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentActivity extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


        this.t1 = (TextView) findViewById(R.id.textView26);
        this.t2 = (TextView) findViewById(R.id.textView27);
        this.t3 = (TextView) findViewById(R.id.textView28);
        this.t4 = (TextView) findViewById(R.id.textView29);
        this.t5 = (TextView) findViewById(R.id.textView30);
        this.t6 = (TextView) findViewById(R.id.textView31);
        this.t7 = (TextView) findViewById(R.id.textView32);
        this.t8 = (TextView) findViewById(R.id.textView34);
        this.t9 = (TextView) findViewById(R.id.textView36);

        this.b2 = (Button) findViewById(R.id.button7);
        this.b1 = (Button) findViewById(R.id.button15);

        final SharedPreferences pref = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        String s1 = pref.getString("name", "");
        String s2 = pref.getString("age", "");
        String s3 = pref.getString("weight", "");
        String s4 = pref.getString("blood", "");
        String s5 = pref.getString("date", "");
        String s6 = pref.getString("time", "");
        String s7 = pref.getString("gender", "");
        String s8 = pref.getString("category", "");
        String s9 = pref.getString("reason", "");

        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        t4.setText(s4);
        t5.setText(s7);
        t6.setText(s5);
        t7.setText(s6);
        t8.setText(s8);
        t9.setText(s9);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_SHORT).show();
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AppointmentActivity.this);

                // set title
                alertDialogBuilder.setTitle("Alert");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you want to Continue..??")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                AppointmentActivity.this.finish();
                                Intent intent = new Intent(AppointmentActivity.this,DoctorActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

    }
}
