import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<ExpenseDatabase> {
    val dbFilePath = NSHomeDirectory() + "/my_room.db"
    return Room.databaseBuilder<ExpenseDatabase>(
        name = dbFilePath,
        factory = { ExpenseDatabase::class.instantiateImpl() }
    )
}
