
package com.example.profilnavigasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// =====================================
// WARNA APLIKASI
// =====================================

private val Purple = Color(0xFF8B5CF6)
private val PurpleDark = Color(0xFF5125A8)
private val PurpleDeep = Color(0xFF35166F)
private val Lavender = Color(0xFFEDE5FF)
private val Background = Color(0xFFFAF8FF)
private val White = Color(0xFFFFFFFF)
private val Charcoal = Color(0xFF29213A)
private val Gray = Color(0xFF827A91)
private val BorderSoft = Color(0xFFE9E2F5)
private val Peach = Color(0xFFFFD8C5)

// =====================================
// MAIN ACTIVITY
// =====================================

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                ProfilNavigasiApp()
            }
        }
    }
}

// =====================================
// NAVIGASI APLIKASI
// =====================================

@Composable
fun ProfilNavigasiApp(
    profilViewModel: ProfilViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "beranda"
    ) {
        composable("beranda") {
            HalamanBeranda {
                navController.navigate("form")
            }
        }

        composable("form") {
            HalamanForm(
                profilViewModel = profilViewModel,
                onSimpan = {
                    navController.navigate("detail")
                }
            )
        }

        composable("detail") {
            HalamanDetail(
                profilViewModel = profilViewModel,
                onKembali = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// =====================================
// HALAMAN BERANDA
// =====================================

@Composable
fun HalamanBeranda(
    onMulai: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        // HEADER STUDENT PORTAL

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 48.dp,
                        bottomEnd = 48.dp
                    )
                )
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF48208E),
                            Color(0xFF9259EF)
                        )
                    )
                )
                .padding(horizontal = 25.dp)
        ) {
            // ORNAMEN HEADER

            Box(
                modifier = Modifier
                    .size(125.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 35.dp, y = (-45).dp)
                    .clip(CircleShape)
                    .background(
                        White.copy(alpha = 0.07f)
                    )
            )

            Box(
                modifier = Modifier
                    .size(75.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 15.dp, y = 25.dp)
                    .clip(CircleShape)
                    .background(
                        White.copy(alpha = 0.08f)
                    )
            )

            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(19.dp))
                        .background(
                            White.copy(alpha = 0.22f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🎓",
                        fontSize = 35.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "Student Portal",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Digital Student Profile",
                        fontSize = 13.sp,
                        color = Color(0xFFE9DDFF)
                    )
                }
            }
        }

        // KONTEN UTAMA

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // LABEL WELCOME

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Lavender)
                    .padding(
                        horizontal = 15.dp,
                        vertical = 7.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(Purple)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "WELCOME",
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6535B5)
                )
            }

            Spacer(modifier = Modifier.height(17.dp))

            Text(
                text = "Selamat Datang",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = PurpleDeep,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "di Aplikasi Profil Mahasiswa",
                fontSize = 16.sp,
                color = Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            // KARTU ILUSTRASI

            Card(
                modifier = Modifier.size(250.dp),
                shape = RoundedCornerShape(35.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Lavender
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(185.dp)
                            .clip(CircleShape)
                            .background(
                                White.copy(alpha = 0.45f)
                            )
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🎓",
                            fontSize = 105.sp
                        )

                        Spacer(modifier = Modifier.height(1.dp))

                        Text(
                            text = "📜",
                            fontSize = 57.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(13.dp)
                            .align(Alignment.TopEnd)
                            .offset((-25).dp, 27.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFB99AF5))
                    )

                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .align(Alignment.BottomStart)
                            .offset(30.dp, (-28).dp)
                            .clip(CircleShape)
                            .background(Peach)
                    )
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            // TOMBOL BUAT PROFIL

            Button(
                onClick = onMulai,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5726A5),
                    contentColor = White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 1.dp
                )
            ) {
                Text(
                    text = "Buat Profil Mahasiswa",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "→",
                    fontSize = 23.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            // DEKORASI BAWAH

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(25.dp)
                        .height(2.dp)
                        .background(Color(0xFFD7C2FF))
                )

                Spacer(modifier = Modifier.width(9.dp))

                Text(
                    text = "STUDENT IDENTITY",
                    fontSize = 9.sp,
                    letterSpacing = 2.sp,
                    color = Color(0xFF9A8BB2)
                )

                Spacer(modifier = Modifier.width(9.dp))

                Box(
                    modifier = Modifier
                        .width(25.dp)
                        .height(2.dp)
                        .background(Color(0xFFD7C2FF))
                )
            }
        }

        // BAGIAN BAWAH

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Lavender,
                            Color(0xFFD7C2FF)
                        )
                    )
                )
        )
    }
}

// =====================================
// HEADER HALAMAN
// =====================================

@Composable
fun HeaderHalaman(
    label: String,
    judul: String,
    deskripsi: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        PurpleDark,
                        Purple
                    )
                )
            )
            .padding(23.dp)
    ) {
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp,
            color = Peach
        )

        Spacer(modifier = Modifier.height(13.dp))

        Text(
            text = judul,
            fontSize = 27.sp,
            lineHeight = 34.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = deskripsi,
            fontSize = 13.sp,
            lineHeight = 20.sp,
            color = Lavender
        )
    }
}

