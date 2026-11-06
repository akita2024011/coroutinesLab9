package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun runQuestion14() = runBlocking {
    println("--- Launch Version ---")
    val job = launch {
        val result = 6 * 7
        println("Launch calculated result inside coroutine: $result")
    }
    job.join()
    println("Job returned: $job (Cannot extract return value from Job)")
    
    println("\n--- Async Version ---")
    val deferred = async {
        delay(100)
        6 * 7
    }
    println("Deferred object returned immediately: $deferred")
    val result = deferred.await()
    println("Calculated result obtained via await(): $result")
}

fun main() = runQuestion14()
