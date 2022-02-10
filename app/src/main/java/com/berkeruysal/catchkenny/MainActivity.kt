package com.berkeruysal.catchkenny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var score:Int=0
    var imageArray=ArrayList <ImageView>()
    var runnable= Runnable {  }
    var handler:Handler=Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //IMAGE ARRAY
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        hideImages()
        //COUNT DOWN TIMER
        object:CountDownTimer(15500,1000)
        {
            override fun onTick(p0: Long) {
                timeView.text="Zaman: ${p0/1000}"
            }
                //ALERT DIALOG
            override fun onFinish() {
                timeView.text="Zaman: 0"
                    //Morty'nin hareket etmemesi için runnable'i durdurmamız gerekli
                    handler.removeCallbacks(runnable)
                    imageArray.forEach { it.visibility=View.INVISIBLE }

                var alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Süre Bitti!")
                alert.setMessage("Yeniden Oynamak İstiyor Musun?")
                alert.setPositiveButton("Evet",DialogInterface.OnClickListener{
                    dialogInterface, i ->var intent=intent
                    finish()
                    startActivity(intent)
                })
                alert.setNegativeButton("Hayır",DialogInterface.OnClickListener{
                    dialogInterface, i ->finish()
                })
                alert.show()

            }

        }.start()
    }
    //SKOR FUNCTION

    fun hideImages()
    {
        runnable=object :Runnable{
            override fun run() {
                var randomValues= Random
                var randomIndex=randomValues.nextInt(9)
                imageArray.forEach { it.visibility=View.INVISIBLE }
                for (i in 0..imageArray.size-1)
                {
                    if (i==randomIndex)
                    {
                        imageArray.get(i).setOnClickListener{
                            score++
                            scoreView.text="Skor: $score"
                        }
                        imageArray[i].visibility=View.VISIBLE
                        handler.postDelayed(runnable,300)
                    }
                }
            }

        }
        handler.post(runnable)
    }
}