package br.net.easify.info.View.Currency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.net.easify.info.Model.Articles
import br.net.easify.info.Model.Currency
import br.net.easify.info.R
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrencyAdapter(private val currencies: ArrayList<Currency>): RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    fun updatedCurrencies(data: List<Currency>?) {
        currencies.clear()
        data?.let { currencies.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.currency_item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount() = currencies.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.view.currencyName.text = currencies[position].name
        holder.view.currencyCode.text = currencies[position].code
        holder.view.currencyDate.text = currencies[position].createDate
        holder.view.currencyConvertion.text = currencies[position].high
    }

    inner class CurrencyViewHolder(var view: View): RecyclerView.ViewHolder(view)
}