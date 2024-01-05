package com.example.hwangtube.data

object VideoList {
    // TODO: Implement encapsulation for `list`.
    //  Provide appropriate interfaces for access and modification, instead of direct access.
    val list: MutableList<Video> = mutableListOf()
    val size get() = list.count()

    init {
        // Add temp data
        add(
            Video(
                "Skibidi toilet", //channel title
                "Skibidi Toilet Titan cameraman VS Pepperman VS Elemental VS Amanda The Adventurer #skibiditoilet", // title
                "https://i.ytimg.com/vi/nX1W4xDkujI/mqdefault.jpg", //thumbnails.medium
                "Skibidi Toilet Titan cameraman VS Pepperman VS Elemental VS Amanda The Adventurer #skibiditoilet #tileshop #13 Thank you ..." //description
            )
        )
        add(
            Video(
                "dreamyas", //channel title
                "(REWORKS + UPDATE 12.75) Skibid Toilet Morphs", // title
                "https://i.ytimg.com/vi/aTm259SBqK4/mqdefault.jpg", //thumbnails.medium
                "skibidi toilt Be a stranger or a good guy!!111111 First game with 1000 visits I really appreciated, I really do First game with 1M ..." //description
            )
        )
        add(
            Video(
                "대형 토일렛맨 전기톱 토일렛맨", //channel title
                "skibidi toilet 69 (paet 2)", // title
                "https://i.ytimg.com/vi/OpYLRi4ea8E/mqdefault.jpg", //thumbnails.medium
                "https://www.youtube.com/channel/UCVr33j5MQxxJqLAm_uKp3xA @DottyTV @share1004 @user-ot9zi4ov4u @AquaAnimation ..." //description
            )
        )
        add(
            Video(
                "GorTheMovieGod", //channel title
                "Gor's \"skibidi toilet 69 (full episode) by @DaFuqBoom\" REACTION", // title
                "https://i.ytimg.com/vi/MHIP7l9fsEE/mqdefault.jpg", //thumbnails.medium
                "skibidi toilet 69 (full episode) https://www.youtube.com/watch?v=wGc0jClpTPQ Follow me on Twitter: ..." //description
            )
        )
        add(
            Video(
                "Tasti", //channel title
                "Skibidi Characters React to SKIBIDI TOILET 69 (Full Episode) - Gacha React", // title
                "https://i.ytimg.com/vi/knpKalmGfck/mqdefault.jpg", //thumbnails.medium
                "in this video you will see new secret G-Man scenes, and infiltration squad is moving towards skibidi scientist, while outside ..." //description
            )
        )
        add(
            Video(
                "QUIZy House", //channel title
                "GUESS THE MONSTERS VOICE #96 DJ SKIBIDI TOILET GIANT SAINT TOILET Skibidi Toilet's Quiz", // title
                "https://i.ytimg.com/vi/_PtHg_AtG_M/mqdefault.jpg", //thumbnails.medium
                "GUESS THE MONSTERS VOICE #96 DJ SKIBIDI TOILET GIANT SAINT TOILET Skibidi Toilet's Quiz Thanks for watching the ..." //description
            )
        )
        add(
            Video(
                "CrewMario Japanese", //channel title
                "Skibidi Toilet Multiverse : Finger Heart on New Year's With Your Family - Skibidi Toilet Animation", // title
                "https://i.ytimg.com/vi/BDOEaTz944U/mqdefault.jpg", //thumbnails.medium
                "skidibitoiletmeme #tvman #tvwoman #cameraman #skibiditoilet #speakerman #dafugboom ----------- WATCH MORE Thank you ..." //description
            )
        )
        add(
            Video(
                "Ski Funny", //channel title
                "Skibidi Toilet Multiverse In Real Life: TV MAN Becomes The Bad Guy 10 | Skibidi Toilet in Real Life", // title
                "https://i.ytimg.com/vi/PNnhx3Tm65Q/mqdefault.jpg", //thumbnails.medium
                "Skibidi Toilet Multiverse In Real Life: TV MAN Becomes The Bad Guy 10 | Skibidi Toilet in Real Life: ..." //description
            )
        )
        add(
            Video(
                "VinsAlvin", //channel title
                "AKHIRNYA BISA LANJUT LAGI REACTION SEMUA FANMADE SKIBIDI TOILET PILIHAN KALIAN!!!", // title
                "https://i.ytimg.com/vi/SFbrGDG8vCU/mqdefault_live.jpg", //thumbnails.medium
                "BUAT KALIAN PECINTA SKIBIDI TOILET WAJIB NONTON !!! Donate Pake Suara Dan Video Sudah Bisa ..." //description
            )
        )
        add(
            Video(
                "Dasher", //channel title
                "SKIBIDI BLASTER (Sonic Wave x Skibidi Toilet) MASHUP", // title
                "https://i.ytimg.com/vi/kmO4V36R8jc/mqdefault.jpg", //thumbnails.medium
                "Skibidi Blaster Mashup DOWNLOAD LINK ..." //description
            )
        )
        add(
            Video(
                "Pixel Loquendo", //channel title
                "skibidi toilet 69-2 titanes de preparan para batalla final 🤯 #shorts #skibidi #skibiditoilet #meme", // title
                "https://i.ytimg.com/vi/jR-fcPzczt0/mqdefault.jpg", //thumbnails.medium
                "" //description
            )
        )
        add(
            Video(
                "Bùm", //channel title
                "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full", // title
                "https://i.ytimg.com/vi/dyuFvdd1Le4/mqdefault.jpg", //thumbnails.medium
                "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full --- Shopacc: https://bumroblox.net/k1 Kênh Phụ: ..." //description
            )
        )
    }

    fun get(index: Int): Video {
        return list[index]
    }

    fun add(video: Video) {
        list.add(video)
    }

    fun remove(video: Video) {
        list.remove(video)
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
    }

    fun find(channelTitle: String): Video? {
        return list.find { it.channelTitle == channelTitle }
    }
}