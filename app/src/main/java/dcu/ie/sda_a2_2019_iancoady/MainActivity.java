package dcu.ie.sda_a2_2019_iancoady;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final    String      TAG = "MainActivity";
    static final            int         REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle details = getIntent().getExtras();
        TextView emailDetails = (TextView)findViewById(R.id.emailDetails);
        TextView tv = (TextView)findViewById(R.id.textView) ;

        if(details == null){
            Log.i(TAG, "No details to print");
        }
        else {
            String email = details.getString("Email", "ian.coady3@mail.dcu.ie");
            String subject = details.getString("Subject", "SDA Email");
            String content = details.getString("Content", "Hello World");

            Log.i(TAG, email); //Log msg to ensure the values we're being passed from the other activity correctly


            emailDetails.setText(String.format("%s\n%s\n%s", email, subject, content));
            //tv.setText("hello World"); Sample setText I was using to test my code as the text view entered above would not update at first.

            Log.i(TAG, emailDetails.getText().toString());
        }

        TextView callExplicitButton = findViewById(R.id.openActivityIntent);
        callExplicitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Demonstrating the use of onClickListener as opposed to XML onClick
            public void onClick(View v) {
                Intent openExplicit = new Intent(getApplicationContext(),ExplicitIntent.class);
                startActivity(openExplicit);
            }
        });

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(details == null){
                    Log.i(TAG, "No details to print");
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_SUBJECT, details.getString("Subject"));
                    intent.putExtra(Intent.EXTRA_TEXT, details.getString("Content"));
                    intent.setData(Uri.parse("mailto:" +details.getString("Email")));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        }));
    }

    public void openCamera(View view) {

        //Ref: https://developer.android.com/training/camera/photobasics
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePhoto.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePhoto,REQUEST_IMAGE_CAPTURE);
        }
    }

    public void openGallery(View view) {

        //Ref: https://stackoverflow.com/questions/16928727/open-gallery-app-from-android-intent
        Intent openPhotos = new Intent();
        openPhotos.setAction(android.content.Intent.ACTION_VIEW);
        openPhotos.setType("image/*");
        openPhotos.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openPhotos);
    }
}
