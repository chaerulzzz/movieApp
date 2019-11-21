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
import kotlinx.android.synthetic.main.fragment_tv.*

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

        val adapter = MainItemAdapter(this)
        val dialog = context?.let { ProgressDialog(it) }
        dialog?.show()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        viewModel.setTvs()

        rvTvShows.layoutManager = GridLayoutManager(activity, 2)
        rvTvShows.adapter = adapter

        viewModel.getAllTvShows().observe(this, Observer { tvShows ->
            adapter.setData(tvShows)
            dialog?.dismiss()
        })
    }

    override fun itemClicked(data: DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", data)
        this.startActivity(intent)
    }
}
