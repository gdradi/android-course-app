package com.example.androidappcourse.exam

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Data class che rappresenta una singola attività
data class ToDoItem(val id: Int, val text: String)


@Composable
fun ToDoScreen() {
    // Stato: lista di attività
    var items by remember { mutableStateOf(listOf<ToDoItem>()) }

    // Contatore per assegnare ID univoci
    var nextId by remember { mutableIntStateOf(1) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Le tue attività", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Lista delle attività, una per riga
        items.forEach { item ->
            ToDoEditor(
                text = item.text,
                onTextChange = { newText ->
                    // Quando il figlio modifica il testo, aggiorniamo lo stato
                    items = items.map {
                        if (it.id == item.id) it.copy(text = newText) else it
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bottone per aggiungere una nuova attività
        Button(onClick = {
            items = items + ToDoItem(id = nextId, text = "")
            nextId++
        }) {
            Text("Aggiungi attività")
        }
    }
}

@Composable
fun ToDoEditor(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        label = { Text("Attività") },
        modifier = modifier
    )
}
