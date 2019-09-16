package br.net.easify.info.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.net.easify.info.Model.Conversion
import br.net.easify.info.Model.Currency
import br.net.easify.info.Services.ConversionService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    val conversion by lazy { MutableLiveData<Conversion>() }
    val currencies by lazy { MutableLiveData<List<Currency>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    val conversionValue by lazy { MutableLiveData<String>() }


    private val conversionService = ConversionService()
    private val disposable = CompositeDisposable()

    fun refresh() {
        loading.value = true
        getAll()
    }

    init {
        conversionValue.value = "5"
    }

    private fun parseConversion() {

        var temp = mutableListOf<Currency>()
        conversion.value.let {
            if (it?.USD != null) temp.add(it?.USD)
            if (it?.USDT != null) temp.add(it?.USDT)
            if (it?.CAD != null) temp.add(it?.CAD)
            if (it?.EUR != null) temp.add(it?.EUR)
            if (it?.GBP != null) temp.add(it?.GBP)
            if (it?.ARS != null) temp.add(it?.ARS)
            if (it?.BTC != null) temp.add(it?.BTC)
            if (it?.LTC != null) temp.add(it?.LTC)
            if (it?.JPY != null) temp.add(it?.JPY)
            if (it?.CHF != null) temp.add(it?.CHF)
            if (it?.AUD != null) temp.add(it?.AUD)
            if (it?.CNY != null) temp.add(it?.CNY)
            if (it?.ILS != null) temp.add(it?.ILS)
            if (it?.ETH != null) temp.add(it?.ETH)
            if (it?.XRP != null) temp.add(it?.XRP)
            currencies.value = temp
        }
    }

    private fun getAll() {
        disposable.add(
            conversionService.getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Conversion>() {
                    override fun onSuccess(res: Conversion) {
                        loading.value = false
                        conversion.value = res
                        loading.value = false
                        parseConversion()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loadError.value = true
                        loading.value = false
                        conversion.value = null
                        currencies.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}