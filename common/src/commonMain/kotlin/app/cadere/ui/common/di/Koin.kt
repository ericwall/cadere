package app.cadere.ui.common.di

import app.cadere.ui.common.network.repository.DefaultLocationApi
import app.cadere.ui.common.network.service.LocationApi
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.logger.Logger
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(): KoinApplication {
    val koinApplication = startKoin {
        modules(
            platformModule,
            coreModule
        )
    }

    return koinApplication
}

private val coreModule = module {
    single<LocationApi> {
        DefaultLocationApi(
            get()
        )
    }
}

internal inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}

// Simple function to clean up the syntax a bit
fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }

expect val platformModule: Module