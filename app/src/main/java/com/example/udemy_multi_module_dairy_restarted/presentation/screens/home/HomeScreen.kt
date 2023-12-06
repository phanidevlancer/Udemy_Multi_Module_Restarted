package com.example.udemy_multi_module_dairy_restarted.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.udemy_multi_module_dairy_restarted.R
import com.example.udemy_multi_module_dairy_restarted.data.repository.Diaries
import com.example.udemy_multi_module_dairy_restarted.model.RequestState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    diaries: Diaries,
    onMenuClicked: () -> Unit,
    navigateToWrite: () -> Unit,
    drawerState: DrawerState,
    onSignOutClick: () -> Unit
) {
    NavigationDrawer(
        drawerState = drawerState,
        onSignOutClick = onSignOutClick
    ) {
        Scaffold(topBar = {
            HomeTopBar(onMenuClicked)
        }, floatingActionButton = {
            FloatingActionButton(onClick = navigateToWrite) {
                Icon(imageVector = Icons.Default.Create, contentDescription = "Write Icon")
            }
        }, content = {
            when (diaries) {
                is RequestState.Success -> {
                    HomeContent(
                        paddingValues = it,
                        dairyNotes = diaries.data,
                        onClick = {})
                }

                is RequestState.Error -> {
                    EmptyPage(
                        title = "Error",
                        subtitle = "${diaries.error.message}"
                    )
                }

                is RequestState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    EmptyPage()
                }
            }
        })
    }
}

@Composable
fun NavigationDrawer(
    drawerState: DrawerState, onSignOutClick: () -> Unit, content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Drawer Logo"
                    )
                }


                NavigationDrawerItem(
                    label = {
                        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.google_logo),
                                contentDescription = "Google Logo",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(
                                modifier = Modifier.width(12.dp)
                            )
                            Text(
                                text = "Sign Out",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }, selected = false, onClick = onSignOutClick
                )
            }
        },
        content = content
    )

}


