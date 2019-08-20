package com.nikatilio.quoteshare.ui.quotesmain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikatilio.quoteshare.R
import com.nikatilio.quoteshare.data.Quote
import com.nikatilio.quoteshare.utils.inflate
import kotlinx.android.synthetic.main.quotes_list_item.view.*

class QuotesListAdapter(val context: Context): RecyclerView.Adapter<QuotesListAdapter.QuoteViewHolder>() {

    lateinit var quotes: List<Quote>

    fun setQuotesList(quotes: List<Quote>) {
        this.quotes = quotes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = parent.inflate(R.layout.quotes_list_item, false)
        return QuoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (::quotes.isInitialized) {
            return quotes.size
        } else return 0
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bindQuote(quotes[position])
    }


    class QuoteViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindQuote(quote: Quote) {
            view.quoteText.text = quote.text
        }
    }
}