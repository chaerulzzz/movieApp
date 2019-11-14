package chaerul.project.movieapp.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import chaerul.project.movieapp.DataModel
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
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        setData()
        setDisplayData()
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

    private fun setData() {
        if (intent != null) {
            val data: DataModel? = intent.getParcelableExtra("data")

            if (data != null) {
                detailViewModel.setMovieModel(data)
            }
        }
    }

    private fun setDisplayData() {
        Picasso.get().load(detailViewModel.getPhoto()).into(ivDetailPoster)
        tvDetailName.text = detailViewModel.getName()
        tvDetailSynopsis.text = detailViewModel.getDescription()
    }


}
