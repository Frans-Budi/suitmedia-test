package com.fransbudikashira.suitmediatest.ui.screen.First

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fransbudikashira.suitmediatest.R
import com.fransbudikashira.suitmediatest.ui.component.MyButton
import com.fransbudikashira.suitmediatest.ui.component.MyTextField
import com.fransbudikashira.suitmediatest.ui.theme.SuitmediaTestTheme

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    onNextClick: (String) -> Unit,
    viewModel: FirstViewModel = viewModel()
) {
    val inputName by viewModel.name
    val inputPalindrome by viewModel.palindrome
    val showDialog by viewModel.showDialog
    val isPalindrome by viewModel.isPalindrome

    val palindromeResult =
        if (isPalindrome) stringResource(R.string.palindrome_result_true)
        else stringResource(R.string.palindrome_result_false)

    FirstContent(
        inputName = inputName,
        inputPalindrome = inputPalindrome,
        onNameChange = viewModel::onNameChange,
        onPalindromeChange = viewModel::onPalindromeChange,
        onCheckClick = { viewModel.checkPalindrome(inputPalindrome) },
        onNextClick = onNextClick,
        showDialog = showDialog,
        onDismissRequest = viewModel::dismissDialog,
        onConfirmClick = viewModel::dismissDialog,
        palindromeResult = palindromeResult,
        modifier = modifier,
    )
}

@Composable
fun FirstContent(
    inputName: String,
    inputPalindrome: String,
    onNameChange: (String) -> Unit,
    onPalindromeChange: (String) -> Unit,
    onCheckClick: () -> Unit,
    onNextClick: (String) -> Unit,
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    palindromeResult: String,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Image(
                painter = painterResource(R.drawable.frans),
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = modifier.size(30.dp))
            // Name
            MyTextField(
                value = inputName,
                onValueChange = onNameChange,
                labelText = stringResource(R.string.name),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.size(10.dp))
            // Palindrome
            MyTextField(
                value = inputPalindrome,
                onValueChange = onPalindromeChange,
                labelText = stringResource(R.string.palindrome),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.size(40.dp))
            // Check Button
            MyButton(
                text = stringResource(R.string.check),
                onClick = onCheckClick,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.size(12.dp))
            // Next Button
            MyButton(
                text = stringResource(R.string.next),
                onClick = { onNextClick(
                    inputName.ifBlank { "-" }
                ) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        if(showDialog) {
            AlertDialog(
                onDismissRequest = onDismissRequest,
                title = { Text("Palindrome Result") },
                text = { Text(text = palindromeResult) },
                confirmButton = {
                    TextButton(onClick = onConfirmClick) {
                        Text("Oke")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    SuitmediaTestTheme {
        FirstScreen(
            onNextClick = {}
        )
    }
}