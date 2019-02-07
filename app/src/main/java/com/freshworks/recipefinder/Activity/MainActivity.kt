package com.freshworks.recipefinder.Activity

import android.app.DownloadManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.freshworks.recipefinder.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_row.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_search.setOnClickListener {
            startActivity(Intent(this,RecipeListActivity::class.java))
        }

    }
}
