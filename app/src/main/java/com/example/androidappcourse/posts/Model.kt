data class User(
    val id: Int,
    val name: String,
    val role: String,
    val pictureUrl: String? = null
)

data class Post(
    val id: Int,
    val authorId: Int,
    val title: String,
    val description: String,
    val pictureUrl: String
)
