package com.example.numberguess

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

@SuppressLint("StaticFieldLeak")
private lateinit var btnBet : Button
@SuppressLint("StaticFieldLeak")
private lateinit var btnRefresh: Button
@SuppressLint("StaticFieldLeak")
private lateinit var betBal : TextView
private lateinit var editText : TextInputEditText
private lateinit var editText2 : TextInputEditText
private var bal : Int  = 1000
class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        betBal = findViewById(R.id.tvArea)
        betBal.text = "BALANCE $bal"

        editText = findViewById(R.id.betNumber)
        editText2 = findViewById(R.id.betAmount)

        btnBet = findViewById(R.id.btnBet)
        btnRefresh =findViewById(R.id.btnRefresh)
        if (bal <= 100){
            btnRefresh.isVisible = true
            btnRefresh.setOnClickListener{
                bal = 1000
                betBal.text = "BALANCE $bal"
            }
        }else{
            btnRefresh.isVisible = false
        }

        btnBet.setOnClickListener{
            if (editText.text == null || editText.text.toString().trim().isEmpty()){
                Toast.makeText(this, "Please Enter your Bet Number.", Toast.LENGTH_SHORT).show()
            }else if(editText2.text == null || editText2.text.toString().trim().isEmpty()){
                Toast.makeText(this, "Please Enter Bet Amount.", Toast.LENGTH_SHORT).show()
            }else if(editText.text.toString().toInt() > 10 || editText.text.toString().toInt() < 0){
                Toast.makeText(this, "Enter a number Between 0-10.", Toast.LENGTH_SHORT).show()
            }else if(editText2.text.toString().toInt() > bal || editText2.text.toString().toInt() < 0){
                Toast.makeText(this, "Enter a bet amount not less than zero or greater than your Balance.", Toast.LENGTH_SHORT).show()
            }else{
                if (bal - editText2.text.toString().toInt() <= 150){
                    btnRefresh.isVisible = true
                    btnRefresh.setOnClickListener{
                        btnRefresh.isVisible = false
                        bal = 1000
                        betBal.text = "BALANCE $bal"
                    }
                }
                bal -= editText2.text.toString().toInt()
                betBal.text = "BALANCE $bal"

                val num = Random.nextInt(0, 10)

                if(editText.text.toString().toInt() == num){
                    val win = editText2.text.toString().toInt() * 2
                    bal += win
                    betBal.text = "BALANCE $bal"
                    Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "You lost! Lucky number was $num", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}