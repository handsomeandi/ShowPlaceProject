package com.example.showplaceproject.presentation.audio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showplaceproject.data.network.repository.GeoRemoteRepository
import com.example.showplaceproject.domain.AudioModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AudioScreenViewModel @Inject constructor(
    val remoteDataSource: GeoRemoteRepository
) : ViewModel() {
    val audio: LiveData<List<AudioModel>?>
        get() = _audio
    private val _audio: MutableLiveData<List<AudioModel>?> = MutableLiveData()


    suspend fun init() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("Error Geo", throwable.message.toString())
        }
        val response = withContext(Dispatchers.IO + handler) {
            remoteDataSource.getGeoData(32.0, 32.0)
        }
        _audio.value = response.audio
    }
}