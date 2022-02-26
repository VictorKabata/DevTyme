package com.vickikbt.devtyme.android.ui.screens.webview_screen

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewScreen(url: String = "https://google.com/") {

    Box(modifier = Modifier.fillMaxWidth()) {
        AndroidView(factory = {
            WebView(it).apply {

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return false
                    }
                }
            }
        }, update = {
            it.loadUrl(url)
        })
    }
}
