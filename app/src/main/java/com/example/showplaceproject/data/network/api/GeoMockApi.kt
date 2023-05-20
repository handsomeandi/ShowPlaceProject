package com.example.showplaceproject.data.network.api

class GeoMockApi : GeoApi {

    override suspend fun getPointsData(): PointsResponseEntity {
        return PointsResponseEntity(
            listOf(
                PointEntity(
                    45.042897, 34.283206
                ),
                PointEntity(
                    44.870198, 34.344008
                ),
                PointEntity(
                    44.419836, 34.055896
                ),
                PointEntity(
                    44.43074, 34.128593
                ),
                PointEntity(
                    45.0341187, 34.5648222
                ),
            )
        )
    }

    override suspend fun getGeoData(lat: Double, lng: Double): InfoResponseEntity {
        val mockTextEntity = TextEntity(
            id = "1",
            name = "Строители трассы Таврида",
            content =
            "Строители трассы Таврида во время работ вскрыли естественный карстовый колодец 14-метровой глубины, из которого ведут длинные галереи высотой 7—8 и шириной 4—6 метров. Высота находящихся в пещере восходящих куполов — до 12 метров. По оценке спелеологов, пещера имеет ярко выраженный гипогенный генезис, то есть образовалась напорными водами снизу[3]. Большая часть галерей заполнена глиной, что затрудняет полное прохождение полости.",
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
//            "test.glb",
//            "https://github.com/eduter/sokobot-3d/raw/master/public/3d-models/robot.glb",
//            "https://github.com/google/model-viewer/blob/master/packages/shared-assets/models/Astronaut.glb?raw=true",
            "https://github.com/handsomeandi/ShowPlaceProject/blob/development/app/src/main/assets/gazelle.glb?raw=true",
//            "https://github.com/handsomeandi/ShowPlaceProject/blob/development/app/src/main/assets/chipmunk.glb?raw=true",
//            "https://github.com/handsomeandi/ShowPlaceProject/blob/development/app/src/main/assets/seal.glb?raw=true",
            longitude = 12.34,
            latitude = 56.78
        )

        val mockAudioEntity = AudioEntity(
            id = 1,
            name = "Вход в пещеру",
//            file = "https://github.com/SergLam/Audio-Sample-files/raw/master/sample.mp3",
            file = "https://www.audioguias-bluehertz.es/audioguia_cuevas_san_jose/ruso/audioguia_bienvenida_ruso.mp3",
            longitude = 12.34,
            latitude = 56.78
        )

        return InfoResponseEntity(
            models = listOf(mockModelEntity),
            audio = listOf(
                mockAudioEntity,
                mockAudioEntity.copy(id = 2, name = "Сталактиты"),
                mockAudioEntity.copy(id = 3, name = "Сталагмиты"),
                mockAudioEntity.copy(id = 4, name = "Обитатели пещер")
            ),
            video = listOf(
                VideoEntity(
                    id = 1,
                    name = "Вход в пещеру",
                    file = "https://github.com/handsomeandi/ShowPlaceProject/blob/development/app/src/main/assets/guide.mp4?raw=true",
                    longitude = 12.34,
                    latitude = 56.78
                ),
                VideoEntity(
                    id = 1,
                    name = "Вход в пещеру",
                    file = "https://github.com/handsomeandi/ShowPlaceProject/blob/development/app/src/main/assets/guide.mp4?raw=true",
                    longitude = 12.34,
                    latitude = 56.78
                ),
                VideoEntity(
                    id = 1,
                    name = "Вход в пещеру",
                    file = "https://github.com/handsomeandi/ShowPlaceProject/blob/development/app/src/main/assets/guide.mp4?raw=true",
                    longitude = 12.34,
                    latitude = 56.78
                ),
                VideoEntity(
                    id = 1,
                    name = "Вход в пещеру",
                    file = "https://github.com/handsomeandi/ShowPlaceProject/blob/development/app/src/main/assets/guide.mp4?raw=true",
                    longitude = 12.34,
                    latitude = 56.78
                )
            ),
            text = listOf(
                TextEntity(
                    id = "1",
                    name = "Строители трассы Таврида",
                    content =
                    "Строители трассы Таврида во время работ вскрыли естественный карстовый колодец 14-метровой глубины, из которого ведут длинные галереи высотой 7—8 и шириной 4—6 метров. Высота находящихся в пещере восходящих куполов — до 12 метров. По оценке спелеологов, пещера имеет ярко выраженный гипогенный генезис, то есть образовалась напорными водами снизу[3]. Большая часть галерей заполнена глиной, что затрудняет полное прохождение полости.",
                ),
                TextEntity(
                    id = "1",
                    name = "Установленная протяжённость",
                    content =
                    "Установленная протяжённость по состоянию на лето 2018 года составляла не менее 1015 метров[4]. Пещера состоит из нескольких магистральных галерей, которые соединены между собой лабиринтами. Средняя высота составляет 6—8 метров, а ширина — 4—5 метров[5].\n",
                ),
                TextEntity(
                    id = "1",
                    name = "Пахикрокута",
                    content =
                    "Пещера Таврида служила логовом гигантской хищной короткомордой гиены пахикрокуты (Pachycrocuta brevirostris[2])[7][8][9]. В пещере найдены ископаемые остатки костей мастодонта[10][11], южного слона (Archidiskodon meridionalis), два вида лошадей (Equus stenonis и мелкая Equus sp.), два вида носорога (двурогий стефанорин[en] и эласмотерий (Elasmotherium sp.), гигантский верблюд (Paracamelus gigas), древний большерогий олень арверноцерос Верещагина (Arvernoceros verestchagini), древние быки лептобос (Leptobos sp.), эобизон (Bison (Eobison) sp.), заяц-гиполагус (Hypolagus brachygnathus), винторогие антилопы газеллоспира (Gazellospira torticornis), понтоцерос (Pontoceros ambiguus), мелкий дикобраз Виноградова (Hystrix vinogradovi), небольшой волк (Canis sp.), крупная саблезубая кошка гомотерий (Homotherium crenatidens), гигантский дманисийский страус (жил в калабрийском веке 2 млн л. н., достигал в высоту 3,5 м и весил ок. 450 кг[12][13]), а также тетерев, ястреб, стрепет и мелкий сокол[2][14].\n",
                ),

                ),
            photos = listOf(
                PhotoEntity(
                    id = "1",
                    name = "Photo 1",
                    file =
                    "https://public.blenderkit.com/thumbnails/assets/9190a0dd661e4ca5a389a88563fc0602/files/thumbnail_ecc14c90-3fa6-45bd-914a-fd5f276f932d.jpg.256x256_q85_crop-%2C.jpg",
                ),
                PhotoEntity(
                    id = "1",
                    name = "Photo 1",
                    file =
                    "https://res.cloudinary.com/featureupvote/image/upload/f_auto/T_CaveBase1_Preview_UI_qtpa0l.png",
                ),
                PhotoEntity(
                    id = "1",
                    name = "Photo 1",
                    file =
                    "https://res.cloudinary.com/featureupvote/image/upload/f_auto/T_CaveBase1_Preview_UI_qtpa0l.png",
                ),
                PhotoEntity(
                    id = "1",
                    name = "Photo 1",
                    file =
                    "https://res.cloudinary.com/featureupvote/image/upload/f_auto/T_CaveBase1_Preview_UI_qtpa0l.png",
                ),
                PhotoEntity(
                    id = "1",
                    name = "Photo 1",
                    file =
                    "https://res.cloudinary.com/featureupvote/image/upload/f_auto/T_CaveBase1_Preview_UI_qtpa0l.png",
                ),
                PhotoEntity(
                    id = "1",
                    name = "Photo 1",
                    file =
                    "https://i0.wp.com/alpshiking.swisshikingvacations.com/wp-content/uploads/2019/11/Postojna.flicker.jpg?fit=1024%2C683&ssl=1",                ),
            ),
            metadata = mockMetadataEntity
        )
    }
}

