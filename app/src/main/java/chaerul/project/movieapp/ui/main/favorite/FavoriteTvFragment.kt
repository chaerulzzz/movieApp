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
import kotlinx.android.synthetic.main.fragment_favorite_tv.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvFragment : Fragment(), MainItemAdapter.OnItemClicked {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
    }

    private lateinit var viewModel: FavoriteViewModel
    val adapter = MainItemAdapter(this)
    val dialog = context?.let { ProgressDialog(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FavoriteViewModel::class.java)

        dialog?.show()

        rvTvShows.layoutManager = GridLayoutManager(activity, 2)
        rvTvShows.adapter = adapter
        rvTvShows.isNestedScrollingEnabled = false
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTvShowsInLocal()?.observe(this, Observer { tvShows ->
            if (tvShows.size > 0) {
                adapter.setData(tvShows)
                rvTvShows.visibility = View.VISIBLE
            } else {
                tvNoData.visibility = View.VISIBLE
                rvTvShows.visibility = View.GONE
            }
            dialog?.dismiss()
        })
    }

    override fun itemClicked(data: DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", data)
        intent.putExtra("id", data.id)
        intent.putExtra("isMovie", false)
        this.startActivity(intent)
    }
}
