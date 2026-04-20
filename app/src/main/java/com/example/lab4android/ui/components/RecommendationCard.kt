package com.example.lab4android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4android.R
import com.example.lab4android.data.PlaceCategory
import com.example.lab4android.data.Recommendation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationCard(
    recommendation: Recommendation,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Image(
                painter = painterResource(recommendation.image),
                contentDescription = stringResource(recommendation.name),
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = dimensionResource(R.dimen.padding_small))
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(recommendation.name),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = stringResource(recommendation.description),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        recommendation.category.icon,
                        contentDescription = null,
                        modifier = Modifier.size(dimensionResource(R.dimen.icon_small)),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacer_small)))
                    Text(
                        text = stringResource(recommendation.category.label),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationCardPreview() {
    RecommendationCard(
        recommendation = Recommendation(
            id = 0,
            name = R.string.name_test,
            address = R.string.address_test,
            description = R.string.description_test,
            image = R.drawable.ic_coffee,
            category = PlaceCategory.RESTAURANTS
        ),
        onClick = { },
        modifier = Modifier
    )
}