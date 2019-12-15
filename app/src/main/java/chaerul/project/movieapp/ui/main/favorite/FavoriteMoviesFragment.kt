package chaerul.project.movieapp.ui.main.favorite

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
import chaerul.project.movieapp.ui.main.MainItemAdapter
import kotlinx.android.synthetic.main.favorite_movies_fragment.*

class FavoriteMoviesFragment : Fragment(), MainItemAdapter.OnItemClicked {

    private lateinit var viewModel: FavoriteViewModel
    val adapter = MainItemAdapter(this)
    val dialog = context?.let { ProgressDialog(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FavoriteViewModel::class.java)

        dialog?.show()

        rvMovie.layoutManager = GridLayoutManager(activity, 2)
        rvMovie.adapter = adapter
        rvMovie.isNestedScrollingEnabled = false
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMoviesInLocal()?.observe(this, Observer { movies ->
            if (movies.size > 0) {
                adapter.setData(movies)
                tvNoData.visibility = View.GONE
                rvMovie.visibility = View.VISIBLE
            } else {
                tvNoData.visibility = View.VISIBLE
                rvMovie.visibility = View.GONE
            }
            dialog?.dismiss()
        })
    }

    override fun itemClicked(data: DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", data)
        intent.putExtra("id", data.id)
        intent.putExtra("isMovie", true)
        this.startActivity(intent)
    }
}
