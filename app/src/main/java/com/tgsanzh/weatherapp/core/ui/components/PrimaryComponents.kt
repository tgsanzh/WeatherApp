package com.tgsanzh.weatherapp.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.tgsanzh.weatherapp.core.ui.extentions.noRippleClickable
import com.tgsanzh.weatherapp.core.ui.theme.primaryColors
import com.tgsanzh.weatherapp.core.ui.theme.primaryTypography

@Composable
fun PrimaryCard(modifier: Modifier = Modifier, content: @Composable () -> (Unit)) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = primaryColors.card,
        ),
        modifier = modifier
            .heightIn(min = 180.dp)
    ) {
        content()
    }
}

@Composable
fun PrimaryInformationCard(
    headerIcon: Painter,
    headerText: String,
    valueText: String,
    modifier: Modifier = Modifier,
    additionalContent: @Composable () -> (Unit) = {}
) {
    PrimaryCard(
        modifier = modifier
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
                    painter = headerIcon,
                    contentDescription = headerText,
                    tint = primaryColors.textTertiary,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = headerText,
                    style = primaryTypography.bodyTransparent,
                )
            }
            Text(
                text = valueText,
                style = primaryTypography.city,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            additionalContent()
        }
    }
}

@Composable
fun BasicBottomSheetItem(
    trailingIcon: Painter,
    text: String,
    onClick: () -> (Unit)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable {
                onClick()
            }
    ) {
        Icon(
            painter = trailingIcon,
            contentDescription = text,
            tint = primaryColors.textPrimary,
            modifier = Modifier.padding(end = 12.dp)
        )
        Text(
            text = text,
            style = primaryTypography.bottomSheetText
        )
    }
}