package com.example.catatankuliah

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CatatanViewModel(application: Application) :
    AndroidViewModel(application) {

    private val database = CatatanDatabase.getDatabase(application)
    private val repository = CatatanRepository(database.catatanDao())

    val semuaCatatan = repository.semuaCatatan.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun tambahCatatan(judul: String, isi: String, tanggal: String) {
        viewModelScope.launch {
            repository.tambah(
                Catatan(
                    judul = judul,
                    isi = isi,
                    tanggal = tanggal
                )
            )
        }
    }

    fun ubahCatatan(catatan: Catatan) {
        viewModelScope.launch {
            repository.ubah(catatan)
        }
    }

    fun hapusCatatan(catatan: Catatan) {
        viewModelScope.launch {
            repository.hapus(catatan)
        }
    }
}