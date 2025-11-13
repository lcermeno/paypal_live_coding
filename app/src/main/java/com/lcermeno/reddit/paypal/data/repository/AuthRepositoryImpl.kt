package com.lcermeno.reddit.paypal.data.repository

import com.lcermeno.reddit.paypal.domain.model.Credentials
import com.lcermeno.reddit.paypal.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl: AuthRepository {

    private val service = ApiServiceImpl()

    override fun login(credentials: Credentials): Flow<Boolean> {
        return flow {
            val valid = service.login(credentials)
            emit(valid)
        }
    }
}

class ApiServiceImpl {

    fun login(credentials: Credentials): Boolean {
        return credentials.password == "1234" && credentials.username == "lcermeno"
    }
}