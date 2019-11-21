package chaerul.project.movieapp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import chaerul.project.movieapp.R
import kotlinx.android.synthetic.main.progress_dialog.*

class ProgressDialog(context: Context): Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.progress_dialog)
        setCanceledOnTouchOutside(false)
        progress_bar.visibility = View.VISIBLE
    }
}