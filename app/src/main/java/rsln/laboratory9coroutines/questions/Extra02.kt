package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runExtra02() = runBlocking {
    coroutineScope {
        launch { // Child 1
            launch { // Grandchild
                delay(200)
                println("grandchild done")
            }
            println("child 1 done")
        }
        launch { // Child 2
            delay(100)
            println("child 2 done")
        }
    }
    println("scope done")
}

fun main() = runExtra02()
