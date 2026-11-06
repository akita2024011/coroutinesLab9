package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion01() = runBlocking {
    launch {
        delay(1000)
        println("Coffee ready")
    }
    println("Order accepted")
}

fun main() = runQuestion01()
