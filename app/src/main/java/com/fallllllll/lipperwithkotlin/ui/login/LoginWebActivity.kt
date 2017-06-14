package com.fallllllll.lipperwithkotlin.ui.login

import android.content.Intent
import android.webkit.*
import com.fallllllll.lipperwithkotlin.R
import com.fallllllll.lipperwithkotlin.core.activity.BaseActivity
import com.fallllllll.lipperwithkotlin.core.constants.CALLBACK_URL
import com.fallllllll.lipperwithkotlin.core.constants.CLIENT_ID
import com.fallllllll.lipperwithkotlin.core.constants.LOGIN_URL
import com.fallllllll.lipperwithkotlin.core.expandFunction.getRandomString
import com.fallllllll.lipperwithkotlin.core.expandFunction.setOrdinaryToolbar
import kotlinx.android.synthetic.main.activity_login_web.*

/**
 * Created by fallllllll on 2017/6/14/014.
 * GitHub :  https://github.com/348476129/Lipper
 */
class LoginWebActivity : BaseActivity() {

    var isLoadUrl = false

    override fun initListeners() {
        webActivityToolbar.setNavigationOnClickListener { finish() }
    }

    override fun initViewAndData() {
        setContentView(R.layout.activity_login_web)
        setOrdinaryToolbar()
        setSupportActionBar(webActivityToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initWebView()

    }

    private fun initWebView() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.setAppCacheEnabled(true)
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.setSupportZoom(true)
        webView.setWebChromeClient(ChromeClient())
        webView.setWebViewClient(WebClient())
        webView.loadUrl(getURl())
    }

    inner class ChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressbar.progress = newProgress
        }
    }

    inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            loadingUrl(view, url)
            return false
        }
    }

    private fun loadingUrl(webView: WebView, url: String) {
        if (!isLoadUrl) {
            if (!url.contains(CALLBACK_URL + "/?code")) {
                webView.loadUrl(url)
            } else {
                isLoadUrl = true
                val intent = Intent()
                intent.putExtra(LOGIN_CODE_KEY, url)
                setResult(RESULT_OK, intent)
                finish()

            }
        }

    }

    private fun getURl(): String {
        var url = LOGIN_URL
        url = url + "authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + CALLBACK_URL + "&state=" + getRandomString(6) + "&scope=public+write+comment+upload"
        return url
    }
}