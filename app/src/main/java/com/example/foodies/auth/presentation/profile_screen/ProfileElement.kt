package com.example.foodies.auth.presentation.profile_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.R

@Composable
fun ProfileElement(
    icon: Int,
    section: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon), 
                contentDescription = "Section icon",
                tint = Color(0xff222831),
                modifier = Modifier.size(26.dp)
            )

            Text(
                text = section,
                fontSize = 18.sp,
                color = Color(0xff222831)
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Icon arrow right",
            tint = Color(0xff222831),
            modifier = Modifier.size(30.dp)
        )
    }

    HorizontalDivider(thickness = 2.dp, color = Color(0xfff6f7f9))
}