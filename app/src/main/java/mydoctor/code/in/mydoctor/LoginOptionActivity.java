package mydoctor.code.in.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOptionActivity extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);

        this.b1 = (Button) findViewById(R.id.button17);
        this.b2 = (Button) findViewById(R.id.button18);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOptionActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginOptionActivity.this,DoctorLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
