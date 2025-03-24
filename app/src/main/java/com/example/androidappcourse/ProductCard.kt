import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidappcourse.User

@Preview
@Composable
fun ProductCard() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://randomuser.me/api/portraits/men/1.jpg") // URL dell'immagine
                .crossfade(true)
                .build(),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Crema Viso", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("€24.99", fontWeight = FontWeight.SemiBold, color = Color(0xFF388E3C))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Crema idratante per viso e collo con ingredienti naturali. Adatta a tutti i tipi di pelle.",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.DarkGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { /* Aggiungi al carrello */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Aggiungi al carrello")
        }
    }
}