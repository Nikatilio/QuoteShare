package com.nikatilio.quoteshare.ui.quotesmain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikatilio.quoteshare.R
import com.nikatilio.quoteshare.application.QuoteShareApp
import kotlinx.android.synthetic.main.activity_quotes_main.*
import javax.inject.Inject

class ActivityQuotesMain: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: QuotesListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes_main)

        (application as QuoteShareApp).appComponent.inject(this)

        quotesRecyclerView.layoutManager = LinearLayoutManager(this)
        quotesRecyclerView.adapter = QuotesListAdapter(this)

        val model = ViewModelProviders.of(this, viewModelFactory)[QuotesListViewModel::class.java]

        model.getQuotes().observe(this, Observer {
            // Update UI
            val adapter = quotesRecyclerView.adapter as QuotesListAdapter
            adapter.setQuotesList(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}