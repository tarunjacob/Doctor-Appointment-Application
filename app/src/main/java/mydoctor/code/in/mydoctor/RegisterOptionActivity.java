package mydoctor.code.in.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterOptionActivity extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_option);

        this.b1 = (Button) findViewById(R.id.button19);
        this.b2 = (Button) findViewById(R.id.button20);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterOptionActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterOptionActivity.this,DoctorRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
