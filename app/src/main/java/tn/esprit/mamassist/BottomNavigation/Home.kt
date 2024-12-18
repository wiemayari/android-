package tn.esprit.mamassist.BottomNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.tooling.preview.Preview
import tn.esprit.mamassist.R
import tn.esprit.mamassist.ui.theme.Pink40

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) } // Ajout de la BottomBar
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Gérer le padding pour le contenu
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //ConstraintLayout {
            //   val (topImg, profile) = createRefs()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(245.dp)
                    .background(
                        Pink40,
                        shape = RoundedCornerShape(bottomStart = 70.dp, bottomEnd = 70.dp)
                    )
            ) {

                Row(
                    modifier = Modifier
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .height(100.dp)
                            .padding(start = 14.dp)
                            .weight(0.7f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Hello",
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Wifek Ben Abdallah",
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 14.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.White, shape = RoundedCornerShape(50.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.waaaffuu),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color.Transparent)
                        )
                    }
                }
            }




            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 0.dp
                    )
                    .offset(y = (-50).dp)
                    .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .align(Alignment.CenterHorizontally)
            )
            {
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp, end = 12.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#ffe0c8")),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.date),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(top = 8.dp, bottom = 4.dp)
                    )

                    Text(
                        text = "Date de conception",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#c77710")),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp, end = 8.dp, start = 8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#ffe0c8")),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.update),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(top = 8.dp, bottom = 4.dp)
                    )
                    Text(
                        text = "Mise à jour des hebdomadaires",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#c77710")),
                        modifier = Modifier.fillMaxWidth(), // Remplit toute la largeur
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#ffe0c8")),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bebe),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(top = 8.dp, bottom = 4.dp)
                    )
                    Text(
                        text = "Croissance de bébé",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(android.graphics.Color.parseColor("#c77710")),
                        modifier = Modifier.fillMaxWidth(), // Remplit toute la largeur
                        textAlign = TextAlign.Center // Centre le texte horizontalement
                    )

                }

            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp)
//                    .offset(y = (-10).dp)
                    .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate("yourSituation") } // Ajout de la navigation

            )
            {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 15.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(25.dp))
                        .height(100.dp)
                        .background( // Appliquer le fond rose ici
                            color = Pink40,
                            shape = RoundedCornerShape(
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                ) {
                    val (img, text1, text2) = createRefs()

                    // Image
                    Image(
                        painter = painterResource(id = R.drawable.compte),
                        contentDescription = null,
                        modifier = Modifier.constrainAs(img) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                    )

                    // Texte principal
                    Text(
                        text = "To Complete your profile info",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 16.dp, start = 8.dp)
                            .constrainAs(text1) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                    )

                    // Texte secondaire
                    Text(
                        text = "Upgrade Your Account",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp)
                            .constrainAs(text2) {
                                top.linkTo(text1.bottom)
                                start.linkTo(parent.start)
                            }
                    )
                }
            }


// Spacer entre le Box et le premier Row
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp)
//                    .offset(y = (-10).dp)
                    .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate("healthAppHome") } // Ajout de la navigation

            )
            {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 15.dp)
                        .shadow(3.dp, shape = RoundedCornerShape(25.dp))
                        .height(100.dp)
                        .background( // Appliquer le fond rose ici
                            color = Pink40,
                            shape = RoundedCornerShape(
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                ) {
                    val (img, text1, text2) = createRefs()

                    // Image
                    Image(
                        painter = painterResource(id = R.drawable.doctor),
                        contentDescription = null,
                        modifier = Modifier.constrainAs(img) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                    )

                    // Texte principal
                    Text(
                        text = "Doctor Right Now!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 16.dp, start = 8.dp)
                            .constrainAs(text1) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                    )

                    // Texte secondaire
                    Text(
                        text = "let's consult the best doctor",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp)
                            .constrainAs(text2) {
                                top.linkTo(text1.bottom)
                                start.linkTo(parent.start)
                            }
                    )
                }
            }
// Première rangée
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (30).dp)
                    .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier.weight(0.25f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = null,
                        Modifier
                            .padding(top = 8.dp, bottom = 4.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    )
                    Text(
                        text = "Inbox",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(android.graphics.Color.parseColor("#2e3d6d"))
                    )
                }
                Column(
                    modifier = Modifier.weight(0.25f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hospital),
                        contentDescription = null,
                        Modifier
                            .padding(top = 8.dp, bottom = 4.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    )
                    Text(
                        text = "Hospital",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(android.graphics.Color.parseColor("#2e3d6d"))
                    )
                }
                Column(
                    modifier = Modifier.weight(0.25f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        Modifier
                            .padding(top = 8.dp, bottom = 4.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    )
                    Text(
                        text = "Settings",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(android.graphics.Color.parseColor("#2e3d6d"))
                    )
                }
            }

// Spacer entre les deux Row
            Spacer(modifier = Modifier.height(16.dp))

// Deuxième rangée
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (50).dp)
                    .shadow(3.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier.weight(0.25f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null,
                        Modifier
                            .padding(top = 8.dp, bottom = 4.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    )
                    Text(
                        text = "Calendar",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(android.graphics.Color.parseColor("#2e3d6d"))
                    )
                }
                Column(
                    modifier = Modifier.weight(0.25f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        Modifier
                            .padding(top = 8.dp, bottom = 4.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    )
                    Text(
                        text = "Settings",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(android.graphics.Color.parseColor("#2e3d6d"))
                    )
                }
                Column(
                    modifier = Modifier.weight(0.25f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        Modifier
                            .padding(top = 8.dp, bottom = 4.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    )
                    Text(
                        text = "More",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(android.graphics.Color.parseColor("#2e3d6d"))
                    )
                }
            }
        }
    }
    }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val fakeNavController = rememberNavController()
    HomeScreen(navController = fakeNavController)
}