package com.example.myapplication.api

import android.text.TextUtils
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class Client {

    var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}

class ServiceGenerator {
    private val API_BASE_URL = "https://api.github.com"
    private val httpClient = OkHttpClient.Builder()
    private val builder = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>?): S {
        return createService(serviceClass, null)
    }

    fun <S> createService(
        serviceClass: Class<S>?, clientId: String?, clientSecret: String?
    ): S {
        if (!TextUtils.isEmpty(clientId)
            && !TextUtils.isEmpty(clientSecret)
        ) {
            val authToken = Credentials.basic(clientId!!, clientSecret!!)
            return createService(serviceClass, authToken)
        }
        return createService(serviceClass, null, null)
    }

    private fun <S> createService(
        serviceClass: Class<S>?, authToken: String?
    ): S {
        var retrofit = builder.build()
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken!!)
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }

        return retrofit.create(serviceClass)
    }
}

class AuthenticationInterceptor(private val authToken: String) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val builder: Request.Builder = original.newBuilder()
            .header("Authorization", authToken)
        val request: Request = builder.build()
        return chain.proceed(request)
    }

}

class AccessToken {
    val accessToken: String? = null
    private var tokenType: String? = null

    fun getTokenType(): String? {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (!Character.isUpperCase(tokenType!![0])) {
            tokenType = Character
                .toString(tokenType!![0])
                .toUpperCase() + tokenType!!.substring(1)
        }
        return tokenType
    }
}