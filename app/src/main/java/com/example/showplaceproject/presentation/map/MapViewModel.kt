package com.example.showplaceproject.presentation.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showplaceproject.data.network.repository.GeoRemoteRepository
import com.yandex.mapkit.geometry.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    val remoteDataSource: GeoRemoteRepository
) : ViewModel() {
    val points: LiveData<List<Point>?>
        get() = _points
    private val _points: MutableLiveData<List<Point>?> = MutableLiveData()


    suspend fun init() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("Error Geo", throwable.message.toString())
        }
        val response = withContext(Dispatchers.IO + handler) {
            remoteDataSource.getPoints()
        }
        _points.value = response.points.map {
            Point(it.lat, it.lon)
        }
    }
}