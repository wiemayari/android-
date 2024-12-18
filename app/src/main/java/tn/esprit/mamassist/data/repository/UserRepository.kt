package tn.esprit.mamassist.data.repository

import retrofit2.Response
import tn.esprit.mamassist.data.network.ApiService
import tn.esprit.mamassist.data.network.LoginRequest
import tn.esprit.mamassist.data.network.LoginResponse
import tn.esprit.mamassist.data.network.SignUpRequest
import tn.esprit.mamassist.data.network.SignUpResponse

class UserRepository(private val apiService: ApiService) {

    // Function to create a new user using the API with error handling
    suspend fun signUp(name: String, email: String, password: String): Response<SignUpResponse> {
        val signUpRequest = SignUpRequest(name, email, password)
        return apiService.signUp(signUpRequest)
    }

    // Function to login a user
    suspend fun login(email: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(email, password)
        return try {
            // Call the API to authenticate the user
            val response = apiService.login(loginRequest)

            // Handle successful response
            if (response.isSuccessful) {
                response
            } else {
                // Return error response in case of failure
                Response.error(response.code(), response.errorBody() ?: throw Exception("Login failed"))
            }
        } catch (exception: Exception) {
            throw Exception("Login failed: ${exception.message}")
        }
    }
}
