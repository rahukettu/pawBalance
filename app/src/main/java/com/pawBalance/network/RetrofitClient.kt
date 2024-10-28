import com.pawBalance.BuildConfig
import com.pawBalance.network.DogApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dog.ceo/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val url = original.url.newBuilder()
                        .addQueryParameter("API_KEY", BuildConfig.API_KEY)
                        .build()

                    val request = original.newBuilder()
                        .url(url)
                        .build()

                    chain.proceed(request)
                }
                .build()
        )
        .build()

    val dogApiService: DogApiService = retrofit.create(DogApiService::class.java)
}