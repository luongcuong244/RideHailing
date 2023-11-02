package com.cuongnl.ridehailing.retrofit

import com.cuongnl.ridehailing.computerlocal.ServerAddress
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private var retrofit: Retrofit? = null
        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(ServerAddress.SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}