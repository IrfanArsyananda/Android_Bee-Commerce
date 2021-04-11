package com.irfanarsya.beecommerce.dataSource.promo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.irfanarsya.beecommerce.model.DataItem

class PromoDataFactory : DataSource.Factory<Long, DataItem>() {

    var mutableLivedata : MutableLiveData<PromoDataSource>
    var promoDataSource : PromoDataSource

    init {
        mutableLivedata = MutableLiveData()
        promoDataSource = PromoDataSource()
    }

    override fun create(): DataSource<Long, DataItem> {
        mutableLivedata.postValue(promoDataSource)
        return promoDataSource

    }

    fun getMutableLiveData(): MutableLiveData<PromoDataSource> {
        return mutableLivedata
    }
}