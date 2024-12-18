package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DoctorProfileScreen() {
    var selectedTime by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(21) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with doctor info
        DoctorHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Additional Information Section
        DoctorAdditionalInfo()

        Spacer(modifier = Modifier.height(16.dp))

        // Date selection
        DateSelection(
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Time slots
        TimeSlotSection(
            sectionTitle = "Morning",
            times = listOf("08:30 AM", "09:00 AM", "09:30 AM", "10:00 AM"),
            selectedTime = selectedTime,
            onTimeSelected = { selectedTime = it }
        )

        TimeSlotSection(
            sectionTitle = "Evening",
            times = listOf("05:30 PM", "06:00 PM", "06:30 PM", "07:00 PM"),
            selectedTime = selectedTime,
            onTimeSelected = { selectedTime = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Book Appointment Button
        Button(
            onClick = { /* Handle booking logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF536DFE))
        ) {
            Text(text = "BOOK AN APPOINTMENT", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun DoctorHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF536DFE), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Doctor Image
        Image(
            painter = painterResource(id = R.drawable.doctor), // Assurez-vous que cette ressource existe
            contentDescription = "Doctor Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            // Doctor Name
            Text(
                text = "Dr. Devy Shety",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            // Doctor Specialization
            Text(
                text = "Heart Surgeon",
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Ratings
            Row {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DoctorAdditionalInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Doctor's Details",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF536DFE),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp)

        Spacer(modifier = Modifier.height(8.dp))

        // Experience
        Text(
            text = "Experience: 15 years",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Specialization
        Text(
            text = "Specialization: Cardiothoracic Surgery",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Contact Information
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Contact: ",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "+1 234 567 890",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Location
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Location: ",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "New York City, USA",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun DateSelection(selectedDate: Int, onDateSelected: (Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        (21..25).forEach { date ->
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        if (date == selectedDate) Color(0xFF536DFE) else Color.LightGray,
                        RoundedCornerShape(12.dp)
                    )
                    .clickable { onDateSelected(date) },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Mon",
                        color = if (date == selectedDate) Color.White else Color.Black,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "$date",
                        color = if (date == selectedDate) Color.White else Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun TimeSlotSection(
    sectionTitle: String,
    times: List<String>,
    selectedTime: String,
    onTimeSelected: (String) -> Unit
) {
    Column {
        Text(

            text = sectionTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            times.forEach { time ->
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp)
                        .background(
                            if (time == selectedTime) Color(0xFF536DFE) else Color.LightGray,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { onTimeSelected(time) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = time,
                        color = if (time == selectedTime) Color.White else Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(15.dp))


    Column {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            times.forEach { time ->
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp)
                        .background(
                            if (time == selectedTime) Color(0xFF536DFE) else Color.LightGray,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { onTimeSelected(time) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = time,
                        color = if (time == selectedTime) Color.White else Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppointmentScreen() {
    DoctorProfileScreen()
}
