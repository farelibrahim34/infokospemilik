package com.projectfarrel.infokosadmin.model

data class ResponsePesanKosItem(
    val createdAt: String,
    val id: String,
    val nama: String,
    val namaKos: String,
    val noHp: String,
    val noKamar: String,
    val tglpesan: String,
    val status: String
)