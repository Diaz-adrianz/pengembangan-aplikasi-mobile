package pam.pertemuan8.uts.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.adapter.OrderItemAdapter
import pam.pertemuan8.uts.data.CartManager
import pam.pertemuan8.uts.data.OrderManager
import pam.pertemuan8.uts.data.UserSession
import pam.pertemuan8.uts.databinding.ActivityAddressBinding
import pam.pertemuan8.uts.util.hideKeyboard
import pam.pertemuan8.uts.util.toRupiah

class AddressActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (CartManager.isEmpty) {
            Toast.makeText(this, R.string.cart_empty_warning, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.etRecipient.setText(UserSession.displayName)
        binding.rvOrder.layoutManager = LinearLayoutManager(this)
        binding.rvOrder.isNestedScrollingEnabled = false
        binding.rvOrder.adapter = OrderItemAdapter(CartManager.items)
        binding.tvTotal.text = CartManager.totalPrice.toRupiah()

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.btnOrder.setOnClickListener { placeOrder() }
    }

    private fun placeOrder() {
        val recipient = binding.etRecipient.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val landmark = binding.etLandmark.text.toString().trim()

        val required = getString(R.string.error_required)
        binding.tilRecipient.error = if (recipient.isEmpty()) required else null
        binding.tilAddress.error = if (address.isEmpty()) required else null
        if (recipient.isEmpty() || address.isEmpty()) return

        hideKeyboard()
        OrderManager.placeOrder(recipient, address, landmark)
        startActivity(Intent(this, ConfirmationActivity::class.java))
        finish()
    }
}