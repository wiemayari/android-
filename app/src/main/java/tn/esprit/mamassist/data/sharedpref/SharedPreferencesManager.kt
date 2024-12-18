import android.content.Context
class SharedPreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun saveToken(token: String, rememberMe: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.putBoolean("rememberMe", rememberMe)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun getRememberMe(): Boolean {
        return sharedPreferences.getBoolean("rememberMe", false)
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.remove("rememberMe")
        editor.apply()
    }
}