import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun Display(value: Int) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = value.toString(),
                    fontSize = 48.sp,
                    modifier = Modifier.padding(32.dp)
                )
            }
        }
    }
}


@Composable
@Preview
fun Counter() {
    var count by remember { mutableStateOf(0) }
    //val (count, setCount) = remember { mutableStateOf(0) }

    Column {
        //Text(text = count.toString())
        Display(count)
        Row {
            Button(
                onClick = {
                    count++
                    println(count)
                }
            ) {
                Text("Inc")
            }
            Button(
                onClick = {
                    count = 0
                    println(count)
                }
            ) {
                Text("Reset")
            }
        }
    }
}