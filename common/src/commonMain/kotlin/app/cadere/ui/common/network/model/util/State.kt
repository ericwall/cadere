package app.cadere.ui.common.network.model.util

data class State<out T>(
    val data: T? = null,
    val exception: String? = null,
    val empty: Boolean = false,
    val loading: Boolean = false
)