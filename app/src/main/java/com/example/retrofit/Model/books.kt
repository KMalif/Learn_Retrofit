package com.example.retrofit.Model

data class books(val bookResult : List<BookResult>){
    data class BookResult(val gambar: String,
                          val idbuku: Int,
                          val kategori: String,
                          val nama_buku: String,
                          val penerbit: String
    )
}


