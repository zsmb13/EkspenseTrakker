import androidx.room.RoomDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(databaseBuilder: RoomDatabase.Builder<ExpenseDatabase>) {
    val dataModule = module {
        single<ExpenseDatabase> { databaseBuilder.getRoomDatabase() }
        single<ExpenseDao> { get<ExpenseDatabase>().expenseDao() }
    }
    
    startKoin {
        modules( dataModule )
    }
}
