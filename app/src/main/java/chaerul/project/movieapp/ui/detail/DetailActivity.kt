package chaerul.project.movieapp.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import chaerul.project.movieapp.R
import chaerul.project.movieapp.api.model.DataModel
import chaerul.project.movieapp.ui.dialog.ProgressDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val URL_BASE_IMAGE = "https://image.tmdb.org/t/p"
        private const val URL_SIZE_IMAGE = "/w185"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        extractData()
        setData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        GlobalScope.launch(Dispatchers.Main) {
            if (intent != null) {
                val id: Int? = intent.getIntExtra("id", 0)

                if (id != null) {
                    val waitData = async(Dispatchers.IO) {
                        val data: DataModel? = detailViewModel.getDataInLocal(id)
                        data
                    }

                    val dataModel: DataModel? = waitData.await()
                    if (dataModel?.id != null) {
                        menuInflater.inflate(R.menu.menu_detail_favorite, menu)
                    } else {
                        menuInflater.inflate(R.menu.menu_detail_unfavorite, menu)
                    }
                } else {
                    menuInflater.inflate(R.menu.menu_detail_unfavorite, menu)
                }
            } else {
                menuInflater.inflate(R.menu.menu_detail_unfavorite, menu)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.detailUnfavorite -> {
                detailViewModel.getDataModel().observe(this, Observer { data ->
                    detailViewModel.addFavoriteMovie(data)
                })
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun extractData() {
        if (intent != null) {
            val data: DataModel? = intent.getParcelableExtra("data")

            if (data != null) {
                detailViewModel.setDataModel(data)
            }
        }
    }

    private fun setData() {
        val dialog = ProgressDialog(this)
        dialog.show()

        detailViewModel.getDataModel().observe(this, Observer { dataModel ->
            val uri = URL_BASE_IMAGE + URL_SIZE_IMAGE + dataModel.photo
            val url = Uri.parse(uri).buildUpon().build().toString()

            Picasso.get().load(url).into(ivDetailPoster)
            tvDetailTitle.text = dataModel.title
            tvDetailReleaseDate.text = dataModel.releaseDate
            tvDetailSynopsis.text = dataModel.overview
            dialog.dismiss()
        })
    }
}
