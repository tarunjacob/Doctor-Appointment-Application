package mydoctor.code.in.mydoctor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DoctorAppointmentActivity extends AppCompatActivity {

    TextView t1;
    Button bt;
    DoctorDataBaseAdapter db;
    ArrayAdapter ad;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        db = new DoctorDataBaseAdapter(DoctorAppointmentActivity.this);
        db = db.open();

        this.t1 = (TextView)findViewById(R.id.textView44);

        this.bt = (Button)findViewById(R.id.button12);

        lv = (ListView)findViewById(R.id.listView);

        Cursor c = db.getAllEntry();

        StringBuilder builder = new StringBuilder();

        while (c.moveToNext()) {

            String Category = c.getString(0);

            String Time = c.getString(1); // the value 0,1 ,2,3,4 represent column numbers  of database


           /* builder.append(Category).append("    ");

            builder.append(Time).append("     ");

            builder.append(Doctor).append("\n");

            t1.setText(builder); */

            String[] data = new String[]{"The Category was: "+Category,"The Time was: "+Time};

            ad = new ArrayAdapter(this,android.R.layout.simple_list_item_2,android.R.id.text2,data);
            lv.setAdapter(ad);


        }

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

    }
}
