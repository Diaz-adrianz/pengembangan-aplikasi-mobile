package pam.pertemuan7.dessertrecycleview


import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DessertAdapter(private val dessertList: List<Dessert>) :
    RecyclerView.Adapter<DessertAdapter.DessertViewHolder>() {

    class DessertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgContainer: FrameLayout = itemView.findViewById(R.id.imgContainer)
        val imgDessert: ImageView = itemView.findViewById(R.id.imgDessert)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemdesert, parent, false)
        return DessertViewHolder(view)
    }

    override fun onBindViewHolder(holder: DessertViewHolder, position: Int) {
        val dessert = dessertList[position]

        holder.tvDescription.text = dessert.description
        holder.imgDessert.setImageResource(dessert.imageRes)

        val circleBg = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(Color.parseColor(dessert.colorHex))
        }
        holder.imgContainer.background = circleBg
    }


    override fun getItemCount(): Int = dessertList.size
}
