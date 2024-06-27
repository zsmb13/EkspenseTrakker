import androidx.room.Room
import androidx.room.RoomDatabase
import data.ExpenseDatabase
import java.io.File

fun createDatabaseBuilder(): RoomDatabase.Builder<ExpenseDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
    return Room.databaseBuilder<ExpenseDatabase>(name = dbFile.absolutePath)
}
