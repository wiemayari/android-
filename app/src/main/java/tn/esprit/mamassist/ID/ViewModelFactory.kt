package tn.esprit.mamassist.ID

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tn.esprit.mamassist.Authentification.connecte.LoginViewModel
import tn.esprit.mamassist.data.repository.UserRepository


class ViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }



            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private var factory: ViewModelFactory? = null

        fun getInstance(userRepository: UserRepository): ViewModelFactory {
            if (factory == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (factory == null) {
                        factory = ViewModelFactory(userRepository)
                    }
                }
            }
            return factory!!
        }
    }
}