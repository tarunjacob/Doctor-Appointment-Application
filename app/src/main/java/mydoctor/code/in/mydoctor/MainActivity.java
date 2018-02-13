package mydoctor.code.in.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.b1 = (Button) findViewById(R.id.loginbutton);
        this.b2 = (Button) findViewById(R.id.registerbutton);


        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginOptionActivity.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterOptionActivity.class);
                startActivity(intent);
            }
        });
    }
}
