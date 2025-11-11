package com.lcermeno.reddit.paypal.data.repository

import com.lcermeno.reddit.paypal.domain.model.Credentials
import com.lcermeno.reddit.paypal.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl: AuthRepository {
    override fun login(credentials: Credentials): Flow<Boolean> {
        return flow {
            val valid = credentials.password == "1234" && credentials.username == "lcermeno"
            emit(valid)
        }
    }
}