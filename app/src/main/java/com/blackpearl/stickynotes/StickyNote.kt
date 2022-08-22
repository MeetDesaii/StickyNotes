package com.blackpearl.stickynotes

import android.content.Context
import android.widget.Toast
import java.io.*

class StickyNote {

    fun getStick(context : Context) : String{

        val file = File(context.filesDir.path+"StickyNote")

        val text = StringBuilder()

        try{
            val bufferedReader = BufferedReader(FileReader(file))

            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                text.append(line)
                text.append("\n")
            }
            bufferedReader.close()
        }
        catch (exception : IOException){
            Toast.makeText(context, "Something went wrong!",Toast.LENGTH_SHORT).show()
        }
        return text.toString()
    }

    fun setStick(textToBeSaved : String, context: Context){
        val text : String = textToBeSaved
        var outputStream : FileOutputStream? = null

        try{
            outputStream = context.applicationContext.openFileOutput("StickyNote.txt", Context.MODE_PRIVATE)
            outputStream.write(text.toByteArray())
        }
        catch (exception : IOException){
            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }
        finally {
            if(outputStream != null){
                try{
                    outputStream.close()
                }
                catch (exception : IOException){
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}