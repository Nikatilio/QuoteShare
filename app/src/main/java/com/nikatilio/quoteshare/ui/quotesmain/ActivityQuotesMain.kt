package com.nikatilio.quoteshare.ui.quotesmain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikatilio.quoteshare.R
import kotlinx.android.synthetic.main.activity_quotes_main.*

class ActivityQuotesMain: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_main)

        quotesRecyclerView.layoutManager = LinearLayoutManager(this)
        quotesRecyclerView.adapter = QuotesListAdapter(this)

        val model = ViewModelProviders.of(this)[QuotesListViewModel::class.java]
        model.getQuotes().observe(this, Observer {
            // Update UI
            val adapter = quotesRecyclerView.adapter as QuotesListAdapter
            adapter.setQuotesList(it)
        })



    }

    override fun onDestroy() {
        super.onDestroy()
    }
}