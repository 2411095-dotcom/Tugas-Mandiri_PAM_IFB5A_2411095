package com.example.catatankuliah

import kotlinx.coroutines.flow.Flow

class CatatanRepository(
    private val catatanDao: CatatanDao
) {
    val semuaCatatan: Flow<List<Catatan>> =
        catatanDao.ambilSemuaCatatan()

    suspend fun tambah(catatan: Catatan) {
        catatanDao.tambahCatatan(catatan)
    }

    suspend fun ubah(catatan: Catatan) {
        catatanDao.ubahCatatan(catatan)
    }

    suspend fun hapus(catatan: Catatan) {
        catatanDao.hapusCatatan(catatan)
    }
}