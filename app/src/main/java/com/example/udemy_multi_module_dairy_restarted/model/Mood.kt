package com.example.udemy_multi_module_dairy_restarted.model

import androidx.compose.ui.graphics.Color
import com.example.udemy_multi_module_dairy_restarted.R
import com.example.udemy_multi_module_dairy_restarted.ui.theme.AngryColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.AwfulColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.BoredColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.CalmColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.DepressedColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.DisappointedColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.HappyColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.HumorousColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.LonelyColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.MysteriousColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.NeutralColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.RomanticColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.ShamefulColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.SurprisedColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.SuspiciousColor
import com.example.udemy_multi_module_dairy_restarted.ui.theme.TenseColor

enum class Mood(
    val icon: Int,
    val contentColor: Color,
    val containerColor: Color
) {
    Neutral(
        icon = R.drawable.neutral,
        contentColor = Color.Black,
        containerColor = NeutralColor
    ),
    Happy(
        icon = R.drawable.happy,
        contentColor = Color.Black,
        containerColor = HappyColor
    ),
    Angry(
        icon = R.drawable.angry,
        contentColor = Color.White,
        containerColor = AngryColor
    ),
    Bored(
        icon = R.drawable.bored,
        contentColor = Color.Black,
        containerColor = BoredColor
    ),
    Calm(
        icon = R.drawable.calm,
        contentColor = Color.Black,
        containerColor = CalmColor
    ),
    Depressed(
        icon = R.drawable.depressed,
        contentColor = Color.Black,
        containerColor = DepressedColor
    ),
    Disappointed(
        icon = R.drawable.disappointed,
        contentColor = Color.White,
        containerColor = DisappointedColor
    ),
    Humorous(
        icon = R.drawable.humorous,
        contentColor = Color.Black,
        containerColor = HumorousColor
    ),
    Lonely(
        icon = R.drawable.lonely,
        contentColor = Color.White,
        containerColor = LonelyColor
    ),
    Mysterious(
        icon = R.drawable.mysterious,
        contentColor = Color.Black,
        containerColor = MysteriousColor
    ),
    Romantic(
        icon = R.drawable.romantic,
        contentColor = Color.White,
        containerColor = RomanticColor
    ),
    Shameful(
        icon = R.drawable.shameful,
        contentColor = Color.White,
        containerColor = ShamefulColor
    ),
    Awful(
        icon = R.drawable.awful,
        contentColor = Color.Black,
        containerColor = AwfulColor
    ),
    Surprised(
        icon = R.drawable.surprised,
        contentColor = Color.Black,
        containerColor = SurprisedColor
    ),
    Suspicious(
        icon = R.drawable.suspicious,
        contentColor = Color.Black,
        containerColor = SuspiciousColor
    ),
    Tense(
        icon = R.drawable.tense,
        contentColor = Color.Black,
        containerColor = TenseColor
    )
}