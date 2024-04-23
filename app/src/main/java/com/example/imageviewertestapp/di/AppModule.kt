package com.example.imageviewertestapp.di

import android.content.Context
import androidx.room.Room
import com.example.imageviewertestapp.common.Constants
import com.example.imageviewertestapp.data.local.CatDatabase
import com.example.imageviewertestapp.data.local.CatDatabase.Companion.DB_NAME
import com.example.imageviewertestapp.data.remote.AuthenticationInterceptor
import com.example.imageviewertestapp.data.remote.CatApi
import com.example.imageviewertestapp.data.remote.CatsPagingSource
import com.example.imageviewertestapp.data.repo.CatLocalRepoImpl
import com.example.imageviewertestapp.data.repo.CatsPagingRepoImpl
import com.example.imageviewertestapp.domain.repo.CatLocalRepo
import com.example.imageviewertestapp.domain.repo.CatsPagingRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun getInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    @Singleton
    fun client(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatDatabase(
        @ApplicationContext context: Context
    ): CatDatabase {
        return Room.databaseBuilder(
            context = context,
            CatDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCatApi(okHttpClient: OkHttpClient): CatApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePagingSource(catApi: CatApi): CatsPagingSource {
            return CatsPagingSource(catApi)
    }

    @Provides
    @Singleton
    fun provideCatPagingSource(catsPagingSource :CatsPagingSource):CatsPagingRepo{
        return CatsPagingRepoImpl(catsPagingSource)
    }

    @Provides
    @Singleton
    fun provideCatLocalRepo(catDatabase: CatDatabase):CatLocalRepo = CatLocalRepoImpl(catDatabase.dao)
}