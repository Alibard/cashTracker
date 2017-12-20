package cashtracker.alibard.tm.com.repository.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class SpendingTable @JvmOverloads constructor(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")var id : Int= 0,
                                                   @ColumnInfo(name = "description") var description: String = "")