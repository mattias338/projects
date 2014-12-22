package com.banken.simplexmltest;

import android.content.Context;
import android.util.Log;

import com.banken.simplexmltest.objects.Apple;
import com.banken.simplexmltest.objects.Level;
import com.banken.simplexmltest.objects.Orange;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by banken on 2014-12-07.
 */
public class WriteExample {
    private final static String TAG = WriteExample.class.getCanonicalName();

    public WriteExample(Context context) throws IOException {
        // Create the Dummy Level with Apples and Oranges
        Log.i(TAG, "Create level");
        Level level = new Level();
        Orange orange;
        level.gameObjects.add(new Apple(1, 10));
        orange = new Orange(2);
        orange.containedJuice = 20;
        level.gameObjects.add(orange);
        orange = new Orange(3);
        orange.containedJuice = 100;
        level.gameObjects.add(orange);
        level.gameObjects.add(new Apple(4, 0));

        // Now write the level out to a file
        Log.i(TAG, "Write Level to file.");
        Serializer serial = new Persister();
        File sdcardFile = new File(context.getFilesDir() + "xmlFileName.xml");//new File("/sdcard/levelout.xml");

        try {
            serial.write(level, sdcardFile);
        } catch (Exception e) {
            // There is the possibility of error for a number of reasons. Handle this appropriately in your code
            e.printStackTrace();
        }
        Log.i(TAG, "XML Written to File: " + sdcardFile.getAbsolutePath());

        BufferedReader reader = new BufferedReader(new FileReader(context.getFilesDir() + "xmlFileName.xml"));
        String line;
        while ((line = reader.readLine()) != null) {
            Log.i("M<attias: ", line);
        }
        reader.close();
    }
}
