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
import kotlinx.android.synthetic.main.fragment_tv.*

/**
 * A simple [Fragment] subclass.
 */
class TVFragment : Fragment(), MainItemAdapter.OnItemClicked {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    private lateinit var viewModel: MainViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        rvTvShows.layoutManager = GridLayoutManager(activity, 2)
        rvTvShows.adapter = MainItemAdapter(this)

        setData()
    }

    private fun setData() {
        val adapter: MainItemAdapter = rvTvShows.adapter as MainItemAdapter

        adapter.movies.addAll(viewModel.getAllTvShows())
    }

    override fun itemClicked(data: DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", data)
        this.startActivity(intent)
    }
}
