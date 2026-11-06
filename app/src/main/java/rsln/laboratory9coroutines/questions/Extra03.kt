package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runExtra03() {
    println("Starting main with runBlocking...")
    runBlocking {
        launch {
            delay(100)
            println("Hello from launch inside runBlocking")
        }
    }
    println("Finished main")
}

fun main() = runExtra03()
