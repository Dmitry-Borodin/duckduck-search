package com.pet.duckducksearch

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

const val DUCK_DUCK_SEARCH_URL = "https://duckduckgo.com/?q="

class MainActivity : AppCompatActivity() {

    private val editText by bindView<EditText>(R.id.main_edittext)
    private val cta by bindView<Button>(R.id.main_cta)
    private val webview by bindView<WebView>(R.id.main_webview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText.addTextChangedListener { validateSearchEnabled() }
        cta.setOnClickListener { webview.loadUrl(DUCK_DUCK_SEARCH_URL+editText.text) }
    }

    override fun onStart() {
        super.onStart()
        getClipboardText()?.let { editText.setText(it) } ?: editText.requestFocus()
    }

    private fun validateSearchEnabled() {
        cta.isEnabled = editText.text.isNotBlank()
    }

    private fun getClipboardText(): CharSequence? {
        val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        //todo get previous if not text
        return clipboard.primaryClip?.getItemAt(0)?.text
    }
}