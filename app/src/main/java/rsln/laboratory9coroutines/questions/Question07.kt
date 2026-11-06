package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion07() = runBlocking {
    println("1")
    launch {
        println("2")
        delay(100)
        println("3")
    }
    println("4")
    delay(50)
    println("5")
}

fun main() = runQuestion07()
