package com.ar.homenetflixtv.landing.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.ar.homenetflixtv.R
import com.ar.homenetflixtv.landing.card.VideoCardPresenter
import com.ar.homenetflixtv.model.Categories
import com.ar.homenetflixtv.model.Video
import com.ar.homenetflixtv.playback.PlaybackActivity


class LandingFragment : BrowseSupportFragment(), LandingView {

    private val NUM_ROWS = 4
    private lateinit var mAdapter: ArrayObjectAdapter
    private lateinit var presenter: LandingPresenter

    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.browse_title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createPresenter()

        prepareBackgroundManager()
        setupUIElements()
        setupEventListeners()

        presenter.onViewAttached()
    }

    private fun createPresenter() {
        presenter = LandingPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDetached()
    }

    private fun setupUIElements() {
        // Badge and Title. Badge, when set, takes precedent over title
//        badgeDrawable = resources.getDrawable(R.drawable.app_icon_your_company)
        title = getString(R.string.browse_title)

        // Headers, we do not need them for now
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireContext(), R.color.fastlane_background)
    }

    private fun prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(requireActivity().window)
        mDefaultBackground = ContextCompat
            .getDrawable(requireActivity(), R.drawable.default_background)
        mMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(mMetrics)
    }

    private fun setupEventListeners() {
        setOnSearchClickedListener {
            Toast.makeText(requireActivity(), "Search Activity", Toast.LENGTH_LONG).show()
        }
        setOnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            startActivity(Intent(requireContext(), PlaybackActivity::class.java))
            /*Toast.makeText(requireActivity(), "Item View Clicked", Toast.LENGTH_LONG).show()*/
        }
        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            /*Toast.makeText(requireActivity(), "Item View Selected", Toast.LENGTH_LONG).show()*/
        }
    }

    override fun showVideos(videos: List<Video>) {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter().apply {
            shadowEnabled = false
        })
        for (cat in Categories.values()) {
            val categoryListRowAdapter = ArrayObjectAdapter(VideoCardPresenter()).apply {
                videos.forEach { v -> add(v) }
            }
            HeaderItem(cat.getValue()).also { header ->
                rowsAdapter.add(ListRow(header, categoryListRowAdapter))
            }
        }
        mAdapter = rowsAdapter
        adapter = mAdapter
    }

}
