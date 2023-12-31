package net.gamal.chefea.festures.commics.data.models.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics")
internal data class ComicsItemEntity(
    @PrimaryKey var id: Int,
    @Embedded(prefix = "thumbnail_") var thumbnail: ThumbnailEntity,
    var title: String
)