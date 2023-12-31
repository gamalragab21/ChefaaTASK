package net.gamal.chefea.core.common.domain.repository.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.gamal.chefea.festures.commics.data.models.entity.ComicsItemEntity

@Dao
internal interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllComics(comicsItems: List<ComicsItemEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertComic(comicsItem: ComicsItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateComic(comicsItem: ComicsItemEntity)

    @Query("SELECT * FROM comics")
    suspend fun getAllComics(): List<ComicsItemEntity>

    @Query("SELECT * FROM comics where id=:id")
    suspend fun getComicById(id: Int): ComicsItemEntity
}