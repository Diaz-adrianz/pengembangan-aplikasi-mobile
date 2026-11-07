package pam.pertemuan8.uts.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.data.UserSession
import pam.pertemuan8.uts.databinding.ActivityRegisterBinding
import pam.pertemuan8.uts.util.hideKeyboard
import pam.pertemuan8.uts.util.openHomeAsNewTask

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener { attemptRegister() }
        binding.btnToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun attemptRegister() {
        val name = binding.etFullName.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString()

        val required = getString(R.string.error_required)
        binding.tilFullName.error = if (name.isEmpty()) required else null
        binding.tilUsername.error = if (username.isEmpty()) required else null
        binding.tilPassword.error = when {
            password.isEmpty() -> required
            password.length < 6 -> getString(R.string.error_password_min)
            else -> null
        }
        if (binding.tilFullName.error != null ||
            binding.tilUsername.error != null ||
            binding.tilPassword.error != null
        ) return

        hideKeyboard()
        if (UserSession.register(name, username, password)) {
            openHomeAsNewTask()
        } else {
            binding.tilUsername.error = getString(R.string.error_username_taken)
        }
    }
}