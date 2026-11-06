package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion06() = runBlocking {
    for (i in 1..10) {
        launch {
            println("download $i started on ${Thread.currentThread().name}")
            delay(200)
            println("download $i finished on ${Thread.currentThread().name}")
        }
    }
}

fun main() = runQuestion06()
