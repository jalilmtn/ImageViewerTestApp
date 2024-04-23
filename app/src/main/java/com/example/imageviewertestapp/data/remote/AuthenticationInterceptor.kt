package com.example.imageviewertestapp.data.remote

import com.example.imageviewertestapp.common.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val request = chain.request()

        request.addAuthentication(Constants.API_KEY)
        val response = chain.proceed(request)

//        handle auth error and etc here
//        if (response.code == 401) {
//        }
        response

    }

    private fun Request.addAuthentication(
        key: String
    ): Request = newBuilder()
        .addHeader(
            "x-api-key",
            key
        )
        .build()
}
