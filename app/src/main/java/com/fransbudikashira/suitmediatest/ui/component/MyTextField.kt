package com.fransbudikashira.suitmediatest.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelText) },
        maxLines = 1,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MyTextFieldPreview() {
    MyTextField(
        value = "",
        onValueChange = {},
        labelText = "Name",
        modifier = Modifier.fillMaxWidth()
    )
}