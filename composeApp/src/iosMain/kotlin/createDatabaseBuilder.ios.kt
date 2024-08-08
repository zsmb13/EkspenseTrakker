import androidx.room.Room
import androidx.room.RoomDatabase
import data.ExpenseDatabase
import platform.Foundation.NSHomeDirectory

fun createDatabaseBuilder(): RoomDatabase.Builder<ExpenseDatabase> {
    val dbFilePath = NSHomeDirectory() + "/my_room.db"
    return Room.databaseBuilder<ExpenseDatabase>(
        name = dbFilePath,
    )
}
