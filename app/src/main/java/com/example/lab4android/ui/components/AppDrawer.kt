package com.example.lab4android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab4android.R
import com.example.lab4android.data.PlaceCategory
import com.example.lab4android.navigation.ScreenDestinations

@Composable
fun AppDrawer(
    currentRoute: String?,
    onDestinationSelected: (String) -> Unit,
    onAboutClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        modifier = modifier.width(dimensionResource(R.dimen.drawer_width))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_large))
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small)))

            Text(
                text = stringResource(R.string.categories),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
            )

            PlaceCategory.entries.forEach { category ->
                NavigationDrawerItem(
                    label = { Text(stringResource(category.label)) },
                    icon = { Icon(category.icon, contentDescription = null) },
                    selected = currentRoute?.contains("category") == true,
                    onClick = {
                        onDestinationSelected(category.name)
                    },
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_xsmall))
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small)))

            NavigationDrawerItem(
                label = { Text(stringResource(R.string.about)) },
                icon = { Icon(ScreenDestinations.ABOUT.icon, contentDescription = null) },
                selected = currentRoute == ScreenDestinations.ABOUT.route,
                onClick = onAboutClick,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_xsmall))
            )

            NavigationDrawerItem(
                label = { Text(stringResource(R.string.settings)) },
                icon = { Icon(ScreenDestinations.SETTINGS.icon, contentDescription = null) },
                selected = currentRoute == ScreenDestinations.SETTINGS.route,
                onClick = onSettingsClick,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_xsmall))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppDrawerPreview() {
    AppDrawer(
        currentRoute = ScreenDestinations.ABOUT.route,
        onDestinationSelected = {  },
        onAboutClick = {  },
        onSettingsClick = {  },
        modifier = Modifier
    )
}