package chaerul.project.movieapp.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chaerul.project.movieapp.R
import chaerul.project.movieapp.api.model.DataModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_item.view.*

class MainItemAdapter(listeners: OnItemClicked) : RecyclerView.Adapter<MainItemAdapter.ViewHolder>() {

    companion object{
        private const val URL_BASE_IMAGE = "https://image.tmdb.org/t/p"
        private const val URL_SIZE_IMAGE = "/w185"
    }

    private val data = arrayListOf<DataModel>()
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

    fun setData(datas: ArrayList<DataModel>){
        data.clear()
        data.addAll(datas)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: DataModel = data[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { listener.itemClicked(data) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataModel) {
            val uri = URL_BASE_IMAGE + URL_SIZE_IMAGE + data.photo
            val url = Uri.parse(uri).buildUpon().build().toString()

            Picasso.get().load(url).into(itemView.ivMoviePoster)
        }
    }

    interface OnItemClicked {
        fun itemClicked(data: DataModel)
    }
}