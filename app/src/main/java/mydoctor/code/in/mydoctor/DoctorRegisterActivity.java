package mydoctor.code.in.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorRegisterActivity extends AppCompatActivity {

    Button bt;
    EditText e1,e2,e3,e4,e5;
    DoctorAppDataBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        db = new DoctorAppDataBaseAdapter(DoctorRegisterActivity.this);
        db = db.open();

        this.e1 = (EditText)findViewById(R.id.editText3);
        this.e2 = (EditText)findViewById(R.id.editText4);
        this.e3 = (EditText)findViewById(R.id.editText5);
        this.e4 = (EditText)findViewById(R.id.editText6);
        this.e5 = (EditText)findViewById(R.id.editText7);

        this.bt = (Button)findViewById(R.id.button2);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.open();

                db.insertEntry(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
                Intent intent = new Intent(DoctorRegisterActivity.this,MainActivity.class);
                Toast.makeText(getApplicationContext(),"Registered Successful",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                db.close();
            }
        });
    }
}
