import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.URL
import kotlin.random.Random

fun randomInt(from: Int, to: Int) = Random.nextInt(from, to + 1)

val users = (0..2).map {
    User(it, "Nome $it", "Ruolo $it", "https://randomuser.me/api/portraits/men/$it.jpg")
}

class PostsViewModel : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
//        loadPosts()
    }

    fun addPost(post: Post) {
        _posts.value += post
    }

    fun deletePost(postId: Int) {
        _posts.value = _posts.value.filterNot { it.id == postId }
    }

    private fun loadPosts() {
        // Usare il launch del viewModelScope
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL("https://jsonplaceholder.typicode.com/posts").readText()
            val jsonArray = JSONArray(json)
            val fetched = mutableListOf<Post>()
            for (i in 0 until minOf(2, jsonArray.length())) {
                val obj = jsonArray.getJSONObject(i)
                fetched.add(
                    Post(
                        id = obj.getInt("id"),
                        authorId = randomInt(0, users.size - 1),
                        title = obj.getString("title"),
                        description = obj.getString("body"),
                        pictureUrl = "https://picsum.photos/id/${obj.getInt("id")}/400/200"
                    )
                )
            }
            _posts.value = fetched
        }
    }
}
