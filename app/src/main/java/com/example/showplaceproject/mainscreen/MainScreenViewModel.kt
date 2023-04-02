package com.example.showplaceproject.mainscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showplaceproject.data.db.repository.GeoLocalRepository
import com.example.showplaceproject.data.network.repository.GeoRemoteRepository
import com.example.showplaceproject.domain.GeoInfoModel
import com.google.ar.sceneform.rendering.ModelRenderable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val localRepository: GeoLocalRepository,
    val remoteRepository: GeoRemoteRepository
) : ViewModel() {
    val model: MutableLiveData<ModelRenderable> = MutableLiveData()
    val geoModel: MutableLiveData<GeoInfoModel> = MutableLiveData()
    private val isModelAdded: MutableLiveData<Boolean> = MutableLiveData(false)

    suspend fun init() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            Log.d("Error Geo", throwable.message.toString())
        }

        val response = withContext(handler + Dispatchers.IO) {
            localRepository.getGeoData()
        }
        response?.let {
            geoModel.value = it
        } ?: run {
            val remoteResponse = withContext(handler + Dispatchers.IO) {
                remoteRepository.getGeoData(32.0, 32.0)
            }
            geoModel.value = remoteResponse
//                    localRepository.insertGeoData(it)
        }
    }


    fun isModelAdded() = isModelAdded.value

    fun changeModelAdded(isAdded: Boolean) {
        isModelAdded.value = isAdded
    }
}