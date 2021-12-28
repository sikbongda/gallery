package com.wally.network

import android.util.Log
import com.wally.network.response.Photo
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class PhotoApiServiceImpl(private val client: HttpClient) : PhotoApiService {
    override suspend fun getListImages(): List<Photo> {
        return try {
            client.get {
                url(LIST_IMAGES_URL)
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Log.e("StockApiService", "RedirectResponseException")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Log.e("StockApiService", "ClientRequestException")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Log.e("StockApiService", "ServerResponseException")
            emptyList()
        }
    }
}