package com.wally.network

import android.util.Log
import com.wally.network.response.PhotoResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*

const val BASE_URL = "https://picsum.photos"

const val LIST_IMAGES_URL = "${BASE_URL}/v2/list"
const val PAGE = "page"
const val LIMIT = "limit"

const val TIME_OUT = 15000L

interface PhotoApiService {
    suspend fun getListImages(page: Int, limit: Int): List<PhotoResponse>

    companion object {
        private const val TAG = "PhotoApiService"

        fun create(): PhotoApiService {
            return PhotoApiServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.BODY
                        logger = object : Logger {
                            override fun log(message: String) {
                                Log.d(TAG, message)
                            }
                        }
                    }
                    install(HttpTimeout) {
                        requestTimeoutMillis = TIME_OUT
                        connectTimeoutMillis = TIME_OUT
                        socketTimeoutMillis = TIME_OUT
                    }
                    install(JsonFeature) {
                        serializer = GsonSerializer {
                            setPrettyPrinting()
                            disableHtmlEscaping()
                        }
                    }
                }
            )
        }
    }
}