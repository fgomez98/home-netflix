package com.ar.homenetflixtv.landing.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import com.ar.homenetflixtv.R

class LandingFragment : BrowseSupportFragment() {

    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareBackgroundManager()
        setupUIElements()
//        loadRows()
        setupEventListeners()
    }

    private fun loadRows() {
        TODO("Not yet implemented")
    }

    private fun setupUIElements() {
        // Badge and Title. Badge, when set, takes precedent over title
//        badgeDrawable = resources.getDrawable(R.drawable.app_icon_your_company)
        title = getString(R.string.browse_title)

        // Headers
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireContext(), R.color.fastlane_background)

        // set search icon color
        searchAffordanceColor = ContextCompat.getColor(requireContext(), R.color.search_opaque)
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
            Toast.makeText(requireActivity(), "Item View Clicked", Toast.LENGTH_LONG).show()
        }
        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            Toast.makeText(requireActivity(), "Item View Selected", Toast.LENGTH_LONG).show()
        }
    }


}