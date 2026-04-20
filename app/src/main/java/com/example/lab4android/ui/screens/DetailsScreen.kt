package com.example.lab4android.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab4android.R
import com.example.lab4android.ui.components.DetailsCard
import com.example.lab4android.viewmodel.CityAppViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    recommendationId: Int,
    viewModel: CityAppViewModel
) {
    val recommendation = viewModel.getRecommendationById(recommendationId)

    if (recommendation != null) {
        DetailsCard(
            modifier = modifier,
            recommendation = recommendation
        )
    } else {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.recommendation_not_found))
        }
    }
}

@Preview(showBackground = true, locale = "ru")
@PreviewScreenSizes
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        modifier = Modifier,
        recommendationId = 0,
        viewModel = viewModel()
    )
}