package chaerul.project.movieapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import chaerul.project.movieapp.R
import chaerul.project.movieapp.api.model.DataModel
import chaerul.project.movieapp.ui.detail.DetailActivity
import chaerul.project.movieapp.ui.dialog.ProgressDialog
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

        val adapter = MainItemAdapter(this)
        val dialog = context?.let { ProgressDialog(it) }
        dialog?.show()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        viewModel.setMovies()

        rvMovie.layoutManager = GridLayoutManager(activity, 2)
        rvMovie.adapter = adapter
        rvMovie.isNestedScrollingEnabled = false

        viewModel.getAllMovies().observe(this, Observer { movies ->
            adapter.setData(movies)
            dialog?.dismiss()
        })
    }

    override fun itemClicked(data: DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", data)
        intent.putExtra("id", data.id)
        this.startActivity(intent)
    }
}
