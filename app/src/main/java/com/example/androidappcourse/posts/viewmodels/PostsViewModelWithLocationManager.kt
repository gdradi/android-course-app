import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidappcourse.posts.business.LocationManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.URL

class PostsViewModelWithLocationManager(
    private val locationManager: LocationManager
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                println("factory");
                // Istanziazione dipendenze
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val locationManager = LocationManager(application.applicationContext)
                PostsViewModelWithLocationManager(locationManager)
            }
        }
    }

    init {
//        loadPosts()
    }

    fun addPost(post: Post) {
        viewModelScope.launch {
            val location = locationManager.getCurrentLocation()
            val postWithLocation = post.copy(
                latitude = location?.latitude,
                longitude = location?.longitude
            )
            _posts.value += postWithLocation
        }
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
