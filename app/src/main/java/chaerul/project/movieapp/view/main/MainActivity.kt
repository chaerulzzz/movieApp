package chaerul.project.movieapp.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import chaerul.project.movieapp.R
import chaerul.project.movieapp.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(this)
        setData()

        lvMain.adapter = adapter
        lvMain.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("movie", adapter.movies[position])
            this.startActivity(intent)
        }
    }

    private fun setData() {
        adapter = MainAdapter(this)
        adapter.movies = mainViewModel.getAllData()
    }
}
