package Nots.NotsModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NotModel(



    @ColumnInfo(name = "notBaslik")
    var notBaslik: String,

    @ColumnInfo(name = "notIcerik")
    var notIcerik: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id=0
}