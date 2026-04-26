package com.tgsanzh.weatherapp.features.home.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tgsanzh.weatherapp.R
import com.tgsanzh.weatherapp.core.error.toMessage
import com.tgsanzh.weatherapp.core.ui.components.PrimaryCard
import com.tgsanzh.weatherapp.core.ui.components.PrimaryInformationCard
import com.tgsanzh.weatherapp.core.ui.theme.primaryColors
import com.tgsanzh.weatherapp.core.ui.theme.primaryTypography
import com.tgsanzh.weatherapp.features.home.presentation.mappers.WeatherBackgroundMapper
import java.util.Locale

@Composable
fun HomeScreen(state: HomeUiState, onEvent: (HomeEvent) -> (Unit)) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                onEvent(HomeEvent.GetData)
            }
            else {
                onEvent(HomeEvent.NoGpsPermission)
            }
        }
    )
    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = Modifier.background(
            color = primaryColors.backgroundGradientTop
        )
    ) {

        if(state.weather != null) {
            Image(
                painter = painterResource(
                    WeatherBackgroundMapper.getBackgroundRes(
                        pop = state.weather.today.pop,
                        hour = state.weather.hourly[0].hour.toInt()
                    )
                ),
                contentDescription = "Background",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }

        PullToRefreshBox(
            isRefreshing = state.isLoading,
            onRefresh = { onEvent(HomeEvent.GetData) },
            state = refreshState
        ) {
            LazyColumn (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
            ) {
                if (!state.isLoading) {
                    if (state.weather != null) {
                        item {
                            Header(
                                weather = state.weather
                            )
                        }
                        item {
                            Spacer(
                                Modifier.height(80.dp)
                            )
                            HourlyCard(
                                hours = state.weather.hourly,
                                todaySummary = state.weather.today.summary
                            )
                        }
                        item {
                            ForecastEightDays(
                                days = state.weather.daily
                            )
                        }
                        item {
                            AverageAndFeelsLikeSection(
                                today = state.weather.today
                            )
                        }
                        item {
                            WindSection(
                                today = state.weather.today
                            )
                        }
                        item {
                            UvAndTimeSection(
                                today = state.weather.today
                            )
                        }
                        item {
                            HumidityAndPressureSection(
                                today = state.weather.today
                            )
                        }
                    }
                    else {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = state.error?.toMessage() ?: "Загрузка данных невозможна",
                                    style = primaryTypography.city,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth().padding(top = 400.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Header(weather: WeatherUi) {
    Spacer(
        Modifier.height(80.dp)
    )

    Text(
        text = weather.timezone.split("/")[1],
        style = primaryTypography.city,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth(),
    )

    Text(
        text = stringResource(R.string.temperature, weather.today.temperature.toString()),
        style = primaryTypography.temperature,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp),
    )

    Text(
        text = weather.today.description.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        },
        style = primaryTypography.description,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
    )

    Text(
        text = stringResource(R.string.temp_max_min,
            weather.today.tempMax.toString(),
                weather.today.tempMin.toString()
        ),
        style = primaryTypography.boundaries,
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun HourlyCard(hours: List<HourlyUi>, todaySummary: String) {
    val listState = rememberLazyListState()


    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = primaryColors.card,
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = todaySummary,
                style = primaryTypography.body
            )
            Spacer(
                Modifier.height(16.dp)
            )
            HorizontalDivider(
                color = primaryColors.textPrimary.copy(alpha = 0.1f)
            )
            Spacer(
                Modifier.height(16.dp)
            )

            LazyRow(
                state = listState,
                flingBehavior = rememberSnapFlingBehavior(
                    lazyListState = listState,
                    snapPosition = SnapPosition.Center
                ),
                contentPadding = PaddingValues(horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(hours) { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(40.dp)
                    ) {
                        Text(
                            text = item.hour,
                            style = primaryTypography.body,
                            maxLines = 1,
                        )
                        Icon(
                            painter = painterResource(item.iconRes),
                            tint = primaryColors.textPrimary,
                            contentDescription = "Icon",
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Text(
                            text = stringResource(R.string.temperature, item.temp.toString()),
                            style = primaryTypography.body,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ForecastEightDays(days: List<DailyUi>) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = primaryColors.card,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = "Calendar Icon",
                    tint = primaryColors.textTertiary,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.forecast_8_days),
                    style = primaryTypography.bodyTransparent,
                )
            }
            HorizontalDivider(
                color = primaryColors.textPrimary.copy(alpha = 0.1f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                for (day in 0..<days.size) {
                    when (day) {
                        0 -> {
                            DailyTemps(days[0])
                            HorizontalDivider(
                                color = primaryColors.textPrimary.copy(alpha = 0.1f),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        days.size - 1 -> {
                            DailyTemps(days[day], days[day].dayOfWeek)
                        }
                        else -> {
                            DailyTemps(days[day], days[day].dayOfWeek)
                            HorizontalDivider(
                                color = primaryColors.textPrimary.copy(alpha = 0.1f),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DailyTemps(day: DailyUi, weekDay: String = stringResource(R.string.today)) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = weekDay,
            style = primaryTypography.body,
            modifier = Modifier.weight(1f)

        )
        Icon(
            painter = painterResource(day.iconRes),
            contentDescription = "Icon",
            tint = primaryColors.textPrimary,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(R.string.temperature, day.tempMin),
            textAlign = TextAlign.Center,
            style = primaryTypography.body,
            modifier = Modifier.weight(1f)

        )
        Text(
            text = stringResource(R.string.temperature, day.tempMax),
            textAlign = TextAlign.Center,
            style = primaryTypography.body,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AverageAndFeelsLikeSection(today: DailyUi) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        PrimaryInformationCard(
            headerIcon = painterResource(R.drawable.ic_graph),
            headerText = stringResource(R.string.average),
            valueText = stringResource(R.string.average_temp, (today.tempMax - today.tempMin).toString()),
            modifier = Modifier.weight(1f),
        ) {
                Text(
                    text = stringResource(R.string.average_desc),
                    style = primaryTypography.body,
                    modifier = Modifier.padding(bottom = 8.dp, top = 4.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.today),
                        style = primaryTypography.caption
                    )
                    Text(
                        text = stringResource(R.string.temp_max, today.tempMin.toString()),
                        style = primaryTypography.caption
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.average_max),
                        style = primaryTypography.caption
                    )
                    Text(
                        text = stringResource(R.string.temp_max, today.tempMin.toString()),
                        style = primaryTypography.caption
                    )
                }
        }

        PrimaryInformationCard(
            headerIcon = painterResource(R.drawable.ic_thermometer),
            headerText = stringResource(R.string.feels_like),
            valueText = stringResource(
                R.string.temperature,
                today.feelsLike.toString()
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text =  if (today.temperature <= today.feelsLike)
                    stringResource(R.string.feels_like_higher)
                else
                    stringResource(R.string.feels_like_lower),
                style = primaryTypography.bodyNormal,
                modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
            )
        }
    }
}

@Composable
private fun WindSection(today: DailyUi) {
    PrimaryCard (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_wind),
                    contentDescription = "Wind Icon",
                    tint = primaryColors.textTertiary,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(R.string.wind_caps),
                    style = primaryTypography.bodyTransparent,
                )
            }
            WindRow(
                stringResource(R.string.wind),
                stringResource(
                    R.string.wind_speed,
                    today.windSpeed.toString()
                )

            )
            HorizontalDivider(
                color = primaryColors.textPrimary.copy(alpha = 0.1f),
                modifier = Modifier.padding(vertical = 4.dp)
            )
            WindRow(
                stringResource(R.string.wind_gusts),
                stringResource(
                    R.string.wind_speed,
                    today.windGust.toString()
                )

            )
            HorizontalDivider(
                color = primaryColors.textPrimary.copy(alpha = 0.1f),
                modifier = Modifier.padding(vertical = 4.dp)
            )
            WindRow(
                stringResource(R.string.wind_direction),
                today.windDeg.toString()
            )
        }
    }
}

@Composable
private fun WindRow(name: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = name,
            style = primaryTypography.body,
        )
        Text(
            text = value,
            style = primaryTypography.bodyTransparent,
        )
    }
}

@Composable
private fun UvAndTimeSection(today: DailyUi) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        PrimaryInformationCard(
            headerIcon = painterResource(R.drawable.ic_sun),
            headerText = stringResource(R.string.uv_index),
            valueText = today.uvi.toString(),
            modifier = Modifier.weight(1f)
        )

        PrimaryInformationCard(
            headerIcon = painterResource(R.drawable.ic_sunset),
            headerText = stringResource(R.string.sunset),
            valueText = today.sunset,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = stringResource(R.string.sunrise, today.sunrise),
                style = primaryTypography.bodyNormal,
                modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
            )
        }
    }
}

@Composable
private fun HumidityAndPressureSection(today: DailyUi) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        PrimaryInformationCard(
            headerIcon = painterResource(R.drawable.ic_humidity),
            headerText = stringResource(R.string.humidity),
            valueText = stringResource(R.string.humidity_value, today.humidity.toString()),
            modifier = Modifier.weight(1f),
        )

        PrimaryInformationCard(
            headerIcon = painterResource(R.drawable.ic_squeeze),
            headerText = stringResource(R.string.pressure),
            valueText = stringResource(R.string.pressure_value, today.pressure.toString()),
            modifier = Modifier.weight(1f),
        )
    }
}
