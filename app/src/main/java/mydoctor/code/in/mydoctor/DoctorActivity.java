package mydoctor.code.in.mydoctor;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorActivity extends AppCompatActivity {

    TextView t1,t2;
    EditText e1;
    Button bt;

    DoctorDataBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        db = new DoctorDataBaseAdapter(DoctorActivity.this);
        db = db.open();



        this.t1 = (TextView)findViewById(R.id.textView38);
        this.t2 = (TextView)findViewById(R.id.textView41);
        this.e1 = (EditText)findViewById(R.id.editText15);
        this.bt = (Button)findViewById(R.id.button8);

        final SharedPreferences pref = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
        String s1 = pref.getString("category", "");
        String s2 = pref.getString("time", "");

        t1.setText(s1);
        t2.setText(s2);






            bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.insertEntry(t1.getText().toString(),t2.getText().toString(),e1.getText().toString());

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DoctorActivity.this);

                // set title
                alertDialogBuilder.setTitle("Confirmation");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you want to Book an Appointment with "+ e1.getText().toString()+" at "+ t2.getText().toString() + "..???")
                        .setCancelable(false)
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                DoctorActivity.this.finish();
                                Toast.makeText(getApplicationContext(),"Appointment Booked Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DoctorActivity.this,ContinueActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
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

                db.close();


            }
        });
    }
}
