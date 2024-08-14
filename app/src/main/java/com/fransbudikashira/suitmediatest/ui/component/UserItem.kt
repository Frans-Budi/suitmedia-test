package com.fransbudikashira.suitmediatest.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fransbudikashira.suitmediatest.ui.theme.SuitmediaTestTheme

@Composable
fun UserItem(
    imageUrl: String,
    email: String,
    firstName: String,
    lastName: String,
    modifier: Modifier
) {
    Column {
        Box(
            modifier = modifier
                .padding(horizontal = 24.dp)
                .padding(vertical = 12.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    Text(
                        text = "$firstName $lastName",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Medium,
                        )
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    SuitmediaTestTheme {
        UserItem(
            imageUrl = "https://reqres.in/img/faces/1-image.jpg",
            email = "george.bluth@reqres.in",
            firstName = "George",
            lastName = "Bluth",
            modifier = Modifier,
        )
    }
}