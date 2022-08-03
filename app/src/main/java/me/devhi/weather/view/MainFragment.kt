package me.devhi.weather.view

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.gson.Gson
import kotlinx.coroutines.launch
import me.devhi.weather.data.LocationProvider
import me.devhi.weather.MainViewModel
import me.devhi.weather.R
import me.devhi.weather.api.WeatherService
import me.devhi.weather.data.database.DBProvider
import me.devhi.weather.data.WeatherRepository
import me.devhi.weather.databinding.FragmentMainBinding
import me.devhi.weather.view.adapter.DailyAdapter
import me.devhi.weather.view.adapter.HourlyAdapter


class MainFragment : Fragment() {
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private val locationProvider by lazy {
        LocationProvider(requireActivity())
    }
    private val weatherRepository by lazy {
        WeatherRepository(
            WeatherService.getWeatherService(),
            DBProvider.provideDataBase(requireContext(), Gson()).getWeatherDAO()
        )
    }
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private val dailyAdapter by lazy { DailyAdapter() }
    private val hourlyAdapter by lazy { HourlyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        with(binding) {
            dailyForecastList.adapter = dailyAdapter
            hourlyForecastList.adapter = hourlyAdapter
            swipeRefreshView.setOnRefreshListener {
                viewModel.loadWeatherData(forceReset = true)
                swipeRefreshView.isRefreshing = false
            }
        }

        lifecycleScope.launch {
            viewModel.uiState.collect {
                binding.todayUiState = it.todayWeather
                binding.addressText.text = it.address
                dailyAdapter.submitList(it.dailyForecasts)
                hourlyAdapter.submitList(it.hourlyForecasts)
            }
        }

        viewModel.apply {
            locationProvider = this@MainFragment.locationProvider
            repository = this@MainFragment.weatherRepository
        }

        if (checkPermission(permissions)) {
            requestLocationPermissions()
        } else {
            getPermission.launch(permissions)
        }

        return binding.root
    }

    private val getPermission =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            if (it.all { permission -> permission.value }) {
                viewModel.loadWeatherData()
            }
        }

    private fun requestLocationPermissions() {
        if (checkPermission(permissions)) {
            viewModel.loadWeatherData()
        }
    }

    private fun checkPermission(permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}