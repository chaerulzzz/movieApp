package chaerul.project.movieapp.view.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chaerul.project.movieapp.MovieModel
import chaerul.project.movieapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        this.getData()
        this.displayData()
    }

    private fun getData() {
        val movie: MovieModel? = intent.getParcelableExtra("movie")
        detailViewModel = DetailViewModel(movie)
    }

    private fun displayData() {
        Picasso.get().load(detailViewModel.getPhoto()!!).into(ivDetailPhoto)
        tvDetailName.text = detailViewModel.getName()
        tvDetailDescription.text = detailViewModel.getDescription()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
