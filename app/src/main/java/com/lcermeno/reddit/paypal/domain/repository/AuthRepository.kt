package com.lcermeno.reddit.paypal.domain.repository

import com.lcermeno.reddit.paypal.domain.model.Credentials
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(credentials: Credentials): Flow<Boolean>
}