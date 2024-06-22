package com.example.avatarbuilder

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.avatarbuilder.models.Avatar
import com.example.avatarbuilder.models.AvatarPart
import com.example.avatarbuilder.models.AvatarParts
import com.example.avatarbuilder.patterns.Style

@Composable
fun AvatarBuilderScreen(
    viewModel: AvatarBuilderViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value
    val avatar = uiState.avatar

    var showDialog by remember { mutableStateOf(false) }
    val dismissDialog = {
        showDialog = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .navigationBarsPadding()
            .systemBarsPadding()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Head(avatar)
            Image(
                modifier = Modifier.offset(y = (-16).dp),
                painter = painterResource(id = avatar.body.res), contentDescription = "Body"
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    showDialog = true
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                ),
                contentPadding = PaddingValues(12.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.dice),
                    contentDescription = "revert icon"
                )
            }
            Button(
                onClick = {
                    viewModel.toggleStyleChecked()
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                )
            ) {
                Text(text = if (uiState.isStylesChecked) "Avatar parts" else "Styles")
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.reset),
                    contentDescription = "revert icon"
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (uiState.isStylesChecked) {
                    Style.entries.forEach { style ->
                        Tag(
                            text = style.name,
                            selected = uiState.currentStyle == style,
                            onClick = {
                                viewModel.selectStyle(style)
                            }
                        )
                    }
                } else {
                    AvatarParts.entries.forEach { avatarPart ->
                        Tag(
                            text = avatarPart.name,
                            selected = uiState.currentAvatarPartsSection == avatarPart,
                            onClick = {
                                viewModel.selectAvatarPartsSection(avatarPart)
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(uiState.avatarParts) { avatarPart ->
                    AvatarPartView(
                        avatarPart = avatarPart,
                        selected = avatar.containsPart(avatarPart),
                        onClick = {
                            viewModel.selectAvatarPart(avatarPart)
                        }
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            modifier = Modifier.rotate(45f),
            text = uiState.flexLevel.flex,
            color = uiState.flexLevel.color,
            fontSize = 20.sp
        )
    }

    val partsToRandomize = remember {
        mutableStateListOf<AvatarParts>()
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = dismissDialog,
        ) {
            Column(
                Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .background(Color.Cyan, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {}
                        )
                    }
            ) {
                AvatarParts.entries.forEach { avatarPart ->
                    AvatarPartsCheckBox(
                        text = avatarPart.name,
                        checked = partsToRandomize.contains(avatarPart),
                        onChecked = { checked ->
                            if (checked) {
                                partsToRandomize.add(avatarPart)
                            } else {
                                partsToRandomize.remove(avatarPart)
                            }
                        }
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.randomizeAvatar(partsToRandomize)
                        dismissDialog()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    )
                ) {
                    Text(text = "Randomize")
                }
            }
        }
    }
}

@Composable
fun AvatarPartsCheckBox(
    text: String,
    checked: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text, color = Color.Black)
        Checkbox(checked = checked, onCheckedChange = onChecked)
    }
}

@Composable
fun Tag(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .widthIn(min = 64.dp)
            .border(
                width = 1.dp,
                color = if (selected) Color.Magenta else Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick()
            }
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.Black)
    }
}

@Composable
fun Head(avatar: Avatar) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.head),
            contentDescription = "head"
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = avatar.eyebrows.res),
                contentDescription = "eyebrows"
            )
            Image(
                painter = painterResource(id = avatar.eyes.res),
                contentDescription = "eyes"
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = avatar.mouth.res),
                contentDescription = "mouth"
            )
        }
    }
}

@Composable
fun AvatarPartView(
    avatarPart: AvatarPart,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = if (selected) Color.Magenta else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick()
            }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = avatarPart.res),
            contentDescription = "Avatar part"
        )
    }
}
