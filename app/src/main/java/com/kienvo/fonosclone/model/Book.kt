package com.kienvo.fonosclone.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverUrl: String
)

// Dữ liệu giả (Dùng link Amazon/TM DB cho chắc ăn)
fun getMockBooks(): List<Book> {
    return listOf(
        Book("1", "Nhà Giả Kim", "Paulo Coelho", "https://nxbhcm.com.vn/Image/Biasach/nhagiakimTB2020.jpg"), // Link ví dụ, lát dùng link thật bên dưới
        Book("2", "Đắc Nhân Tâm", "Dale Carnegie", "https://nxbhcm.com.vn/Image/Biasach/dacnhantam86.jpg"),
        Book("3", "Sapiens", "Yuval Noah Harari", "https://images-na.ssl-images-amazon.com/images/I/811PTyrckTL.jpg"),
        Book("4","Cây cam ngọt của tôi","José Mauro","https://nld.mediacdn.vn/2021/1/22/13-cay-cam-ngot-161132379604435791636.jpg")
        // ... Copy thêm vài cuốn cho đầy danh sách
    )
}