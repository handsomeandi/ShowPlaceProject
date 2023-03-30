package com.example.showplaceproject.data.network.api

class GeoMockApi : GeoApi {

    override suspend fun getGeoData(lat: Double, lng: Double): InfoResponseEntity {
        val mockTextEntity = TextEntity(
            id = "1",
            name = "Text 1",
            content = "This is some text content"
        )

        val mockPhotoEntity = PhotoEntity(
            id = "1",
            name = "Photo 1",
            file = "photo1.jpg"
        )

        val mockMetadataEntity = MetadataEntity(
            total = 2
        )

        val mockModelEntity = ModelEntity(
            id = 1,
            name = "Model 1",
            file = "model1.obj",
            longitude = 12.34,
            latitude = 56.78
        )

        val mockAudioEntity = AudioEntity(
            id = 1,
            name = "Audio 1",
            file = "audio1.mp3",
            longitude = 12.34,
            latitude = 56.78
        )

        return InfoResponseEntity(
            models = listOf(mockModelEntity),
            audio = listOf(mockAudioEntity),
            text = listOf(mockTextEntity),
            photos = listOf(mockPhotoEntity),
            metadata = mockMetadataEntity
        )
    }
}