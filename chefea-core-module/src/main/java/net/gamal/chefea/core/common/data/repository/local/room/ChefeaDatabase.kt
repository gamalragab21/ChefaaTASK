package net.gamal.chefea.core.common.data.repository.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import net.gamal.chefea.core.common.domain.repository.local.room.ComicsDao
import net.gamal.chefea.festures.commics.data.models.entity.ComicsItemEntity

@Database(entities = [ComicsItemEntity::class], version = 1)
internal abstract class ChefeaDatabase : RoomDatabase() {
    abstract fun comicsDao(): ComicsDao
}