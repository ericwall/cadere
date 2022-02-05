import app.cadere.ui.common.App
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.cadere.ui.common.di.initKoin
import app.cadere.ui.common.network.repository.LocationRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

fun main() = application {

    initKoin()

    Window(onCloseRequest = ::exitApplication) {

        MaterialTheme {
            val locRepo = LocationRepository()

            CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
                observeLocations(locRepo)
            }
            App()
        }
    }
}

@OptIn(FlowPreview::class)
private suspend fun observeLocations(locationRepository: LocationRepository) {
    coroutineScope {
        this.launch {
            flowOf(
                locationRepository.getLocations()
            ).flattenMerge().collect {
                println("Hey this probably shouldnt be working bu hey ${it.size}")
            }
        }
    }
}