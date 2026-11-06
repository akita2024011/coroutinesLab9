package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion08() = runBlocking {
    val parentJob = launch {
        launch {
            delay(200)
            println("child done")
        }
        println("parent body done")
    }
    
    parentJob.join()
    println("report completed")
}

fun main() = runQuestion08()
