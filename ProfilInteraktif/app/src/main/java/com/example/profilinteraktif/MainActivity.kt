
package com.example.profilinteraktif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilinteraktif.ui.theme.ProfilInteraktifTheme

private val Navy = Color(0xFF142B4A)
private val Blue = Color(0xFF28558A)
private val LightBlue = Color(0xFFEAF3FF)
private val InputWhite = Color(0xFFFFFFFF)
private val Teal = Color(0xFF24A99A)
private val Background = Color(0xFFF3F6FB)
private val Muted = Color(0xFF8290A3)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProfilInteraktifTheme {
                MaterialTheme(
                    colorScheme = lightColorScheme(
                        primary = Navy,
                        secondary = Teal,
                        background = Background,
                        surface = Color.White
                    )
                ) {
                    ProfilApp()
                }
            }
        }
    }
}

@Composable
fun ProfilApp() {
    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var prodi by remember { mutableStateOf("") }
    var sudahSimpan by remember { mutableStateOf(false) }

    val jumlahIsi = listOf(nama, nim, prodi)
        .count { it.isNotBlank() }

    val valid = jumlahIsi == 3
    val progress = jumlahIsi / 3f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
    ) {

        // HEADER APLIKASI
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Navy, Blue)
                    )
                )
                .padding(horizontal = 24.dp, vertical = 30.dp)
        ) {
            Text(
                text = "🎓  UNIVERSITAS MULIA",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "FAKULTAS ILMU KOMPUTER",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 11.sp,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Profil Mahasiswa",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Identitas mahasiswa digital",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )
        }

        // PROGRES PENGISIAN
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = (-17).dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Kelengkapan Profil",
                        color = Navy,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "$jumlahIsi dari 3 data",
                        color = Muted,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp),
                    color = Teal,
                    trackColor = Background
                )
            }
        }

        // FORM INFORMASI MAHASISWA
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = (-3).dp),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                // JUDUL FORM
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Informasi Mahasiswa",
                        color = Navy,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                LightBlue,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            )
                    ) {
                        Text(
                            text = "FORMULIR",
                            color = Blue,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // AREA FORM BERWARNA
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            LightBlue,
                            RoundedCornerShape(18.dp)
                        )
                        .padding(16.dp)
                ) {

                    LabelForm("NAMA LENGKAP")

                    InputMahasiswa(
                        value = nama,
                        placeholder = "Masukkan nama lengkap",
                        onValueChange = {
                            nama = it
                            sudahSimpan = false
                        }
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    LabelForm("NOMOR INDUK MAHASISWA")

                    InputMahasiswa(
                        value = nim,
                        placeholder = "Masukkan NIM",
                        onValueChange = {
                            nim = it
                            sudahSimpan = false
                        }
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    LabelForm("PROGRAM STUDI")

                    InputMahasiswa(
                        value = prodi,
                        placeholder = "Contoh: Informatika",
                        onValueChange = {
                            prodi = it
                            sudahSimpan = false
                        }
                    )
                }

                if (!valid && jumlahIsi > 0) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Lengkapi semua data sebelum menyimpan.",
                        color = Color(0xFFD15B5B),
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // TOMBOL SIMPAN
                Button(
                    onClick = {
                        sudahSimpan = true
                    },
                    enabled = valid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Navy,
                        disabledContainerColor = Color(0xFFD8DEE7)
                    )
                ) {
                    Text(
                        text = "Simpan Profil",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // HASIL PROFIL
        if (sudahSimpan) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "✓  Profil Berhasil Disimpan",
                        color = Teal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider(color = LightBlue)

                    Spacer(modifier = Modifier.height(12.dp))

                    DataHasil("Nama Lengkap", nama)
                    DataHasil("Nomor Induk Mahasiswa", nim)
                    DataHasil("Program Studi", prodi)
                }
            }
        }

        // FOOTER
        Text(
            text = "FORM PROFIL MAHASISWA • UNIVERSITAS MULIA",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            color = Muted,
            fontSize = 10.sp,
            letterSpacing = 0.5.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LabelForm(judul: String) {
    Text(
        text = judul,
        color = Blue,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.7.sp
    )

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun InputMahasiswa(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                color = Muted,
                fontSize = 13.sp
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Teal,
            unfocusedBorderColor = Color(0xFFD5E2F0),
            focusedContainerColor = InputWhite,
            unfocusedContainerColor = InputWhite
        )
    )
}

@Composable
fun DataHasil(label: String, nilai: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = Muted,
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = nilai,
            color = Navy,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
