package pam.pertemuan8.uts.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.adapter.CartAdapter
import pam.pertemuan8.uts.adapter.ProductAdapter
import pam.pertemuan8.uts.data.CartManager
import pam.pertemuan8.uts.data.Category
import pam.pertemuan8.uts.data.MenuRepository
import pam.pertemuan8.uts.data.UserSession
import pam.pertemuan8.uts.databinding.ActivityHomeBinding
import pam.pertemuan8.uts.databinding.SheetCartBinding
import pam.pertemuan8.uts.util.openAddressOrWarn
import pam.pertemuan8.uts.util.toRupiah

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var productAdapter: ProductAdapter
    private val products = MenuRepository.products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = ProductAdapter(products) { updateCartBar() }
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = productAdapter

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            applyFilter(checkedIds.firstOrNull() ?: R.id.chipAll)
        }

        binding.cartBar.setOnClickListener { showCartSheet() }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_order -> {
                    openAddressOrWarn(binding.root)
                    false
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    false
                }
                else -> true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvGreeting.text = getString(R.string.home_greeting, UserSession.firstName)
        productAdapter.notifyDataSetChanged()
        updateCartBar()
    }

    private fun applyFilter(checkedId: Int) {
        val filtered = when (checkedId) {
            R.id.chipDrink -> products.filter { it.category == Category.DRINK }
            R.id.chipFood -> products.filter { it.category == Category.FOOD }
            else -> products
        }
        productAdapter.submit(filtered)
    }

    private fun updateCartBar() {
        val qty = CartManager.totalQty
        binding.cartBar.isVisible = qty > 0
        binding.tvCartCount.text = getString(R.string.cart_items_format, qty)
        binding.tvCartTotal.text = CartManager.totalPrice.toRupiah()
    }

    private fun showCartSheet() {
        val dialog = BottomSheetDialog(this)
        val sheet = SheetCartBinding.inflate(layoutInflater)
        dialog.setContentView(sheet.root)

        fun render() {
            if (CartManager.isEmpty) {
                dialog.dismiss()
                return
            }
            sheet.tvSheetTotal.text = CartManager.totalPrice.toRupiah()
        }

        sheet.rvCart.layoutManager = LinearLayoutManager(this)
        sheet.rvCart.isNestedScrollingEnabled = false
        sheet.rvCart.adapter = CartAdapter(CartManager.items) {
            render()
            productAdapter.notifyDataSetChanged()
            updateCartBar()
        }
        sheet.btnCheckout.setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, AddressActivity::class.java))
        }

        render()
        dialog.show()
    }
}