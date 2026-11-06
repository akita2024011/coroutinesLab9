package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun getCurrentThreadName(): String {
    delay(1)
    return Thread.currentThread().name
}

fun runQuestion05() = runBlocking {
    val beforeThread = Thread.currentThread().name
    println("Thread before suspend call: $beforeThread")
    
    val suspendThread = getCurrentThreadName()
    println("Thread inside suspend call: $suspendThread")
}

fun main() = runQuestion05()
