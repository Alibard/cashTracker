package cashtracker.alibard.tm.com.db.entities

import android.arch.persistence.room.*


@Entity(tableName = "settings")
data class StructureSettings(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                             var pushState: Boolean = true)


@Entity(tableName = "spending_type")
data class SpendingType(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                        var name: String,
                        var image: Int,
                        @ColumnInfo(name = "settings_id") var settingsId: Long)


class FullSettings {
    @Embedded
    lateinit var settings: StructureSettings
    @Relation(parentColumn = "id",
            entityColumn = "settings_id")
    lateinit var typeList: List<SpendingType>
}
