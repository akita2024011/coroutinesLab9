package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun runExtra01() = runBlocking {
    val delayTime = measureTimeMillis {
        coroutineScope {
            launch { delay(300) }
            launch { delay(300) }
            launch { delay(300) }
        }
    }
    
    val sleepTime = measureTimeMillis {
        coroutineScope {
            launch { Thread.sleep(300) }
            launch { Thread.sleep(300) }
            launch { Thread.sleep(300) }
        }
    }
    
    val roundedDelay = ((delayTime + 50) / 100) * 100
    val roundedSleep = ((sleepTime + 50) / 100) * 100
    
    println("Time with delay: ~${roundedDelay}ms (Exact: ${delayTime}ms)")
    println("Time with Thread.sleep: ~${roundedSleep}ms (Exact: ${sleepTime}ms)")
}

fun main() = runExtra01()
