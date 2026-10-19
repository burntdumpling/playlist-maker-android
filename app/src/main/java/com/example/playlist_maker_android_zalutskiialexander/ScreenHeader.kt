package com.example.playlist_maker_android_zalutskiialexander

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.MainTextColor
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.MenuFontFamily

@Composable
fun ScreenHeader(
    title: String,
    onBackClick: () -> Unit,
    showBackButton: Boolean = true
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showBackButton) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.back),
                    tint = MainTextColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Text(
            text = title,
            modifier = Modifier.padding(start = 12.dp),
            color = MainTextColor,
            fontFamily = MenuFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp
        )
    }
}
