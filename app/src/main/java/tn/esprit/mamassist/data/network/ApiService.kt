package tn.esprit.mamassist.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import tn.esprit.mamassist.Tools.DailyCheckIn
import retrofit2.Call


data class Baby(
    var name: String = "",
    var age: String = "",
    var weight: String = "",
    var height: String = "",
    var healthIssues: String = "",
    var behavior: String = ""
)

data class LoginRequest(
    val email: String,
    val password: String
)


data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)

data class SignUpRequest(
    val name : String,
    val email: String,
    val password: String
)

data class SignUpResponse(
    val success: Boolean,
    val message: String
)

data class ForgotPasswordRequest(val email: String)
data class ForgotPasswordResponse(val message: String, val resetCode: String)

data class VerifyCodeRequest(val email: String ,val code: String)

data class VerifyCodeResponse(val message: String)

data class ResetPasswordRequest(
    val email: String,
    val code: String,
    val newPassword: String
)

object Routes {
    val Login = Route("login")
    val ConfirmCode = Route("confirmCode")
    val ResetPass = Route("resetPass")
}

data class Route(val route: String)

interface ApiService {

    @POST("daily-checkin")
    fun addDailyCheckIn(@Body dailyCheckIn: DailyCheckIn): Call<DailyCheckIn>

    @POST("auth/signup")
    suspend fun signUp(@Body signupRequest: SignUpRequest): Response<SignUpResponse>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/forgot-password")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @POST("auth/verify-reset-code")
    fun verifyResetCode(@Body request: VerifyCodeRequest): Call<VerifyCodeResponse>

    @POST("auth/reset-password")
    fun resetPassword(@Body request: ResetPasswordRequest): Call<Void>
}