package dragor.international.di

import dragor.international.api.ApiConstants
import dragor.international.repository.Repository
import com.google.gson.GsonBuilder
import dragor.international.api.ApiConstants.Companion.TIMEOUT_IN_SEC
import dragor.international.api.network.ApiService
import dragor.international.ui.test.ExampleViewModel
import dragor.international.api.network.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { ExampleViewModel(get()) }
}

val repositoryModule = module {
    single { Repository(get()) }
}


val retrofitModule = module {

    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
        okHttpClient.readTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        return okHttpClient.build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.ENDPOINT)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .serializeNulls()
                        .setPrettyPrinting()
                        .create()
                )
            )
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }


    single { provideHttpClient() }
    single { provideRetrofit(get()) }
}