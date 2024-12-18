package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import tn.esprit.mamassist.R

@Composable
fun PregnancyTrackerScreen() {
    var selectedMonth by remember { mutableStateOf(1) }
    var selectedTab by remember { mutableStateOf("Baby") }

    // Month images map
    val monthImages = mapOf(
        1 to R.drawable.month1, // Replace with your actual images
        2 to R.drawable.month2,
        3 to R.drawable.month3,
        4 to R.drawable.month4,
        5 to R.drawable.month5,
        6 to R.drawable.month6,
        7 to R.drawable.month7,
        8 to R.drawable.month8,
        9 to R.drawable.month9
    )

    // Detailed content for Baby and Mother
    val babyContent = mapOf(
        1 to "Your baby is as tiny as a sesame seed and growing rapidly! The neural tube, which becomes the brain and spine, starts forming. The heart also begins to beat.",
        2 to "Your baby is as big as a blueberry and developing quickly. The heartbeat is now strong, and small buds are forming where the arms and legs will grow.",
        3 to "Your baby is the size of a grape, about 2.5 cm long. Tiny arms and legs are moving, and facial features like the eyes, ears, and nose are taking shape.",
        4 to "Your baby is as big as a lime and weighs around 28 grams. Bones are starting to harden, and the baby can make small movements that are not yet felt.",
        5 to "Your baby is now the size of an avocado. Hair begins to grow on the scalp, and the baby's skin is becoming less transparent.",
        6 to "Your baby is the size of a mango. Fingerprints have formed, and the baby can now hiccup! You may begin to feel light movements called 'quickening.'",
        7 to "Your baby is about the size of a papaya. The brain and lungs are continuing to develop, and the baby can now open and close their eyelids.",
        8 to "Your baby is as big as a pineapple. Rapid weight gain occurs now, and the baby starts practicing breathing movements to prepare for life outside.",
        9 to "Your baby is the size of a watermelon and fully developed. Organs are ready to function, and the baby has dropped lower in preparation for delivery."
    )

    val motherContent = mapOf(
        1 to "You might feel early pregnancy symptoms like fatigue, nausea, and tender breasts. Make sure to rest and stay hydrated to help ease discomfort.",
        2 to "Nausea and morning sickness might peak this month. Your hormones are changing, and your body is adjusting to the growing baby. Eat small meals frequently.",
        3 to "You're finishing the first trimester, and energy levels may start to return. You may notice a small baby bump forming as your uterus expands.",
        4 to "You might experience back pain as the baby grows. Your belly is expanding, and you may need to start wearing maternity clothes for comfort.",
        5 to "You may start feeling the baby move, which is an exciting milestone! Stay active and ensure proper nutrition to support your growing baby.",
        6 to "Your body is working hard to nourish your baby. Leg cramps and swelling in your feet may occur. Stay hydrated and take time to rest.",
        7 to "Fatigue might return as the baby grows rapidly. You may notice Braxton Hicks contractions as your body prepares for delivery.",
        8 to "Your baby bump is prominent now! You might feel shortness of breath as the baby pushes against your diaphragm. Prepare for the final stretch.",
        9 to "You're almost there! You may feel tired and heavy. Frequent Braxton Hicks contractions are common as your body prepares for labor."
    )

    val months = (1..9).toList()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF2EB))
            .padding(16.dp)
    ) {
        // Month Selector Row
        Text(
            text = "Week ${selectedMonth * 4}",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color(0xFF4A3457),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(months) { month ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            if (month == selectedMonth) Color(0xFF8A6C9E) else Color(0xFFE2CFE2),
                            CircleShape
                        )
                        .clickable { selectedMonth = month }
                ) {
                    Text(
                        text = "$month",
                        color = if (month == selectedMonth) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            }
        }

        // Display Month Image
        monthImages[selectedMonth]?.let { imageRes ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Month $selectedMonth Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(150.dp)
                )
            }
        }

        // Tab Selector
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val tabs = listOf("Baby", "Mother")
            tabs.forEach { tab ->
                Button(
                    onClick = { selectedTab = tab },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == tab) Color(0xFF8A6C9E) else Color(0xFFF8EDEB),
                        contentColor = if (selectedTab == tab) Color.White else Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(text = tab, fontSize = 16.sp)
                }
            }
        }

        // Dynamic Content for Baby and Mother
        Text(
            text = when (selectedTab) {
                "Baby" -> babyContent[selectedMonth] ?: "Details not available."
                "Mother" -> motherContent[selectedMonth] ?: "Details not available."
                else -> ""
            },
            fontSize = 16.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPregnancyTrackerScreen() {
    PregnancyTrackerScreen()
}
