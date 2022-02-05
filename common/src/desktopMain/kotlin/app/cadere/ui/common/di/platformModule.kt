package app.cadere.ui.common.di

import io.ktor.client.engine.okhttp.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {

    single {
        OkHttp.create()
    }
}
