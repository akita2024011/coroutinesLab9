package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun CoroutineScope.startCounter(name: String, limit: Int): Job {
    return launch {
        for (i in 1..limit) {
            delay(100)
            println("$name: $i")
        }
    }
}

fun runQuestion11() = runBlocking {
    coroutineScope {
        println("launched")
        startCounter("Counter A", 3)
        startCounter("Counter B", 2)
    }
    println("Scope completed")
}

fun main() = runQuestion11()
