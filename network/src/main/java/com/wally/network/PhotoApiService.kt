package com.wally.network

import android.util.Log
import com.wally.network.response.Photo
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*

interface PhotoApiService {
    suspend fun getListImages(): List<Photo>

    companion object {
        private const val TAG = "PhotoApiService"

        fun create(): PhotoApiService {
            return PhotoApiServiceImpl(
                client = HttpClient(CIO) {
                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                                Log.d(TAG, message)
                            }
                        }
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