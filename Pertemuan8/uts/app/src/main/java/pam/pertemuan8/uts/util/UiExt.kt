package pam.pertemuan8.uts.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.data.CartManager
import pam.pertemuan8.uts.ui.AddressActivity
import pam.pertemuan8.uts.ui.HomeActivity
import java.text.NumberFormat
import java.util.Locale

fun Int.toRupiah(): String =
    "Rp " + NumberFormat.getNumberInstance(Locale("id", "ID")).format(this)

fun Activity.hideKeyboard() {
    currentFocus?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.openHomeAsNewTask() {
    startActivity(
        Intent(this, HomeActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    )
}

fun Activity.openAddressOrWarn(anchor: View) {
    if (CartManager.isEmpty) {
        Snackbar.make(anchor, R.string.cart_empty_warning, Snackbar.LENGTH_SHORT).show()
    } else {
        startActivity(Intent(this, AddressActivity::class.java))
    }
}