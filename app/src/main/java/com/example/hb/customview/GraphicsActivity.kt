package com.example.hb.customview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_graphics.*
import org.jetbrains.anko.*

class GraphicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphics)

        // Open a dialog on button two
        secondButton.setOnClickListener() {
            alert("Yo") {
                yesButton {
                    longToast("You said YES")
                }
                noButton {  }
            }.show()
        }

        // Open navigator to chillcoding on button three
        thirdButton.setOnClickListener() {
            browse("https://www.chillcoding.com/")
        }

        // Add a toast when user is writing in the input text
        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                toast("You're writing ^^")
            }
        })

    }
}
