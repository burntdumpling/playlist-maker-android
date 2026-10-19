package com.example.playlist_maker_android_zalutskiialexander

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.ScreenBackground
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.ScreenBlue
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.SearchFieldBackground
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.SearchHintColor
import com.example.playlist_maker_android_zalutskiialexander.ui.theme.SearchInputStyle

@Composable
fun SearchScreen(onBackClick: () -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(ScreenBackground).safeDrawingPadding()
    ) {
        ScreenHeader(
            title = stringResource(R.string.search),
            onBackClick = onBackClick
        )
        SearchInput(
            query = query,
            onQueryChange = { query = it },
            onClearClick = { query = "" },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun SearchInput(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val queryDescription = stringResource(R.string.search_query)

    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth().semantics { contentDescription = queryDescription },
        singleLine = true,
        textStyle = SearchInputStyle,
        cursorBrush = SolidColor(ScreenBlue),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {}),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 36.dp)
                    .background(SearchFieldBackground, RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = stringResource(R.string.search_action),
                        tint = SearchHintColor,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Box(
                    modifier = Modifier.weight(1f).padding(vertical = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search),
                            style = SearchInputStyle,
                            color = SearchHintColor
                        )
                    }
                    innerTextField()
                }
                if (query.isNotEmpty()) {
                    IconButton(onClick = onClearClick, modifier = Modifier.size(36.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clear),
                            contentDescription = stringResource(R.string.clear_search),
                            tint = SearchHintColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else {
                    Box(modifier = Modifier.size(12.dp))
                }
            }
        }
    )
}
