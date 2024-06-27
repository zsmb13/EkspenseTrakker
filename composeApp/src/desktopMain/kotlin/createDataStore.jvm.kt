import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.createDataStore
import data.dataStoreFileName
import java.io.File

fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        File(System.getProperty("java.io.tmpdir"), dataStoreFileName).absolutePath
    }
)
