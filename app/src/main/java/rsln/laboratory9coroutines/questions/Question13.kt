package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun runQuestion13() {
    println("1. runBlocking demonstration:")
    val runBlockingResult: String = runBlocking {
        println("   Inside runBlocking")
        
        val launchJob = launch {
            delay(50)
            println("   Inside launch")
        }
        println("   launch returns: $launchJob")
        
        val asyncDeferred = async {
            delay(50)
            "Result from async"
        }
        println("   async returns: $asyncDeferred")
        val asyncValue = asyncDeferred.await()
        println("   await() returned: $asyncValue")
        
        val scopeResult = coroutineScope {
            delay(50)
            "Result from coroutineScope"
        }
        println("   coroutineScope returns: $scopeResult")
        
        val withContextResult = withContext(Dispatchers.Default) {
            delay(50)
            "Result from withContext"
        }
        println("   withContext returns: $withContextResult")
        
        "runBlocking result string"
    }
    println("runBlocking returned: $runBlockingResult")
}

fun main() = runQuestion13()
