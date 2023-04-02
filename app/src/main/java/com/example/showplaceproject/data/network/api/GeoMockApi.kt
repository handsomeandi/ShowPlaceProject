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
            file =
//            "test.obj"
//            "test.glb"
//            "https://github.com/eduter/sokobot-3d/raw/master/public/3d-models/robot.glb"
//            "https://github.com/KhronosGroup/glTF-Sample-Models/blob/master/1.0/Box/glTF/Box.gltf"
            "https://github.com/google/model-viewer/blob/master/packages/shared-assets/models/Astronaut.glb?raw=true",
            longitude = 12.34,
            latitude = 56.78
        )

        val mockAudioEntity = AudioEntity(
            id = 1,
            name = "Audio 1",
            file = "https://file-examples.com/storage/fef89aabc36429826928b9c/2017/11/file_example_MP3_700KB.mp3",
            longitude = 12.34,
            latitude = 56.78
        )

        return InfoResponseEntity(
            models = listOf(mockModelEntity),
            audio = listOf(
                mockAudioEntity,
                mockAudioEntity.copy(id = 2, name = "Audio 2"),
                mockAudioEntity.copy(id = 3, name = "Audio 3"),
                mockAudioEntity.copy(id = 4, name = "Audio 4")
            ),
            text = listOf(mockTextEntity),
            photos = listOf(mockPhotoEntity),
            metadata = mockMetadataEntity
        )
    }
}