package rsln.laboratory9coroutines.questions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun loadUser() {
    delay(100)
    println("Step 1: User loaded")
    checkPassword()
}

suspend fun checkPassword() {
    delay(100)
    println("Step 2: Password checked")
    openProfile()
}

suspend fun openProfile() {
    delay(100)
    println("Step 3: Profile opened")
}

fun runQuestion03() = runBlocking {
    println("Login flow started")
    loadUser()
    println("Login flow completed")
}

fun main() = runQuestion03()
