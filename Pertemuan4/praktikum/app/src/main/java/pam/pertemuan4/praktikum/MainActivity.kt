package pam.pertemuan4.praktikum

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mCount = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonEmail = findViewById<Button>(R.id.button_email)
        val buttonMaps = findViewById<Button>(R.id.button_maps)

        buttonCountUp.setOnClickListener(View.OnClickListener {
            mCount++;
            if (mShowCount != null)
                mShowCount.text = mCount.toString()
        })

        buttonToast.setOnClickListener(View.OnClickListener {
            val tulisan: String = mShowCount?.text.toString()
            val toast: Toast =
                Toast.makeText(this, "Angka yang dimunculkan " + tulisan, Toast.LENGTH_LONG)
            toast.show()
        })

        buttonSwitchPage.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        })

        buttonBrowser.setOnClickListener(View.OnClickListener {
            val intentbrowse = Intent(Intent.ACTION_VIEW)
            intentbrowse.setData(Uri.parse("https://diaz.adrianz.me/"))
            startActivity(intentbrowse)
        })

        buttonEmail.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("diazz.developer@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Hello")
                putExtra(Intent.EXTRA_TEXT, "Sent from android studio")
            }
            startActivity(intent)
        })

        buttonMaps.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-6.9175,107.6191?q=Bandung"))
            startActivity(intent)
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
}