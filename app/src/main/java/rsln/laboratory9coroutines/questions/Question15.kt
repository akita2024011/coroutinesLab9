package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion15() = runBlocking {
    println("Start")
    coroutineScope {
        launch {
            delay(300)
            println("Export X finished")
        }
        launch {
            delay(100)
            println("Export Y finished")
        }
        launch {
            delay(200)
            println("Export Z finished")
        }
    }
    println("All done")
}

fun main() = runQuestion15()
