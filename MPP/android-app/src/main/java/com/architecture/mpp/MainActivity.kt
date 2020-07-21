package com.architecture.mpp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import domain.model.MonumentMainItemDomain
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val monument = MonumentMainItemDomain(1,"La sagrada familia","1-1")
        textExample.text = monument.title
        AndroidHelloWorld.print()
    }
}