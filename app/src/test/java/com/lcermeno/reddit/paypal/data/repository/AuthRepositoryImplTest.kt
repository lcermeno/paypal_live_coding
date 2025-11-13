package com.lcermeno.reddit.paypal.data.repository

import com.lcermeno.reddit.paypal.domain.model.Credentials
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class AuthRepositoryImplTest {

    private val apiService = mockk<ApiServiceImpl>()
    private val repository = AuthRepositoryImpl()

    @Test
    fun `successful login`() = runTest {

        every { apiService.login(any()) } returns true
        val credentials = Credentials("lcermeno", "1234")
        val result = repository.login(credentials).first()
        assertTrue(result)
    }

    @Test
    fun `successful failed`() = runTest {
        every { apiService.login(any()) } returns false
        val credentials = Credentials("lcermeno", "12")
        val result = repository.login(credentials).first()
        assertFalse(result)
    }
  
}