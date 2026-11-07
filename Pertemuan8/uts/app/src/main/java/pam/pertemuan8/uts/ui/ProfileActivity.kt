package pam.pertemuan8.uts.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.data.CartManager
import pam.pertemuan8.uts.data.UserSession
import pam.pertemuan8.uts.databinding.ActivityProfileBinding
import pam.pertemuan8.uts.util.hideKeyboard
import pam.pertemuan8.uts.util.openAddressOrWarn

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindUser()
        binding.etName.setText(UserSession.displayName)
        binding.etUsername.setText(UserSession.currentUser?.username.orEmpty())

        binding.btnSave.setOnClickListener { saveProfile() }
        binding.btnLogout.setOnClickListener { logout() }

        binding.bottomNav.selectedItemId = R.id.nav_profile
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    finish()
                    true
                }
                R.id.nav_order -> {
                    openAddressOrWarn(binding.root)
                    false
                }
                else -> true
            }
        }
    }

    private fun bindUser() {
        binding.tvGreeting.text = getString(R.string.home_greeting, UserSession.firstName)
        binding.tvAvatar.text = UserSession.displayName.take(1).uppercase()
    }

    private fun saveProfile() {
        val name = binding.etName.text.toString().trim()
        if (name.isEmpty()) {
            binding.tilName.error = getString(R.string.error_required)
            return
        }
        binding.tilName.error = null
        UserSession.updateName(name)
        bindUser()
        hideKeyboard()
        Snackbar.make(binding.root, R.string.profile_saved, Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.bottomNav)
            .show()
    }

    private fun logout() {
        UserSession.logout()
        CartManager.clear()
        startActivity(
            Intent(this, SignUpActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }
}