package com.wally.network

import android.util.Log
import com.wally.network.response.PhotoResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class PhotoApiServiceImpl(private val client: HttpClient) : PhotoApiService {
    private val tag = "PhotoApiServiceImpl"

    override suspend fun getListImages(page: Int, limit: Int): List<PhotoResponse> {
        return try {
            client.get {
                url(LIST_IMAGES_URL)
                parameter(PAGE, page)
                parameter(LIMIT, limit)
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Log.e(tag, "RedirectResponseException")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Log.e(tag, "ClientRequestException")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Log.e(tag, "ServerResponseException")
            emptyList()
        }
    }
}