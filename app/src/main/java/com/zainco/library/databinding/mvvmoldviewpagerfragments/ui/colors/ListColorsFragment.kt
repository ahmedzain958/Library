package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zainco.library.BuildConfig
import com.zainco.library.R
import com.zainco.library.databinding.FragmentListColorsBinding
import com.zainco.library.databinding.mvvmoldviewpagerfragments.data.remote.response.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @author Wellington Costa on 31/12/2017.
 */
class ListColorsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListColorsViewModel

    private lateinit var binding: FragmentListColorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ListColorsViewModel::class.java)

        observeLoadingStatus()
        observeResponse()

        viewModel.loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_colors, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.swipeContainer.setOnRefreshListener(viewModel::loadData)
        return binding.root
    }

    private fun observeResponse() {
        viewModel.loadingStatus.observe(
            this,
            Observer { isLoading -> binding.swipeContainer.isRefreshing = isLoading ?: false })
    }

    private fun observeLoadingStatus() {
        viewModel.response.observe(
            this,
            Observer { response ->
                if (response != null && response.status == Status.SUCCESS) {
                    binding.colors = response.data
                    binding.executePendingBindings()
                } else {
                    if ((response != null && response.status == Status.ERROR) && BuildConfig.DEBUG) {
                        Log.e("get colors error", response.error.toString())
                    }
                    Snackbar.make(binding.root, R.string.load_data_error, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        )
    }

}
