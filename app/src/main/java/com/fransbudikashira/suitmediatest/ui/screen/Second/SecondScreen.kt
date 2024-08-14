package com.fransbudikashira.suitmediatest.ui.screen.Second

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fransbudikashira.suitmediatest.R
import com.fransbudikashira.suitmediatest.ui.component.MyButton
import com.fransbudikashira.suitmediatest.ui.theme.SuitmediaTestTheme

@Composable
fun SecondScreen(
    name: String,
    onChooseUserClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedUser: String,
) {
    SecondContent(
        name = name,
        selectedUser = selectedUser,
        onChooseUserClick = onChooseUserClick,
        modifier = modifier
    )
}

@Composable
fun SecondContent(
    name: String,
    selectedUser: String,
    onChooseUserClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Text(
                text = selectedUser,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            MyButton(
                text = "Choose a user",
                onClick = onChooseUserClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    SuitmediaTestTheme {
        SecondScreen(
            name = "Frans",
            onChooseUserClick = {},
            selectedUser = stringResource(R.string.selected_user_name)
        )
    }
}