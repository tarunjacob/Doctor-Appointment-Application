package mydoctor.code.in.mydoctor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DiseaseActivity extends AppCompatActivity {

    EditText ed;
    RadioGroup r;
    RadioButton r1;
    Button bt,bt1;
    PatientDataBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        this.ed = (EditText)findViewById(R.id.editText14);

        this.r = (RadioGroup) findViewById(R.id.radioGroup);

        this.bt = (Button)findViewById(R.id.button4);
        this.bt1 = (Button)findViewById(R.id.button6);

        this.db = new PatientDataBaseAdapter(DiseaseActivity.this);
        db = db.open();

        final SharedPreferences pref = getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiseaseActivity.this, AppointmentActivity.class);
                startActivity(intent);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.open();

                int id2 = r.getCheckedRadioButtonId();
                r1 = (RadioButton)findViewById(id2);

                String s8 = r1.getText().toString();
                String s9 = ed.getText().toString();

                db.insertEntry1(s8,s9);

                SharedPreferences.Editor editor = pref.edit();

                editor.putString("category",s8);
                editor.putString("reason",s9);
                editor.commit();
                Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_SHORT).show();

                db.close();
            }
        });
    }
}
