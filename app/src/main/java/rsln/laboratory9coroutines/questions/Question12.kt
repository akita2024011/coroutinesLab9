package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.greetInBackground(name: String): Job {
    return launch {
        delay(200)
        println("Background Hello, $name!")
    }
}

suspend fun greetAll(names: List<String>) = coroutineScope {
    for (name in names) {
        launch {
            delay(100)
            println("Sequential Hello, $name!")
        }
    }
}

fun runQuestion12() = runBlocking {
    println("Calling greetInBackground...")
    greetInBackground("Alice")
    println("Line right after greetInBackground call (returned immediately)")
    
    println("\nCalling greetAll...")
    greetAll(listOf("Bob", "Charlie"))
    println("Line right after greetAll call (returned only after all finished)")
}

fun main() = runQuestion12()
