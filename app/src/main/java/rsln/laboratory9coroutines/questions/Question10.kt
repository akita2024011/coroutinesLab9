package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion10() = runBlocking {
    coroutineScope {
        launch {
            delay(200)
            println("Avatar loaded (200ms)")
        }
        launch {
            delay(100)
            println("Settings loaded (100ms)")
        }
    }
    println("Profile ready")
    
    launch {
        delay(100)
        println("Background task after scope")
    }
    println("end of main")
}

fun main() = runQuestion10()
