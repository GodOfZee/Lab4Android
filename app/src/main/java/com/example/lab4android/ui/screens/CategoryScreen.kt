package com.example.lab4android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.lab4android.ui.components.RecommendationCard
import com.example.lab4android.viewmodel.CityAppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    category: String,
    viewModel: CityAppViewModel,
    onRecommendationClick: (Int) -> Unit
) {
    val recommendations by viewModel.recommendations.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val categoryEnum = try {
        PlaceCategory.valueOf(category)
    } catch (e: IllegalArgumentException) {
        PlaceCategory.COFFEE
    }

    val filteredRecommendations = recommendations.filter { it.category == categoryEnum }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "${stringResource(R.string.categories)}: ${stringResource(categoryEnum.label)}",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (filteredRecommendations.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.no_recommendations))
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.padding_medium))
            ) {
                items(filteredRecommendations) { recommendation ->
                    RecommendationCard(
                        recommendation = recommendation,
                        onClick = { onRecommendationClick(recommendation.id) }
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_small)))
                }
            }
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@PreviewScreenSizes
@Composable
fun CategoryScreenPreview() {
    CategoryScreen(
        modifier = Modifier,
        category = "coffee",
        viewModel = viewModel(),
        onRecommendationClick = {  }
    )
}