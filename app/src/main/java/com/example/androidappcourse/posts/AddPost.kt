import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.exp
import kotlin.random.Random

@Composable
fun AddPost(
    onAdd: (Post) -> Unit
) {
    var authorId by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }
        OutlinedTextField(
            value = authorId,
            onValueChange = { authorId = it },
            label = { Text("ID Autore") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrizione") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val authorIdAsInt = authorId.toIntOrNull()
                if (authorIdAsInt == null) {
                    error = "L'id inserito non è un numero"
                }
                else if (description.isEmpty()) {
                    error = "La descrizione è vuota"
                } else {
                    val newPostId = randomInt(0, 1000)
                    val newPost = Post(
                        id = newPostId,
                        authorId = authorIdAsInt,
                        title = "Titolo del post #$newPostId",
                        description = description,
                        pictureUrl = "https://picsum.photos/id/$newPostId/400/200"
                    )
                    onAdd(newPost)
                    error = null
                    authorId = ""
                    description = ""
                }
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text("Aggiungi Post")
        }


    }
}
