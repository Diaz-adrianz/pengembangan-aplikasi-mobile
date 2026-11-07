package pam.pertemuan8.uts.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.adapter.OrderItemAdapter
import pam.pertemuan8.uts.data.OrderManager
import pam.pertemuan8.uts.data.UserSession
import pam.pertemuan8.uts.databinding.ActivityConfirmationBinding
import pam.pertemuan8.uts.util.openHomeAsNewTask
import pam.pertemuan8.uts.util.toRupiah

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val order = OrderManager.lastOrder
        if (order == null) {
            openHomeAsNewTask()
            return
        }

        binding.tvGreeting.text = getString(R.string.home_greeting, UserSession.firstName)
        binding.tvOrderId.text = getString(R.string.order_id_format, order.id)

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.isNestedScrollingEnabled = false
        binding.rvItems.adapter = OrderItemAdapter(order.items)
        binding.tvTotal.text = order.total.toRupiah()

        binding.tvRecipient.text = order.recipient
        binding.tvAddress.text = order.address
        binding.tvLandmark.text = order.landmark
        binding.tvLandmark.isVisible = order.landmark.isNotEmpty()

        binding.btnOk.setOnClickListener { openHomeAsNewTask() }

        // Tombol back selalu kembali ke Home, bukan ke form alamat.
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = openHomeAsNewTask()
        })
    }
}