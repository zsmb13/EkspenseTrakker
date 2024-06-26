import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import data.ExpenseDatabase

fun createDatabaseBuilder(ctx: Context): RoomDatabase.Builder<ExpenseDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<ExpenseDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
