package pam.pertemuan8.uts.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.data.UserSession
import pam.pertemuan8.uts.databinding.ActivityLoginBinding
import pam.pertemuan8.uts.util.hideKeyboard
import pam.pertemuan8.uts.util.openHomeAsNewTask

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { attemptLogin() }
        binding.btnToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun attemptLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString()

        binding.tilUsername.error =
            if (username.isEmpty()) getString(R.string.error_required) else null
        binding.tilPassword.error =
            if (password.isEmpty()) getString(R.string.error_required) else null
        if (username.isEmpty() || password.isEmpty()) return

        hideKeyboard()
        if (UserSession.login(username, password)) {
            openHomeAsNewTask()
        } else {
            Snackbar.make(binding.root, R.string.login_failed, Snackbar.LENGTH_LONG).show()
        }
    }
}