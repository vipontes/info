package br.net.easify.info.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Conversion (
    val USD: Currency?,
    val USDT: Currency?,
    val CAD: Currency?,
    val EUR: Currency?,
    val GBP: Currency?,
    val ARS: Currency?,
    val BTC: Currency?,
    val LTC: Currency?,
    val JPY: Currency?,
    val CHF: Currency?,
    val AUD: Currency?,
    val CNY: Currency?,
    val ILS: Currency?,
    val ETH: Currency?,
    val XRP: Currency?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader),
        parcel.readParcelable(Currency::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(USD, flags)
        parcel.writeParcelable(USDT, flags)
        parcel.writeParcelable(CAD, flags)
        parcel.writeParcelable(EUR, flags)
        parcel.writeParcelable(GBP, flags)
        parcel.writeParcelable(ARS, flags)
        parcel.writeParcelable(BTC, flags)
        parcel.writeParcelable(LTC, flags)
        parcel.writeParcelable(JPY, flags)
        parcel.writeParcelable(CHF, flags)
        parcel.writeParcelable(AUD, flags)
        parcel.writeParcelable(CNY, flags)
        parcel.writeParcelable(ILS, flags)
        parcel.writeParcelable(ETH, flags)
        parcel.writeParcelable(XRP, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Conversion> {
        override fun createFromParcel(parcel: Parcel): Conversion {
            return Conversion(parcel)
        }

        override fun newArray(size: Int): Array<Conversion?> {
            return arrayOfNulls(size)
        }
    }
}

data class Currency (
    val code: String?,
    val codein: String?,
    val name: String?,
    val high: String?,
    val low: String?,
    val varBid: String?,
    val pctChange: String?,
    val bid: String?,
    val ask: String?,
    val timestamp: String?,
    @SerializedName("create_date")
    val createDate: String?,
    val status: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(codein)
        parcel.writeString(name)
        parcel.writeString(high)
        parcel.writeString(low)
        parcel.writeString(varBid)
        parcel.writeString(pctChange)
        parcel.writeString(bid)
        parcel.writeString(ask)
        parcel.writeString(timestamp)
        parcel.writeString(createDate)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currency> {
        override fun createFromParcel(parcel: Parcel): Currency {
            return Currency(parcel)
        }

        override fun newArray(size: Int): Array<Currency?> {
            return arrayOfNulls(size)
        }
    }
}