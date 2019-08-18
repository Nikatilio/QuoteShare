package com.nikatilio.quoteshare.ui.quotesmain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikatilio.quoteshare.R
import com.nikatilio.quoteshare.data.Quote
import kotlinx.android.synthetic.main.quotes_list_item.view.*

class QuotesListAdapter(val context: Context): RecyclerView.Adapter<QuotesListAdapter.ViewHolder>() {

    lateinit var quotes: List<Quote>

    fun setQuotesList(quotes: List<Quote>) {
        this.quotes = quotes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quotes_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (::quotes.isInitialized) {
            return quotes.size
        } else return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.quoteText.text = quotes[position].text
    }


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    }
}