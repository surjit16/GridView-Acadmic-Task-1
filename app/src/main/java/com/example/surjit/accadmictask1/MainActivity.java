package com.example.surjit.accadmictask1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    CustomAdapter mAdapter;
    GridView gridView;
    ArrayList<DataClass> list = new ArrayList<>();
    ArrayList<DataClass> newlist = new ArrayList<>();

    int REQUEST_CAMERA = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
        mAdapter = new CustomAdapter(this, R.layout.grid_view, list);
        gridView.setAdapter(mAdapter);
    }

    public void doClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        } else {
            startActivityForResult(intent, 90);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 90) {
            if (resultCode == RESULT_OK) {
                final Bitmap imageData = (Bitmap) data.getExtras().get("data");
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);

                final View  v = getLayoutInflater().inflate(R.layout.alert, null);
                builder.setView(v);
                builder.setTitle("Image Title");
                builder.setCancelable(false);

                AlertDialog dialog = builder.create();
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String a =((EditText)v.findViewById(R.id.editname)).getText().toString();
                        if (a.isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "Error Picture Not Captured/Stored", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            newlist.add(new DataClass(imageData, a));
                        }
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Error Picture Not Captured/Stored", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();

            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }

    public void doUpload(View view) {

        list = new ArrayList<>(newlist);
        mAdapter = new CustomAdapter(this, R.layout.grid_view, list);
        gridView.setAdapter(mAdapter);
    }

}
