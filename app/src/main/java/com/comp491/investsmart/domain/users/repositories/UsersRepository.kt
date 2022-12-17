package com.comp491.investsmart.domain.users.repositories

interface UsersRepository {

    suspend fun logOut()
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(username: String, email: String, password: String)
}