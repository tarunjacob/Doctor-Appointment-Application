package mydoctor.code.in.mydoctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContinueActivity extends AppCompatActivity {

    Button b1,b2,b3,b4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);

        this.b1 = (Button)findViewById(R.id.button9);
        this.b2 = (Button)findViewById(R.id.button10);
        this.b3 = (Button)findViewById(R.id.button11);
        this.b4 = (Button)findViewById(R.id.button14);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContinueActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContinueActivity.this,PatientActivity.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ContinueActivity.this);

                alertDialogBuilder.setTitle("Confirmation");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are You Sure You Want to Exit the Application..??")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                               // Toast.makeText(getApplicationContext(),"Thank You for Using the Application",Toast.LENGTH_SHORT).show();

                               // ContinueActivity.this.finish();
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);

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

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContinueActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });



    }

}
