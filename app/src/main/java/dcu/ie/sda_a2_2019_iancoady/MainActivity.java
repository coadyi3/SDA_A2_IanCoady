package dcu.ie.sda_a2_2019_iancoady;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
