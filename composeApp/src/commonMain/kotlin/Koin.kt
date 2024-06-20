import androidx.room.RoomDatabase
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(databaseBuilder: RoomDatabase.Builder<ExpenseDatabase>) {
    val dataModule = module {
        single<ExpenseDatabase> { databaseBuilder.getRoomDatabase() }
        single<ExpenseDao> { get<ExpenseDatabase>().expenseDao() }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
    }

    startKoin {
        modules(dataModule, viewModelModule)
    }
}
