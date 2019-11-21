package chaerul.project.movieapp.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import chaerul.project.movieapp.R
import chaerul.project.movieapp.api.model.DataModel
import chaerul.project.movieapp.ui.dialog.ProgressDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*

class DetailActivity : AppCompatActivity() {

    companion object{
        private const val URL_BASE_IMAGE = "https://image.tmdb.org/t/p"
        private const val URL_SIZE_IMAGE = "/w185"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        setData()
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
                detailViewModel.setDataModel(data)
                val dialog = ProgressDialog(this)
                dialog.show()

                detailViewModel.getDataModel().observe(this, Observer { dataModel ->
                    val uri = URL_BASE_IMAGE + URL_SIZE_IMAGE + dataModel.photo
                    val url = Uri.parse(uri).buildUpon().build().toString()

                    Picasso.get().load(url).into(ivDetailPoster)
                    tvDetailName.text = dataModel.title
                    tvDetailSynopsis.text = dataModel.overview
                    dialog.dismiss()
                })
            }
        }
    }
}
