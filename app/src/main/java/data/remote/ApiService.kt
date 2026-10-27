package data.remote

import data.model.Post
import retrofit2.http.GET

interface ApiService{
    @GET("/post")
    suspend fun getPost(): List<Post>
}