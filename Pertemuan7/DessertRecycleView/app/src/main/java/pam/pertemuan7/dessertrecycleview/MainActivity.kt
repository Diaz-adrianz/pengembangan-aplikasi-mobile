package pam.pertemuan7.dessertrecycleview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val desserts = listOf(
            Dessert(
                description = "Donuts are glazed and sprinkled with candy.",
                imageRes = R.drawable.donut_circle,
                colorHex = "#C1E8C5"
            ),
            Dessert(
                description = "Ice cream sandwiches have chocolate wafers and vanilla filling.",
                imageRes = R.drawable.icecream_circle,
                colorHex = "#BBE0F8"
            ),
            Dessert(
                description = "Froyo is premium self-serve frozen yogurt.",
                imageRes = R.drawable.froyo_circle,
                colorHex = "#E4BCDF"
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DessertAdapter(desserts)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}