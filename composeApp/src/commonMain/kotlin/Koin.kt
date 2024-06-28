import androidx.room.RoomDatabase
import data.ExpenseDao
import data.ExpenseDatabase
import data.getRoomDatabase
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ui.AddViewModel
import ui.HomeViewModel

fun initKoin(
    databaseBuilder: RoomDatabase.Builder<ExpenseDatabase>,
) {
    val dataModule = module {
        single<ExpenseDatabase> { databaseBuilder.getRoomDatabase() }
        single<ExpenseDao> { get<ExpenseDatabase>().expenseDao() }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { AddViewModel(get()) }
    }

    startKoin {
        modules(dataModule, viewModelModule)
    }
}
