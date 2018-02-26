package com.example.surjit.accadmictask1;

import android.graphics.Bitmap;

/**
 * Created by surjit on 26-02-2018.
 */

public class DataClass {

    private Bitmap bitmap;
    private String text;

    DataClass(Bitmap bitmap, String a)
    {
        this.bitmap = bitmap;
        this.text = a;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
