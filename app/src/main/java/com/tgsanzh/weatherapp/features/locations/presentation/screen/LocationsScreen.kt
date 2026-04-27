package com.tgsanzh.weatherapp.features.locations.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tgsanzh.weatherapp.R
import com.tgsanzh.weatherapp.core.ui.components.BasicBottomSheetItem
import com.tgsanzh.weatherapp.core.ui.extentions.noRippleClickable
import com.tgsanzh.weatherapp.core.ui.theme.primaryColors
import com.tgsanzh.weatherapp.core.ui.theme.primaryTypography
import com.tgsanzh.weatherapp.features.locations.presentation.event.LocationsEvent
import com.tgsanzh.weatherapp.features.locations.presentation.state.Location
import com.tgsanzh.weatherapp.features.locations.presentation.state.LocationsUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    state: LocationsUiState,
    onEvent: (LocationsEvent) -> (Unit)
) {
    val sheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = primaryColors.backgroundBlue),
    ) {
        if (state.showSheet) {
            ModalBottomSheet(
                onDismissRequest = { onEvent(LocationsEvent.BottomSheetVisibilityChanged(false)) },
                sheetState = sheetState,
                containerColor = primaryColors.bottomSheet,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(12.dp)
                ) {
                    BasicBottomSheetItem(
                        trailingIcon = painterResource(R.drawable.ic_pencil),
                        text = stringResource(R.string.edit_list),
                        onClick = {  },
                    )
                    BasicBottomSheetItem(
                        trailingIcon = painterResource(R.drawable.ic_bell),
                        text = stringResource(R.string.notifications),
                        onClick = {  },
                    )
                    HorizontalDivider(
                        color = primaryColors.textTertiary,
                    )
                    BasicBottomSheetItem(
                        trailingIcon = painterResource(R.drawable.ic_celsius),
                        text = stringResource(R.string.celsius),
                        onClick = {  },
                    )
                    BasicBottomSheetItem(
                        trailingIcon = painterResource(R.drawable.ic_fahrenheit),
                        text = stringResource(R.string.fahrenheit),
                        onClick = {  },
                    )
                    HorizontalDivider(
                        color = primaryColors.textTertiary,
                    )
                    BasicBottomSheetItem(
                        trailingIcon = painterResource(R.drawable.ic_columns),
                        text = stringResource(R.string.units),
                        onClick = {  },
                    )
                    HorizontalDivider(
                        color = primaryColors.textTertiary,
                    )
                    BasicBottomSheetItem(
                        trailingIcon = painterResource(R.drawable.ic_message),
                        text = stringResource(R.string.report_problem),
                        onClick = {  },
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 48.dp, bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Header {
                onEvent(LocationsEvent.BottomSheetVisibilityChanged(true))
            }
            LocationsList(state.locations)
        }

        BottomSearchMenu(state.searchText) {
            onEvent(LocationsEvent.OnSearchTextChanged(it))
        }
    }
}

@Composable
private fun Header(
    makeBottomSheetVisible: () -> (Unit)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.weather_header),
            style = primaryTypography.header,
        )
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(color = primaryColors.accentBlueSecondary, shape = CircleShape)
                .border(width = 1.dp, color = primaryColors.whiteBorder, shape = CircleShape)
                .noRippleClickable {
                    makeBottomSheetVisible()
                }
        ) {
            Icon(
                painterResource(R.drawable.ic_more_dots),
                contentDescription = "Dots",
                tint = primaryColors.textPrimary,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun LocationsList(locations: List<Location>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(
            items = locations,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.bg_clear_day),
                    contentDescription = "Background",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.matchParentSize()
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Column {
                        Text(
                            text = it.cityName,
                            style = primaryTypography.citySmall,
                        )
                        Text(
                            text = it.localTime,
                            style = primaryTypography.descriptionSmall,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )
                        Text(
                            text = it.description,
                            style = primaryTypography.descriptionSmall,
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = stringResource(R.string.temperature, it.temperature.toString()),
                            style = primaryTypography.temperatureSmall,
                            textAlign = TextAlign.End,
                        )
                        Text(
                            text = stringResource(
                                R.string.temp_max_min,
                                it.maxTemp,
                                it.minTemp
                            ),
                            style = primaryTypography.boundariesSmall,
                            modifier = Modifier.padding(top = 4.dp),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BoxScope.BottomSearchMenu(
    searchText: String,
    onSearchTextChanged: (String) -> (Unit)
) {
    TextField(
        value = searchText,
        onValueChange = { onSearchTextChanged(it) },
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = primaryColors.accentBlueSecondary,
            unfocusedContainerColor = primaryColors.accentBlueSecondary,
        ),
        shape = RoundedCornerShape(64.dp),
        textStyle = primaryTypography.body,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search",
                tint = primaryColors.textPrimary,
                modifier = Modifier.size(24.dp).padding(start = 4.dp)
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_microphone),
                contentDescription = "Microphone",
                tint = primaryColors.textPrimary,
                modifier = Modifier.size(24.dp).padding(end = 4.dp)
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_hint),
                style = primaryTypography.bodyTransparent,
            )
        },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxWidth()
            .height(52.dp)
            .align (Alignment.BottomCenter)
            .border(width = 1.dp, shape = RoundedCornerShape(64.dp), color = primaryColors.whiteBorder)
    )
}


