package com.cuongnl.ridehailing.retrofit

import android.content.Context
import com.cuongnl.ridehailing.computerlocal.ServerAddress
import com.cuongnl.ridehailing.utils.LocalStorageUtils
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        private var retrofit: Retrofit? = null
        fun getClient(context: Context): Retrofit {
            if (retrofit == null) {

                val httpClient = setupOkHttpClient(context)

                retrofit = Retrofit.Builder()
                    .baseUrl(ServerAddress.SERVER_ADDRESS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
            }
            return retrofit!!
        }

        private fun setupOkHttpClient(context: Context) : OkHttpClient {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { chain ->
                val originalRequest: Request = chain.request()

                val accessToken = LocalStorageUtils.readData(context, LocalStorageUtils.KEY_ACCESS_TOKEN) as String?

                val builder: Request.Builder = originalRequest.newBuilder().header(
                    "Authorization",
                    "Bearer $accessToken"
                )
                val newRequest: Request = builder.build()
                chain.proceed(newRequest)
            }

            return httpClient.build()
        }
    }
}