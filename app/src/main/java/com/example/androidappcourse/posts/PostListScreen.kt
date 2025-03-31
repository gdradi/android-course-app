package com.example.androidappcourse.posts

import AddPost
import PostCard
import PostsViewModelWithLocationManager
import User
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


val users = (0..2).map {
    User(it, "Nome $it", "Ruolo $it", "https://randomuser.me/api/portraits/men/$it.jpg")
}

@Composable
fun PostListScreen(
    viewModel: PostsViewModelWithLocationManager = viewModel(
        factory = PostsViewModelWithLocationManager.Factory
    )
) {
    val posts by viewModel.posts.collectAsState()

    LocationPermissionHandler {
        // Qui la permission è stata concessa
        println("Permesso concesso")
    }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        AddPost { post ->
            viewModel.addPost(post)
//            Toast.makeText(context, "Post aggiunto: ${post.title}", Toast.LENGTH_SHORT).show()
        }

        posts.forEach { post ->
            val user = users.find { it.id == post.authorId }
            if (user == null) {
                Text(
                    text = "Impossibile visualizzare il post #${post.id}. Utente ${post.authorId} non trovato",
                    modifier = Modifier
                        .background(Color.Red)
                        .padding(15.dp),
                    color = Color.White
                )
            } else {
                PostCard(
                    post = post,
                    user = user
                )
            }
        }
    }
}