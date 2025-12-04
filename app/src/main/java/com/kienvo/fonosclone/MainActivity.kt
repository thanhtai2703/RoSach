package com.kienvo.fonosclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kienvo.fonosclone.screens.FonosHomeScreen
import com.kienvo.fonosclone.ui.theme.FonosCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FonosCloneTheme {
                FonosHomeScreen()

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FonosCloneTheme {
        FonosHomeScreen()
    }
}