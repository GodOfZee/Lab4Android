package com.example.lab4android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab4android.R
import com.example.lab4android.data.PlaceCategory
import com.example.lab4android.ui.components.CategoryButton
import com.example.lab4android.ui.components.RecommendationCard
import com.example.lab4android.viewmodel.CityAppViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: CityAppViewModel,
    onCategoryClick: (String) -> Unit,
    onRecommendationClick: (Int) -> Unit
) {
    val recommendations by viewModel.recommendations.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium))
    ) {
        item {
            Text(
                text = stringResource(R.string.welcome_to_izhevsk),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
            )
        }

        item {
            Text(
                text = stringResource(R.string.categories) + ":",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
            )
        }

        items(PlaceCategory.entries) { category ->
            CategoryButton(
                category = category,
                onClick = { onCategoryClick(category.name) }
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_small)))
        }

        item {
            Text(
                text = stringResource(R.string.popular_places),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
            )
        }

        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_xlarge)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        } else {
            val popularRecommendations = recommendations.take(3)
            items(popularRecommendations) { recommendation ->
                RecommendationCard(
                    recommendation = recommendation,
                    onClick = { onRecommendationClick(recommendation.id) }
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_small)))
            }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@PreviewScreenSizes
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        modifier = Modifier,
        viewModel = viewModel(),
        onCategoryClick = {},
        onRecommendationClick = {}
    )
}