package com.e.correctioncircularimage;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private CircleImageView mProfileImage;
    private Button btChooseImage;
    private static final int PICK_IMAGE_REQUEST =1;
    private Uri mImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >22){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
        }

        mProfileImage = (CircleImageView)findViewById(R.id.iv_profile_image);
         //setting up the circular image
        mProfileImage.setCircleBackgroundColor(Color.WHITE);

        mProfileImage.setBorderColor(Color.WHITE);

        mProfileImage.setBorderWidth(2);

        mProfileImage.setImageResource(R.drawable.imagecircle4);

        mProfileImage.setBorderOverlay(true);

        btChooseImage =(Button)findViewById(R.id.bt_chooseimage_id);
        btChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChooseImage();
            }
        });



    }

    private void ChooseImage() {
        Intent chooseImageIntent = new Intent();

        chooseImageIntent.setType("image/*");

        chooseImageIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            mProfileImage.setImageURI(mImageUri);


        } else {

            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }
        }
    }