// =====================================
// TOMBOL UTAMA
// =====================================

@Composable
fun TombolUtama(
    teks: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PurpleDark,
            contentColor = White
        )
    ) {
        Text(
            text = teks,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "→",
            fontSize = 19.sp
        )
    }
}

// =====================================
// KARTU PUTIH
// =====================================

@Composable
fun KartuPutih(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            content = content
        )
    }
}

// =====================================
// HALAMAN FORM PROFIL
// =====================================

@Composable
fun HalamanForm(
    profilViewModel: ProfilViewModel,
    onSimpan: () -> Unit
) {
    var nama by remember {
        mutableStateOf(profilViewModel.nama)
    }

    var nim by remember {
        mutableStateOf(profilViewModel.nim)
    }

    var tampilkanError by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(22.dp)
    ) {
        HeaderHalaman(
            label = "PERSONAL INFORMATION",
            judul = "Lengkapi Profil Kamu",
            deskripsi = "Masukkan identitas mahasiswa dengan benar."
        )

        Spacer(modifier = Modifier.height(24.dp))

        KartuPutih {
            Text(
                text = "Informasi Mahasiswa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Charcoal
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Nama Lengkap",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nama,
                onValueChange = {
                    nama = it
                    tampilkanError = false
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Masukkan nama lengkap")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Purple,
                    unfocusedBorderColor = BorderSoft,
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    cursorColor = PurpleDark
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Nomor Induk Mahasiswa (NIM)",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nim,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        nim = it
                        tampilkanError = false
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Masukkan NIM")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Purple,
                    unfocusedBorderColor = BorderSoft,
                    focusedContainerColor = White,
                    unfocusedContainerColor = White,
                    cursorColor = PurpleDark
                )
            )

            if (tampilkanError) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Nama dan NIM wajib diisi.",
                    color = Color(0xFFBC4545),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            TombolUtama(
                teks = "Simpan Profil"
            ) {
                if (nama.isNotBlank() && nim.isNotBlank()) {
                    profilViewModel.simpan(
                        nama.trim(),
                        nim.trim()
                    )
                    onSimpan()
                } else {
                    tampilkanError = true
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Pastikan data yang dimasukkan sudah benar.",
            fontSize = 12.sp,
            color = Gray
        )
    }
}

// =====================================
// HALAMAN DETAIL PROFIL
// =====================================

@Composable
fun HalamanDetail(
    profilViewModel: ProfilViewModel,
    onKembali: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(22.dp)
    ) {
        HeaderHalaman(
            label = "STUDENT INFORMATION",
            judul = "Profil Mahasiswa",
            deskripsi = "Identitas mahasiswa berhasil disimpan."
        )

        Spacer(modifier = Modifier.height(25.dp))

        // KARTU IDENTITAS

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            PurpleDark,
                            Purple
                        )
                    )
                )
                .padding(23.dp)
        ) {
            Text(
                text = "STUDENT IDENTITY",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = Peach
            )

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(CircleShape)
                        .background(Lavender),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profilViewModel.nama
                            .trim()
                            .firstOrNull()
                            ?.uppercase() ?: "?",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = PurpleDark
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "NAMA MAHASISWA",
                        fontSize = 9.sp,
                        letterSpacing = 1.sp,
                        color = Lavender
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = profilViewModel.nama,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            HorizontalDivider(
                color = White.copy(alpha = 0.3f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "NOMOR INDUK MAHASISWA",
                fontSize = 9.sp,
                letterSpacing = 1.sp,
                color = Lavender
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = profilViewModel.nim,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "STUDENT PROFILE",
                fontSize = 9.sp,
                letterSpacing = 1.5.sp,
                color = Lavender
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Rincian Identitas",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = Charcoal
        )

        Spacer(modifier = Modifier.height(14.dp))

        KartuPutih {
            RincianData(
                label = "Nama Lengkap",
                nilai = profilViewModel.nama
            )

            Spacer(modifier = Modifier.height(18.dp))

            HorizontalDivider(
                color = BorderSoft
            )

            Spacer(modifier = Modifier.height(18.dp))

            RincianData(
                label = "Nomor Induk Mahasiswa",
                nilai = profilViewModel.nim
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13.dp))
                .background(Lavender)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "✓",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = PurpleDark
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Profil mahasiswa berhasil disimpan.",
                fontSize = 13.sp,
                color = PurpleDark
            )
        }

        Spacer(modifier = Modifier.height(23.dp))

        TombolUtama(
            teks = "Kembali ke Form"
        ) {
            onKembali()
        }
    }
}

// =====================================
// RINCIAN IDENTITAS
// =====================================

@Composable
fun RincianData(
    label: String,
    nilai: String
) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Gray
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = nilai,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Charcoal
        )
    }
}
