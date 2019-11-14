package chaerul.project.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chaerul.project.movieapp.DataModel
import chaerul.project.movieapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_item.view.*

class MainItemAdapter(listeners: OnItemClicked) :
    RecyclerView.Adapter<MainItemAdapter.ViewHolder>() {

    val movies = arrayListOf<DataModel>()
    private val listener: OnItemClicked = listeners

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.main_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: DataModel = movies[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { listener.itemClicked(data) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataModel) {
            Picasso.get().load(data.photo).resize(395, 550).into(itemView.ivMoviePoster)
        }
    }

    interface OnItemClicked {
        fun itemClicked(data: DataModel)
    }
}