package com.example.movie.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class Registration(private val context: Context) {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: String, password: String) {
        (email.isNotEmpty() && password.isNotEmpty()).let {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        checkedLogged()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    suspend fun loginUser(email: String, password: String) {
        (email.isNotEmpty() && password.isNotEmpty()).let {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        checkedLogged()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }


    fun logout() {
        auth.signOut()
    }

    private fun checkedLogged() {
        if (auth.currentUser == null)
            Toast.makeText(context, "You are not logged in", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context, "You are logged in", Toast.LENGTH_LONG).show()

    }
}