package chaerul.project.movieapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import chaerul.project.movieapp.DataModel
import chaerul.project.movieapp.R
import chaerul.project.movieapp.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(), MainItemAdapter.OnItemClicked {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        rvMovie.layoutManager = GridLayoutManager(activity, 2)
        rvMovie.adapter = MainItemAdapter(this)

        setData()
    }

    private fun setData() {
        val adapter: MainItemAdapter = rvMovie.adapter as MainItemAdapter

        adapter.movies.addAll(viewModel.getAllMovies())
    }

    override fun itemClicked(data: DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", data)
        this.startActivity(intent)
    }
}
