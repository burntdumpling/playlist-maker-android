package com.example.playlist_maker_android_zalutskiialexander.ui.setting

import com.example.playlist_maker_android_zalutskiialexander.R
import com.example.playlist_maker_android_zalutskiialexander.ui.ScreenHeader

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.ScreenBackground
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.SettingsIconColor
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.SettingsRowStyle
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.SettingsSwitchThumb
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.SettingsSwitchTrack
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.Transparent

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val shareText = stringResource(R.string.share_message)
    val shareTitle = stringResource(R.string.share_app)
    val recipient = stringResource(R.string.developer_email)
    val subject = stringResource(R.string.developer_email_subject)
    val body = stringResource(R.string.developer_email_body)
    val offerUrl = stringResource(R.string.practicum_offer_url)
    val noApplication = stringResource(R.string.no_application)

    fun openExternal(intent: Intent) {
        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, noApplication, Toast.LENGTH_SHORT).show()
        }
    }

    BackHandler(onBack = navigateBack)
    Column(
        modifier = modifier.fillMaxSize().background(ScreenBackground).safeDrawingPadding()
    ) {
        ScreenHeader(
            title = stringResource(R.string.settings),
            onBackClick = navigateBack
        )
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(top = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().heightIn(min = 61.dp).padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.dark_theme),
                    style = SettingsRowStyle,
                    modifier = Modifier.weight(1f)
                )
                Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.CenterEnd) {
                    Switch(
                        checked = false,
                        onCheckedChange = null,
                        enabled = false,
                        modifier = Modifier.scale(0.7f),
                        colors = SwitchDefaults.colors(
                            disabledUncheckedThumbColor = SettingsSwitchThumb,
                            disabledUncheckedTrackColor = SettingsSwitchTrack,
                            disabledUncheckedBorderColor = Transparent
                        )
                    )
                }
            }
            SettingsAction(
                title = stringResource(R.string.share_app),
                icon = R.drawable.ic_share,
                onClick = {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }
                    openExternal(Intent.createChooser(sendIntent, shareTitle))
                }
            )
            SettingsAction(
                title = stringResource(R.string.contact_developers),
                icon = R.drawable.ic_support,
                onClick = {
                    val mailUri = Uri.parse(
                        "mailto:" + Uri.encode(recipient) +
                            "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body)
                    )
                    openExternal(Intent(Intent.ACTION_SENDTO, mailUri).apply {
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
                        putExtra(Intent.EXTRA_SUBJECT, subject)
                        putExtra(Intent.EXTRA_TEXT, body)
                    })
                }
            )
            SettingsAction(
                title = stringResource(R.string.user_agreement),
                icon = R.drawable.ic_chevron_right,
                onClick = { openExternal(Intent(Intent.ACTION_VIEW, Uri.parse(offerUrl))) }
            )
        }
    }
}

@Composable
private fun SettingsAction(title: String, @DrawableRes icon: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 61.dp)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = SettingsRowStyle, modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = SettingsIconColor,
            modifier = Modifier.padding(start = 8.dp).size(24.dp)
        )
    }
}
