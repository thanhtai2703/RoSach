package com.kienvo.fonosclone

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kienvo.fonosclone.navigation.AppNavigation
import com.kienvo.fonosclone.ui.theme.FonosCloneTheme

@Composable
fun FonosApp() {
    val navController = rememberNavController()
    AppNavigation(navController = navController)
}

@Preview(showBackground = true)
@Composable
fun FonosAppPreview() {
    FonosCloneTheme {
        FonosApp()
    }
}
