package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun fetchUserData(): String {
    delay(100)
    return "User: Alice"
}

suspend fun fix1Suspend() {
    val user = fetchUserData()
    println("Fix 1 result: $user")
}

fun fix2RunBlocking() {
    runBlocking {
        val user = fetchUserData()
        println("Fix 2 result: $user")
    }
}

fun runQuestion04() = runBlocking {
    fix1Suspend()
    fix2RunBlocking()
}

fun main() = runQuestion04()
