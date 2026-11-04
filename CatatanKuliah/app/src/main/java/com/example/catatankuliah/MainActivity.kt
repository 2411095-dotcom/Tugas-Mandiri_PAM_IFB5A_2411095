
package com.example.catatankuliah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catatankuliah.ui.theme.CatatanKuliahTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val Lavender = Color(0xFF8174B8)
private val DarkPurple = Color(0xFF514477)
private val LightLavender = Color(0xFFF1EEFA)
private val SoftBackground = Color(0xFFF8F7FC)
private val TextDark = Color(0xFF302B40)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatatanKuliahTheme {
                CatatanKuliahScreen()
            }
        }
    }
}

@Composable
fun CatatanKuliahScreen(
    viewModel: CatatanViewModel = viewModel()
) {
    val daftarCatatan by viewModel.semuaCatatan.collectAsState()

    var judul by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }
    var catatanDipilih by remember { mutableStateOf<Catatan?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(SoftBackground),
        contentPadding = PaddingValues(bottom = 30.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Lavender,
                                DarkPurple
                            )
                        ),
                        shape = RoundedCornerShape(
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    )
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 45.dp,
                        bottom = 30.dp
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color.White.copy(alpha = 0.18f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(12.dp).size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "MY STUDY NOTES",
                            color = Color.White.copy(alpha = 0.75f),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "Catatan Kuliah",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Simpan ide dan materi kuliahmu",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Semua catatan tersimpan dengan aman.",
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    text = if (catatanDipilih == null)
                        "Buat Catatan Baru"
                    else "Edit Catatan",
                    color = TextDark,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {
                        OutlinedTextField(
                            value = judul,
                            onValueChange = { judul = it },
                            label = { Text("Judul Catatan") },
                            placeholder = { Text("Contoh: Pemrograman Mobile") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = isi,
                            onValueChange = { isi = it },
                            label = { Text("Isi Materi Kuliah") },
                            placeholder = {
                                Text("Tuliskan materi yang ingin disimpan...")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            minLines = 4
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                if (
                                    judul.isNotBlank() &&
                                    isi.isNotBlank()
                                ) {
                                    if (catatanDipilih == null) {
                                        val tanggal =
                                            SimpleDateFormat(
                                                "dd-MM-yyyy",
                                                Locale.getDefault()
                                            ).format(Date())

                                        viewModel.tambahCatatan(
                                            judul.trim(),
                                            isi.trim(),
                                            tanggal
                                        )
                                    } else {
                                        viewModel.ubahCatatan(
                                            catatanDipilih!!.copy(
                                                judul = judul.trim(),
                                                isi = isi.trim()
                                            )
                                        )
                                    }

                                    judul = ""
                                    isi = ""
                                    catatanDipilih = null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DarkPurple
                            )
                        ) {
                            Icon(
                                Icons.Default.Save,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                if (catatanDipilih == null)
                                    "Simpan Catatan"
                                else "Perbarui Catatan",
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        if (catatanDipilih != null) {
                            TextButton(
                                onClick = {
                                    judul = ""
                                    isi = ""
                                    catatanDipilih = null
                                },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Text("Batal Edit")
                            }
                        }
                    }
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Daftar Catatan",
                    color = TextDark,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    color = LightLavender,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "${daftarCatatan.size} Catatan",
                        color = DarkPurple,
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 6.dp
                        ),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (daftarCatatan.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = Lavender,
                        modifier = Modifier.size(55.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Belum ada catatan",
                        color = TextDark,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Mulai simpan materi kuliahmu di sini.",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        } else {
            items(daftarCatatan, key = { it.id }) { catatan ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(17.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = LightLavender,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(
                                    Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = DarkPurple,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = catatan.judul,
                                    color = TextDark,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = catatan.tanggal,
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = catatan.isi,
                            color = Color.DarkGray,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        HorizontalDivider(color = LightLavender)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    catatanDipilih = catatan
                                    judul = catatan.judul
                                    isi = catatan.isi
                                }
                            ) {
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = Lavender
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text("Edit", color = DarkPurple)
                            }

                            TextButton(
                                onClick = {
                                    viewModel.hapusCatatan(catatan)

                                    if (catatanDipilih?.id == catatan.id) {
                                        catatanDipilih = null
                                        judul = ""
                                        isi = ""
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color(0xFFD65B70)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    "Hapus",
                                    color = Color(0xFFD65B70)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
