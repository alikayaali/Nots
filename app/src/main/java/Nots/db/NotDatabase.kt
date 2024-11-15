package Nots.db

import Nots.NotsModel.NotModel
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [NotModel::class], version = 2)
abstract class NotDatabase : RoomDatabase() {

    abstract fun notsDao(): NotsDao


}
