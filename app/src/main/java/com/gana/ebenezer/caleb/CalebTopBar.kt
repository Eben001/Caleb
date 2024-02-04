package com.gana.ebenezer.caleb

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.gana.ebenezer.caleb.ui.theme.md_theme_light_onPrimary
import com.gana.ebenezer.caleb.ui.theme.md_theme_light_primary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalebTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Caleb", color = md_theme_light_primary, fontWeight = FontWeight.Bold)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = md_theme_light_onPrimary
        )
    )

}

@Preview
@Composable
private fun RachaelTopBarPreview() {
    CalebTopBar()
}


