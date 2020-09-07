package com.zainco.library.androiddevsrunningtracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.GoogleMap
import com.zainco.library.R
import com.zainco.library.androiddevsrunningtracker.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tracking.*

@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {
    private val viewModel: MainViewModel by viewModels()
    private var map: GoogleMap? =
        null/*don't confuse between GoogleMap(actual map object) and mapview(view that displays the google map) */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mapview has its own lifecycle as well.Hence, we can synchronize its lifecycle events with the fragment lifecycle events
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->/*the loaded map*/
            map = googleMap
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
        /*
        * as you saw in mapView.getMapAsync { googleMap ->/*the loaded map*/
it loads map asynchronously so it takes time to load the map so no need to load it every time */

    }
}