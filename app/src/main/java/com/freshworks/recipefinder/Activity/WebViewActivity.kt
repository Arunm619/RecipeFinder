package com.freshworks.recipefinder.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.freshworks.recipefinder.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        progressBar.visibility=View.VISIBLE

        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()



        webView.webViewClient = object  : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
            }
        }


        if (intent != null) {
            val url = intent.getStringExtra("source")
            if (url.isNotEmpty()) {
                webView.loadUrl(url)
            }
        }



    }
}
