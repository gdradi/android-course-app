@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidappcourse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androidappcourse.ui.theme.AndroidAppCourseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate")

        enableEdgeToEdge()
        setContent {
            AndroidAppCourseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_name)) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Greeting(
                            text = "Benvenuti al corso Mobile",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                        )
                        TodoList(listOf("Sviluppo feature 1", "Testing", "Documentazione"))
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        println("onResume")

        // Liste immutabili (readonly)
        val lista1 = listOf("ciao", "a", "tutti")
        var lista3 = listOf("massimo", "decimo", "meridio")
        //lista1[0] = "ciao"  // Non ammesso -> è readonly

        // Liste mutabili
        val listaMutabile = mutableListOf("ciao", "a", "tutti", "modificabile", "b")
        println("listaMutabile prima: $listaMutabile")
        listaMutabile[0] = "buongiorno"
        listaMutabile.add("elemento aggiunto")
        println("listaMutabile dopo: $listaMutabile")

        //lista1 = lista3   // val impedisce di cambiare il riferimento del puntatore
        // Attenzione: è un cambio del riferimento del puntatore, non un clonazione della lista
        lista3 = lista1
        println("lista1: $lista1")
        println("lista3: $lista3")
        var lista2 = lista1

        // Invocazione funzioni
        println("groupWordsByLength ${groupWordsByLength(lista1)}")
        println("groupWordsByLength ${groupWordsByLength(listaMutabile)}")

        // Invocazione funzioni con lambda in input

        // Raggruppamento per lunghezza
        println("groupWordsBy per lunghezza ${lista1.groupWordsBy() { word -> word.length }}")

        // Raggruppamento per numero di vocali, scrittura "estesa"
        println("groupWordsBy per numero di vocali, estesa ${lista1.groupWordsBy { word ->
            val vocali = arrayOf('a', 'e', 'i', 'o', 'u')
            var counter = 0
            for (c in word) {
                if (c in vocali) counter++
            }
            counter
        }}")

        // Raggruppamento per numero di vocali, scrittura più concisa
        println("groupWordsBy per numero di vocali, più concisa ${
            lista1.groupWordsBy { word ->  word.count { c -> c in arrayOf('a', 'e', 'i', 'o', 'u') }
        }}")

        // Raggruppamento per numero di vocali, scrittura kotlin-like
        println("groupWordsBy per numero di vocali, kotlin-like ${
            lista1.groupWordsBy { it.count { it in arrayOf('a', 'e', 'i', 'o', 'u') } }
        }")

        // Funzione di trasformazione MAP
        val listaDiNumberi = listOf(1,2,3,4)
        println("map: moltiplicazione ${listaDiNumberi.map { n -> n*2 }}")
        println("map: moltiplicazione con it ${listaDiNumberi.map { it * 2 }}")
        println("map: pari o dispari ${listaDiNumberi.map { it % 2 == 0 }}")

        // Concatenazione trasformazioni filter e map
        println(
            listaDiNumberi
                .filter { it % 2 == 0 } // Filtro: considero solo i pari
                .map { "L'elemento $it è pari" }
        )

        // Creazione istanze di una classe
        val persona1 = Person("Giacomo", "Dradi", 33)
        val persona2 = Person("Pippo", "Pluto", 30)

        // Invocazione metodi di una classe
        persona1.sayHello()

        // Data class e clonazione
        data class User(val name: String, val age: Int)
        val jack = User(name = "Jack", age = 1)
        val olderJack = jack.copy(age = 2)
        println(jack.age)

        // Funzione with
        with(jack) {
            println(name)
            println(age)
        }

        // Delegato Lazy<T> e keyword by
        val lazyValue: String by lazy {
            println("computed!")
            "Hello"
        }

        // Esercizio contatore
        var counter: ICounter = SimpleCounter(0)
        with(counter) {
            inc()
            inc()
            print()
            reset()
            print()
        }
        val display: IDisplay = ConsoleDisplay()
        counter = CounterWithDisplay(display)
        with(counter) {
            inc()
            inc()
            print()
            reset()
            print()
        }
    }
}

// Classe con proprietà definite nel costruttore primario
class Person1(val name: String, val surname: String)

/*
    Classe aperta con costruttore primario e proprietà definite
    - sia nel costruttore primario
    - sia nel corpo della classe
 */
open class Person (name: String, surname: String, val age: Int) {
    val name = name.uppercase()
    val surname = surname.uppercase()

    open fun sayHello(): String {
       return "Ciao $name $surname"
    }

    fun sayHello2() = "Ciao $name $surname (anni $age)"
}

/*
    Classe figlia che utilizza il costruttore primario della classe genitore
    e aggiunge una proprietà
 */
class Student(name: String, surname: String, age: Int, val matr: String)
    : Person(name, surname, age) {

    var p1: String = ""
        get() = name.uppercase()
        set(value) { field = value }

    override fun sayHello(): String {
        //return "Ciao $name $surname (matr: $matr)"
        return "${super.sayHello()} (matr: $matr)"
        //return super.sayHello() + " (matr: $matr)"
    }
}


/*
class Student: Person {
    constructor(name: String, surname: String, age: Int) : super(name, surname, age)
}
*/


/*
    Funzione che raggruppa le parole per lunghezza

    modalità classica
*/
fun groupWordsByLength(words: List<String>): Map<Int, List<String>> {
    val result = mutableMapOf<Int, MutableList<String>>()
    for (word in words) {
        val length = word.length
        if (length !in result) {
            //if (result.containsKey(length)) {
            result[length] = mutableListOf()
        }
        result[length]?.add(word)
    }
    return result
}


/*
    Funzione che raggruppa le parole per lunghezza

    modalità extension function
 */
fun List<String>.groupWordsBy(fn: (String) -> Int): Map<Int, List<String>> {
    val result = mutableMapOf<Int, MutableList<String>>()
    for (word in this) {
        val key = fn(word)
        if (key !in result) {
            result[key] = mutableListOf()
        }
        result[key]?.add(word)
    }
    return result
}

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier) {
    Text(
        text = "Ciao a tutti!\n$text!",
        modifier = modifier
    )
}

@Composable
fun TodoList(todos: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "TODOs",
            style = MaterialTheme.typography.headlineLarge,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(todos) { todo ->
                TodoItem(todo)
            }
        }
    }
}

@Composable
fun TodoItem(todo: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Completed",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = todo,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}