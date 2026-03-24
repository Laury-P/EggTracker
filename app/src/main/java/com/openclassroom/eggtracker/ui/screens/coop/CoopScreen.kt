package com.openclassroom.eggtracker.ui.screens.coop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.openclassroom.eggtracker.R
import com.openclassroom.eggtracker.domain.Coop
import com.openclassroom.eggtracker.ui.screens.NavigationRoutes

@Composable
fun CoopScreen(
    viewModel: CoopViewModel = hiltViewModel(), navController: NavController
) {
    // TODO Limitation des poulailler en fonction du statut premium ou non
    val coops = viewModel.coops.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(NavigationRoutes.AddEditCoopScreenNav.route) }) {
                Text(text = "+")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())) {
            CoopContent(coops.value)
        }
    }
}

@Composable
fun CoopContent(coops: List<Coop>) {
    if (coops.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize().padding(32.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = stringResource(R.string.no_coops),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = stringResource(R.string.nav_coops),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

            }
            coops.forEach { coop ->
                item {
                    CoopDisplay(coop)
                }
            }
        }
    }
}

@Composable
fun CoopDisplay(coop: Coop, editCoopButton: () -> Unit = {}, deleteCoopButton: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = coop.name, style = MaterialTheme.typography.titleSmall)
            Text(text = "${stringResource(coop.type.labelResId)} - ${coop.birdCount}")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.End),
        ) {
            Button(onClick = { editCoopButton() }) {
                Text(text = stringResource(R.string.edit_button))
                // TODO Modifier le poulailler
            }

            Button(onClick = { deleteCoopButton() }) {
                Text(text = stringResource(R.string.delete_button))
                // TODO Supprimer le poulailler
            }
        }
    }
}


@Preview
@Composable
fun CoopContentPreview() {
    CoopContent(emptyList())
}




