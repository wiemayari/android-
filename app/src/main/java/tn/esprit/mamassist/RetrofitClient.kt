package tn.esprit.mamassist

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.mamassist.data.network.ApiService

object RetrofitClient {
    private const val BASE_URL = "http://localhost:3000/daily-checkin"// Remplacez par l'URL de votre API

    // Activer la journalisation HTTP pour le débogage
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging) // Ajoute l'intercepteur de journalisation
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client) // Utilise le client avec la journalisation
        .build()

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}

