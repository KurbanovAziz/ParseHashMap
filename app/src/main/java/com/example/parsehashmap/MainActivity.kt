package com.example.parsehashmap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.parsehashmap.databinding.ActivityMainBinding
import java.net.URLDecoder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parseDownloadUrl()
    }

    private fun parseDownloadUrl() {
        val downloadUrl = intent?.getStringExtra(Intent.EXTRA_REFERRER)

        val params = downloadUrl?.let { parseUrlParams(it) }

        Log.d("ololo", "Источник: ${params?.get("utm_source")}")
        Log.d("ololo", "Кампания: ${params?.get("utm_campaign")}")
    }

    private fun parseUrlParams(url: String): HashMap<String, String> {
        val params = HashMap<String, String>()
        val queryStart = url.indexOf('?')
        if (queryStart >= 0) {
            val query = url.substring(queryStart + 1)
            for (param in query.split('&')) {
                val pair = param.split('=')
                val key = URLDecoder.decode(pair[0], "UTF-8")
                val value = if (pair.size > 1) URLDecoder.decode(pair[1], "UTF-8") else ""
                params[key] = value
            }
        }
        return params
    }
}
