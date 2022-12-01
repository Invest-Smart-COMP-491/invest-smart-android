package com.comp491.investsmart.data.api.retrofit

import com.comp491.investsmart.data.api.Constant
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

object RetrofitBuilder {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            val moshi: Moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()

            retrofit =
                Retrofit
                    .Builder()
                    .baseUrl(Constant.baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                    .build()
        }

        return retrofit as Retrofit
    }
}
