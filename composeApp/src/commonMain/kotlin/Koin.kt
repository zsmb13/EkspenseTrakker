import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(
    databaseBuilder: RoomDatabase.Builder<ExpenseDatabase>,
    dataStore: DataStore<Preferences>,
) {
    val dataModule = module {
        single<ExpenseDatabase> { databaseBuilder.getRoomDatabase() }
        single<ExpenseDao> { get<ExpenseDatabase>().expenseDao() }

        single<DataStore<Preferences>> { dataStore }
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { AddViewModel(get(), get()) }
    }

    startKoin {
        modules(dataModule, viewModelModule)
    }
}
