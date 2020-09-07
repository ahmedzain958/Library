package com.zainco.library.androiddevsrunningtracker.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

/**
 * Created by Ahmed Zain on 7/24/2020.
 */
class Converters {
    @TypeConverter/*tells room that they are type converter functions*/
    //if room has a bitmap object and normally don't know how to save in db, and it simply found a type converter function that tells it how to convert this object
    //it will simply use that function
    fun fromBitmapToByteArray(bmp: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bmp.compress(
            Bitmap.CompressFormat.PNG,
            100/*full quality*/,
            outputStream/*bitmap bytes will be saved in this output stream*/
        )
        return outputStream.toByteArray()
    }

    @TypeConverter/*tells room that they are type converter functions*/
    fun convertByteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0/*no offset*/, /*length */ byteArray.size)
    }
}