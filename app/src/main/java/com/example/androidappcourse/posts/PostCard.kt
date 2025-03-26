import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import okhttp3.OkHttpClient



@Composable
fun PostCard(post: Post, user: User, onDelete: (Int) -> Unit) {

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .okHttpClient { getUnsafeOkHttpClient() }
        .build()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            UserCard(user)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.pictureUrl)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader, // usa il tuo imageLoader
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Button(
                onClick = { onDelete(post.id) },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 8.dp)
            ) {
                Text("Elimina")
            }
        }
    }
}