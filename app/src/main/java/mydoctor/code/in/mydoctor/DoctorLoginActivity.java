package mydoctor.code.in.mydoctor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText password;
    EditText email;

    DoctorAppDataBaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);


        db = new DoctorAppDataBaseAdapter(DoctorLoginActivity.this);
        db=db.open();

        this.email = (EditText) findViewById(R.id.editText);
        this.password = (EditText) findViewById(R.id.editText2);
        this.loginButton = (Button) findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(password.getWindowToken(), 0);

                String s1 = email.getText().toString();
                String s2 = password.getText().toString();

                final ProgressDialog progressDialog = new ProgressDialog(DoctorLoginActivity.this,ProgressDialog.STYLE_SPINNER);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();


                try {
                    if (db.getSingleEntry(s1,s2)) {
                        Intent intent = new Intent(DoctorLoginActivity.this, DoctorAppointmentActivity.class);
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(DoctorLoginActivity.this, "Some problem occurred", Toast.LENGTH_SHORT).show();
                }


                db.close();

            }
        });
    }
}
