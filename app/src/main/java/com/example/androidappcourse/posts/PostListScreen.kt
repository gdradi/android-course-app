import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

fun randomInt(from: Int, to: Int) = Random.nextInt(from, to + 1)

val users = (0..2).map {
    User(it, "Nome $it", "Ruolo $it", "https://randomuser.me/api/portraits/men/$it.jpg")
}

/*
val posts = (0..10).map {
    Post(
        it,
        randomInt(0, users.size-1),
        "Post $it",
        "Descrizione $it",
        "https://picsum.photos/id/$it/400/200")
}
 */

@Composable
fun PostListScreen() {
    val posts = remember { mutableStateListOf<Post>() }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        AddPost { newPost ->
            posts.add(newPost)
        }
        posts.forEach {
            val user = users.find { u -> u.id == it.authorId }
            if (user == null) {
                Text(
                    text = "Impossibile visualizzare il post #${it.id}. Utente ${it.authorId} non trovato}",
                    modifier = Modifier
                        .background(Color.Red)
                        .padding(15.dp),
                    color = Color.White
                )
            } else {
                PostCard(it, user)
            }
        }
    }
}
