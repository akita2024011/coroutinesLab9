package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

data class User(val id: Int, val name: String)

suspend fun loadUserById(id: Int): User {
    delay(100)
    return User(id, "User #$id")
}

fun runExtra04() = runBlocking {
    val user = loadUserById(42)
    println("Loaded user: $user")
}

fun main() = runExtra04()
