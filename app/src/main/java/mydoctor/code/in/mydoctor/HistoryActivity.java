package mydoctor.code.in.mydoctor;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    TextView t1;
    Button bt;
    DoctorDataBaseAdapter db;
    ArrayAdapter ad;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        db = new DoctorDataBaseAdapter(HistoryActivity.this);
        db = db.open();

        this.t1 = (TextView)findViewById(R.id.textView44);

        this.bt = (Button)findViewById(R.id.button12);

        lv = (ListView)findViewById(R.id.listView);

        Cursor c = db.getAllEntry();

        StringBuilder builder = new StringBuilder();

        while (c.moveToNext()) {

            String Category = c.getString(0);

            String Time = c.getString(1); // the value 0,1 ,2,3,4 represent column numbers  of database

            String Doctor = c.getString(2);

            String[] data = new String[]{"The Category was: "+Category,"The Time was: "+Time,"The Doctor was: "+Doctor};

            ad = new ArrayAdapter(this,android.R.layout.simple_list_item_2,android.R.id.text2,data);
            lv.setAdapter(ad);


        }

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this,ContinueActivity.class);
                startActivity(intent);
            }
        });

    }
}
