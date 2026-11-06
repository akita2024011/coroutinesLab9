package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion09() = runBlocking {
    val parentJob = launch {
        launch {
            try {
                println("Request 1 started")
                delay(1000)
                println("Request 1 completed")
            } finally {
                println("Request 1 cleanup in finally")
            }
        }
        
        launch {
            try {
                println("Request 2 started")
                delay(1000)
                println("Request 2 completed")
            } finally {
                println("Request 2 cleanup in finally")
            }
        }
    }
    
    delay(100)
    println("Cancelling parent coroutine...")
    parentJob.cancelAndJoin()
    println("end")
}

fun main() = runQuestion09()
