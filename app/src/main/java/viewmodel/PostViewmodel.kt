package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.Post
import repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewmodel : ViewModel(){
    private val repository = PostRepository()
    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList: StateFlow<List<Post>> = _postList

    init {
        fetchPost()
    }

    private fun fetchPost(){
        try{
            _postList.value = repository.getPosts()
        }catch (e: Exception){
            println("Error al obtener datos: ${e.localizedMessage}")
        }
    }
}