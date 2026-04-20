package com.example.lab4android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.lab4android.R
import com.example.lab4android.data.PlaceCategory
import com.example.lab4android.data.Recommendation
import com.example.lab4android.ui.theme.Typography

@Composable
fun DetailsCard (
    modifier: Modifier = Modifier,
    recommendation: Recommendation
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(recommendation.image),
            contentDescription = stringResource(recommendation.name),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_small)))
        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Text(
                    text = stringResource(recommendation.name),
                    style = Typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_small)))
                Text(
                    text = stringResource(recommendation.description)
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer_small)))
                Text(
                    text = stringResource(R.string.address) + ": "+ stringResource(R.string.address_test)
                )
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun DetailsCardPreview() {
    DetailsCard(
        modifier = Modifier,
        recommendation = Recommendation(
            id = 0,
            name = R.string.name_test,
            address = R.string.address_test,
            description = R.string.description_test,
            image = R.drawable.ic_coffee,
            category = PlaceCategory.RESTAURANTS
        )
    )
}