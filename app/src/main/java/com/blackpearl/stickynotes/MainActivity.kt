package com.blackpearl.stickynotes

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.content.res.ResourcesCompat
import com.blackpearl.stickynotes.databinding.ActivityMainBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        binding?.apply{

            val stickyNote = StickyNote()


            fabSave.setOnClickListener {

                stickyNote.setStick(edtTxtAddNote.text.toString(), this@MainActivity)

                updateWidget()

                MotionToast.Companion.createColorToast(
                    this@MainActivity,
                    "Success",
                    "Note has been updated!",
                    MotionToastStyle.SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.SHORT_DURATION,
                    ResourcesCompat.getFont(this@MainActivity, R.font.poppins_medium)
                )
            }

//            btnBold.setOnClickListener {
//                btnItalic.setTextColor(resources.getColor(R.color.font_color))
//                btnItalic.setBackgroundResource(R.drawable.edit_text_bg)
//                if(edtTxtAddNote.typeface.isBold){
//                    edtTxtAddNote.typeface = Typeface.DEFAULT
//                    btnBold.setTextColor(resources.getColor(R.color.font_color))
//                    btnBold.setBackgroundResource(R.drawable.edit_text_bg)
//                }
//                else{
//                    btnBold.setTextColor(resources.getColor(R.color.white))
//                    btnBold.setBackgroundResource(R.drawable.selected_btn_bg)
//                    edtTxtAddNote.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
//                }
//            }
//
//            btnUnderline.setOnClickListener {
//                if(edtTxtAddNote.paintFlags == 8){
//                    btnUnderline.setTextColor(resources.getColor(R.color.font_color))
//                    btnUnderline.setBackgroundResource(R.drawable.edit_text_bg)
//                    edtTxtAddNote.paintFlags = (edtTxtAddNote.paintFlags) or (Paint.UNDERLINE_TEXT_FLAG)
//                }
//                else{
//                    btnUnderline.setTextColor(resources.getColor(R.color.white))
//                    btnUnderline.setBackgroundResource(R.drawable.selected_btn_bg)
//                    edtTxtAddNote.paintFlags = Paint.UNDERLINE_TEXT_FLAG
//                }
//            }
//
//            btnItalic.setOnClickListener {
//                btnBold.setTextColor(resources.getColor(R.color.font_color))
//                btnBold.setBackgroundResource(R.drawable.edit_text_bg)
//                if(edtTxtAddNote.typeface.isBold){
//                    edtTxtAddNote.typeface = Typeface.DEFAULT
//                    btnItalic.setTextColor(resources.getColor(R.color.font_color))
//                    btnItalic.setBackgroundResource(R.drawable.edit_text_bg)
//                }
//                else{
//                    btnItalic.setTextColor(resources.getColor(R.color.white))
//                    btnItalic.setBackgroundResource(R.drawable.selected_btn_bg)
//                    edtTxtAddNote.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
//                }
//            }
        }
    }

    private fun updateWidget(){
        val appWidgetManager = AppWidgetManager.getInstance(this@MainActivity)
        val remoteViews = RemoteViews(this@MainActivity.packageName, R.layout.widget_layout)
        val thisWidget = ComponentName(this@MainActivity, AppWidget::class.java)
        remoteViews.setTextViewText(R.id.tvWidget, binding!!.edtTxtAddNote.text.toString())
        appWidgetManager.updateAppWidget(thisWidget, remoteViews)
    }

    override fun onBackPressed() {
        finish()
    }
}