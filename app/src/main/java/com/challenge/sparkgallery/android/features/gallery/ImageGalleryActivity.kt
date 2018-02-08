package com.challenge.sparkgallery.android.features.gallery

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.challenge.sparkgallery.R
import com.challenge.sparkgallery.android.CYAApplication
import com.challenge.sparkgallery.data.gallery.model.Image
import com.challenge.sparkgallery.android.common.BaseActivity
import com.challenge.sparkgallery.android.common.di.DaggerUIComponent
import kotlinx.android.synthetic.main.gallery_activity.*
import javax.inject.Inject
import com.challenge.sparkgallery.android.features.auth.AuthActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


/**
 * Created by 805640 on 04.02.2018.
 */
class ImageGalleryActivity: BaseActivity(), ImageGalleryView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var imageGalleryPresenter: ImageGalleryPresenter

    override fun getLayoutID(): Int = R.layout.gallery_activity
    override fun getTitleID(): Int = R.string.gallery_title

    private var gridMode = false
    private var loading: Boolean = false

    companion object {

        private val RC_AUTH: Int = 1099

        val GRID_MODE = "GridModeOn"
        val LOADING = "Loading"

        fun getIntent(context: Context): Intent {
            return Intent(context, ImageGalleryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerUIComponent.builder()
                .appComponent(CYAApplication.applicationComponent)
                .build()
                .inject(this)
        gridMode = savedInstanceState?.getBoolean(GRID_MODE, false) == true
        loading = savedInstanceState?.getBoolean(LOADING, false) == true
        initViews()
        imageGalleryPresenter.attachView(this)
        refresh()
    }

    private fun initViews() {
        refresh_layout.setOnRefreshListener(this)
        add_first_btn.setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime < 1000) {
                return@setOnClickListener
            }
            lastClickTime = System.currentTimeMillis()
            imageGalleryPresenter.onNewPictureClicked()
        }
        fab_add.setOnClickListener({
            if (System.currentTimeMillis() - lastClickTime < 1000) {
                return@setOnClickListener
            }
            lastClickTime = System.currentTimeMillis()
            imageGalleryPresenter.onNewPictureClicked()
        })
        gallery_recycler_view.adapter = GalleryAdapter(this)
        fab_add.invalidate()
        refresh_layout.isRefreshing = loading
        if (gridMode) {
            switchToGrid()
        } else {
            switchToList()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.gallery_menu, menu)
        val gridMenuItem = menu?.findItem(R.id.menu_grid)
        val listMenuItem = menu?.findItem(R.id.menu_list)

        gridMenuItem?.setOnMenuItemClickListener {
            gridMode = true
            gridMenuItem.setVisible(!gridMode)
            listMenuItem?.setVisible(gridMode)
            switchToGrid()
            invalidateOptionsMenu()

            true
        }

        listMenuItem?.setOnMenuItemClickListener {
            gridMode = false
            gridMenuItem?.setVisible(!gridMode)
            listMenuItem.setVisible(gridMode)
            switchToList()
            invalidateOptionsMenu()
            true
        }

        gridMenuItem?.setVisible(!gridMode)
        listMenuItem?.setVisible(gridMode)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(GRID_MODE, gridMode)
        outState?.putBoolean(LOADING, loading)
    }

    private fun switchToGrid() {
        val manager = GridLayoutManager(this, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when ((gallery_recycler_view.adapter as GalleryAdapter).getItemViewType(position)) {
                    GalleryAdapter.HEADER_TYPE -> 3
                    else -> 1
                }
            }
        }
        gallery_recycler_view.setLayoutManager(manager)
        gallery_recycler_view.adapter.notifyDataSetChanged()
    }

    private fun switchToList() {
        gallery_recycler_view.layoutManager = LinearLayoutManager(this)
        gallery_recycler_view.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        imageGalleryPresenter.detachView()
    }

    override fun onRefresh() {
        imageGalleryPresenter.refresh()
    }

    override fun showList() {
        gallery_recycler_view.visibility = View.VISIBLE
        empty_state.visibility = View.GONE
        fab_add.setIndeterminate(true)
        fab_add.visibility = View.VISIBLE
        fab_add.setIndeterminate(false)
    }

    override fun showEmpty() {
        gallery_recycler_view.visibility = View.GONE
        empty_state.visibility = View.VISIBLE
        fab_add.visibility = View.GONE
    }

    override fun showLoading() {
        loading = true
        fab_add.setIndeterminate(true)
        refresh_layout.isRefreshing = true
    }

    override fun hideLoading() {
        loading = false
        fab_add.hideProgress()
        refresh_layout.isRefreshing = false
    }

    override fun showError(throwable: Throwable) {
        Log.e(this.localClassName, throwable.localizedMessage)
        AlertDialog.Builder(this)
                .setTitle(R.string.error_title)
                .setMessage(throwable.message)
                .setNegativeButton(R.string.retry, { _, _ -> refresh()})
                .setPositiveButton(R.string.accept, {it, _ -> it.dismiss()})
                .create().show()
    }

    override fun addImages(images: List<Image>) {
        (gallery_recycler_view.adapter as GalleryAdapter).setImages(images)
    }

    override fun loadGalleryPage(page: Int) {
        imageGalleryPresenter.loadGalleryPage(page)
    }

    override fun refresh() {
        imageGalleryPresenter.refresh()
    }

    override fun addNewImage() {
        if (!loading) {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == RC_AUTH && resultCode == Activity.RESULT_OK) {
            refresh()
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                imageGalleryPresenter.uploadImage(result.uri.path, result.uri.lastPathSegment)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                showError(result.error)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun login() {
        startActivityForResult(AuthActivity.getIntent(this), RC_AUTH)
//        Navigator.invokeAuth(this)
    }
}