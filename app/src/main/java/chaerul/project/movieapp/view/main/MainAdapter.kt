package chaerul.project.movieapp.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import chaerul.project.movieapp.MovieModel
import chaerul.project.movieapp.R
import com.squareup.picasso.Picasso

class MainAdapter internal constructor(private val context: Context): BaseAdapter() {
    internal var movies = arrayListOf<MovieModel>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false)
        }

        val viewHolder = ViewHolder(itemView as View)
        val item = getItem(position) as MovieModel
        viewHolder.bind(item)

        return itemView
    }

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = movies.size

    private inner class ViewHolder internal constructor(view: View) {
        private val tvName: TextView = view.findViewById(R.id.tvMovieName)
        private val tvDescription: TextView = view.findViewById(R.id.tvMovieDesc)
        private val ivPhoto: ImageView = view.findViewById(R.id.ivMoviePhoto)

        internal fun bind(movie: MovieModel) {
            tvName.text = movie.name
            tvDescription.text = movie.descrption
            Picasso.get().load(movie.photo).into(ivPhoto)
        }
    }
}