package com.example.tiffo

import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher(private val onTextChanged:()->Unit): TextWatcher {
    override fun afterTextChanged(s: Editable?) {
         if(!s.isNullOrEmpty()){
             onTextChanged()
         }
    }

    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {

    }

    override fun onTextChanged(
        p0: CharSequence?,
        p1: Int,
        p2: Int,
        p3: Int
    ) {

      }
}