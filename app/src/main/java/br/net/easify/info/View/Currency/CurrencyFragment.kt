package br.net.easify.info.View.Currency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.net.easify.info.Model.Currency
import br.net.easify.info.R
import br.net.easify.info.ViewModel.CurrencyViewModel
import kotlinx.android.synthetic.main.fragment_currency.*
import kotlinx.android.synthetic.main.fragment_currency.listError
import kotlinx.android.synthetic.main.fragment_currency.loadingView

class CurrencyFragment : Fragment() {

    private lateinit var viewModel: CurrencyViewModel
    private var listAdapter = CurrencyAdapter(arrayListOf())

    private val currenciesDataObserver = Observer<List<Currency>> { res: List<Currency> ->
        res?.let {
            currencyList.visibility = View.VISIBLE
            listAdapter.updatedCurrencies(res)
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading: Boolean ->
        loadingView.visibility = if(isLoading) View.VISIBLE else View.GONE
        if ( isLoading ) {
            listError.visibility = View.GONE
            currencyList.visibility = View.GONE
        }
    }

    private val loadErrorLiveDataObserver = Observer<Boolean> { isError: Boolean ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        viewModel.currencies.observe(this, currenciesDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, loadErrorLiveDataObserver)
        viewModel.refresh()

        currencyList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }
}
