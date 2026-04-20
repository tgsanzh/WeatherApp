package com.tgsanzh.weatherapp.core.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.tgsanzh.weatherapp.R

sealed interface AppError {

    sealed interface Network : AppError {
        data object NoInternet : Network
        data object Unauthorized : Network
        data object NotFound : Network
        data object Server : Network
        data object Unknown : Network
    }

    sealed interface Location : AppError {
        data object NoPermission : Location
        data object Disabled : Location
        data object NotAvailable : Location
    }
}

@Composable
fun AppError.toMessage(): String {
    return when (this) {

        AppError.Network.NoInternet ->
            stringResource(R.string.error_no_internet)

        AppError.Network.Server ->
            stringResource(R.string.error_server)

        AppError.Network.Unauthorized ->
            stringResource(R.string.error_unauthorized)

        AppError.Network.NotFound ->
            stringResource(R.string.error_not_found)

        AppError.Location.NoPermission ->
            stringResource(R.string.error_location_permission)

        AppError.Location.Disabled ->
            stringResource(R.string.error_location_disabled)

        AppError.Location.NotAvailable -> {
            stringResource(R.string.error_location_not_available)
        }

        else ->
            stringResource(R.string.error_unknown)
    }
}