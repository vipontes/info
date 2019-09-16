package br.net.easify.info.View.Currency

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.net.easify.info.Model.Currency
import br.net.easify.info.R
import br.net.easify.info.ViewModel.CurrencyViewModel
import br.net.easify.info.databinding.FragmentCurrencyBinding

import kotlinx.android.synthetic.main.fragment_currency.listError
import kotlinx.android.synthetic.main.fragment_currency.loadingView

class CurrencyFragment : Fragment() {

    private lateinit var viewModel: CurrencyViewModel
    private var listAdapter = CurrencyAdapter(arrayListOf())
    private var conversionValue: String = "1.0"

    private lateinit var dataBinding: FragmentCurrencyBinding

    private val currenciesDataObserver = Observer<List<Currency>> { res: List<Currency> ->
        res?.let {
            dataBinding.currencyList.visibility = View.VISIBLE
            listAdapter.updatedCurrencies(res)
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading: Boolean ->
        loadingView.visibility = if(isLoading) View.VISIBLE else View.GONE
        if ( isLoading ) {
            listError.visibility = View.GONE
            dataBinding.currencyList.visibility = View.GONE
        }
    }

    private val loadErrorLiveDataObserver = Observer<Boolean> { isError: Boolean ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
    }

    private val conversionValueObserver = Observer<String> { value: String ->
        conversionValue = value
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency, container, false)
        dataBinding.conversionValue = conversionValue
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)
        viewModel.currencies.observe(this, currenciesDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, loadErrorLiveDataObserver)
        viewModel.conversionValue.observe(this, conversionValueObserver)
        viewModel.refresh()

        dataBinding.currencyList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }
}
