package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runWithDelay() = runBlocking {
    println("--- With delay ---")
    launch {
        println("Espresso start")
        delay(300)
        println("Espresso end")
    }
    launch {
        println("Tea start")
        delay(100)
        println("Tea end")
    }
    launch {
        println("Latte start")
        delay(200)
        println("Latte end")
    }
}

fun runWithSleep() = runBlocking {
    println("--- With Thread.sleep ---")
    launch {
        println("Espresso start")
        Thread.sleep(300)
        println("Espresso end")
    }
    launch {
        println("Tea start")
        Thread.sleep(100)
        println("Tea end")
    }
    launch {
        println("Latte start")
        Thread.sleep(200)
        println("Latte end")
    }
}

fun runQuestion02() {
    runWithDelay()
    runWithSleep()
}

fun main() = runQuestion02()
