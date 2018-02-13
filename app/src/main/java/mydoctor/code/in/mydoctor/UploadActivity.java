package mydoctor.code.in.mydoctor;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {

    Button b1,b2;
    ImageView imageView;
    ReportDataBaseAdapter dba;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "UploadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        this.b1 = (Button) findViewById(R.id.button13);
        this.b2 = (Button) findViewById(R.id.button16);

        this.dba = new ReportDataBaseAdapter(UploadActivity.this);
        dba = dba.open();

        this.imageView = (ImageView) findViewById(R.id.imageView2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UploadActivity.this);

                // set title
                alertDialogBuilder.setTitle("Alert");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you want to Upload this Report..??")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                UploadActivity.this.finish();
                                Intent intent = new Intent(UploadActivity.this,ContinueActivity.class);
                                startActivity(intent);
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
    }

    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    imageView.setImageURI(selectedImageUri);

                    InputStream iStream = null;
                    try {
                        iStream = getContentResolver().openInputStream(selectedImageUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        byte[] inputData = getBytes(iStream);
                        dba.insertEntry("Reports", inputData);

                        Toast.makeText(getApplicationContext(),"Report Uploaded Successfully",Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public byte[] getBytes(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}
