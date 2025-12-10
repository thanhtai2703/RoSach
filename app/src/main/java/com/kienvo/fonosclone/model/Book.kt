package com.kienvo.fonosclone.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverUrl: String
)

// Dữ liệu giả
fun getBooks(): List<Book> {
    return listOf(
        Book("1", "Nhà Giả Kim", "Paulo Coelho", "https://nxbhcm.com.vn/Image/Biasach/nhagiakimTB2020.jpg"),
        Book("2", "Đắc Nhân Tâm", "Dale Carnegie", "https://nxbhcm.com.vn/Image/Biasach/dacnhantam86.jpg"),
        Book("3", "Sapiens", "Yuval Noah Harari", "https://images-na.ssl-images-amazon.com/images/I/811PTyrckTL.jpg"),
        Book("4","Cây Cam Ngọt Của Tôi","José Mauro","https://nld.mediacdn.vn/2021/1/22/13-cay-cam-ngot-161132379604435791636.jpg"),
        Book("5","Trí Tuệ Do Thái","Eran Katz","https://bizweb.dktcdn.net/thumb/1024x1024/100/197/269/products/tri-tue-do-thai.jpg?v=1510634413673"),
        Book("6","Kinh Thánh Nói Gì Về Tương Lai","Charles H. Dyer","https://bizweb.dktcdn.net/thumb/1024x1024/100/465/223/products/kinh-thanh-noi-gi-ve-tuong-lai.jpg?v=1692868032187"),
        Book("7","Đọc Vị Bất Kì Ai","David J. Lieberman","https://cdn.hstatic.net/products/200000900535/doc_vi_bat_ky_ai_de_khong_bi_loi_dung_-bia_1__tb_2025__899034494358448295b41a80dc16019e.jpg"),
        Book("8","Muôn Kiếp Nhân Sinh","Nguyên Phong","https://product.hstatic.net/200000122283/product/bia1-muonkiepnhansinh3-01_d1a246c6abfd4621bed63b8ca3b73ba9_master.jpg"),
        // ... Copy thêm vài cuốn cho đầy danh sách
    )
}

fun getPopularBooks(): List<Book> {
    return listOf(
        Book("9","Nam Châm Tài Chính","Marie-Claire Carlyle","https://bizbooks.vn/uploads/images/2023/thang-10/1-nam-cham-tai-chinh-mt.jpg"),
        Book("10","Hành Trình Trở Thành Người Giáo Viên","Nguyễn Công Thái","https://i0.wp.com/sachnoiviet.net/wp-content/uploads/2022/03/hanh-trinh-tro-thanh-nguoi-giao-vien-hanh-phuc-thinh-vuong-binh-an.jpg?fit=200%2C300&ssl=1"),
        Book("11","Bách khoa thư về khoa học- Trái Đất và vũ trụ","Nhiều tác giả","https://cdn1.fahasa.com/media/catalog/product/i/m/image_195509_1_44248.jpg?_gl=1*hnfzpb*_ga*MzAxNTAxNjIyLjE3NjQ4NjQwNjY.*_ga_D3YYPWQ9LN*czE3NjQ4NjQwNjUkbzEkZzAkdDE3NjQ4NjQwNjUkajYwJGwwJGgw*_gcl_aw*R0NMLjE3NjQ4NjQwNjYuQ2owS0NRaUFfOFRKQmhETkFSSXNBUFg1cXhUYUMzdnNQS09BaklOWkdhdlM4c2lFNzNnZldJQ3M5bThQUVlhZTBmdmVzdzhyMnFmN0RwVWFBc1RzRUFMd193Y0I.*_gcl_au*MTUyNzYzOTcwMi4xNzY0ODY0MDY2*_ga_460L9JMC2G*czE3NjQ4NjQwNjUkbzEkZzEkdDE3NjQ4NjQwNjYkajU5JGwwJGgxMzM1OTM5NDUy"),
        Book("12","Tính Ưu Việt Của Hoài Nghi","Tim Palmer","https://www.nxbtre.com.vn/Images/Book/nxbtre_full_25122025_111216.jpg"),
        Book("13","Tĩnh Lặng","Thích Nhất Hạnh","https://cdn.hstatic.net/products/200000900535/tinh_lang_-_bia_1_tb_2025__c4a6ae7b209f4a8792c707b6a9b69a6e.jpg"),
        Book("14","Sống Chậm","Melanie Barnes","https://minhkhai.com.vn/hinhlon/8936186549434.jpg"),
        Book("15","Tuổi Trẻ Đáng Giá Bao Nhiêu","Rosie Nguyễn","https://nld.mediacdn.vn/2018/3/24/sach-1521858607292758740290.jpg"),
        Book("16","Chó Sủa Nhầm Cây","Eric Barker","https://cdn1.fahasa.com/media/flashmagazine/images/page_images/cho_sua_nham_cay_tb2023___tai_sao_nhung_gi_ta_biet_ve_thanh_cong_co_khi_lai_sai/2023_06_22_11_07_10_1-390x510.jpg?_gl=1*10va6ot*_gcl_aw*R0NMLjE3NjQ4NjQ2MjcuQ2owS0NRaUFfOFRKQmhETkFSSXNBUFg1cXhUMWQyRWVxRkFQMDE4R3ZvOXVWVDV4ajRxUFhWUlgxQ2UtUi05b3BNVWUxOXlXeDZaMnVKRWFBcWxPRUFMd193Y0I.*_gcl_au*MTUyNzYzOTcwMi4xNzY0ODY0MDY2*_ga*MzAxNTAxNjIyLjE3NjQ4NjQwNjY.*_ga_D3YYPWQ9LN*czE3NjQ4NjQwNjUkbzEkZzEkdDE3NjQ4NjQ2MjYkajMxJGwwJGgw*_ga_460L9JMC2G*czE3NjQ4NjQwNjUkbzEkZzEkdDE3NjQ4NjQ2MjgkajI5JGwwJGgxMzM1OTM5NDUy"),
        )
}

fun getHealingBooks(): List<Book> {
    return listOf(
        Book("17","Một Cuốn Sách Chữa Lành","Brianna Wiest","https://davibooks.vn/stores/uploads/z/z4729024325679_319a5b9666920fe8e785dcf3f0102996__97337_image2_800_big.jpg"),
        Book("18","Một Thoáng Rực Rỡ Giữa Nhân Gian","Ocean Vuong","https://bizweb.dktcdn.net/thumb/1024x1024/100/363/455/products/motthoangtarucroonhangian011.jpg?v=1705552591463"),
        Book("19","Sức Mạnh Chữa Lành Cơ Thể Của Nước","F. Batmanghelidj, M.D","https://bizweb.dktcdn.net/thumb/1024x1024/100/465/223/products/86.png?v=1761215098273"),
        Book("20","Hoàng Tử Bé","Antoine De Saint-Exupéry","https://bizweb.dktcdn.net/thumb/1024x1024/100/363/455/products/hoangtube.jpg?v=1705552581243"),
        Book("21","Làm Lành Với Bản Thân","Manuela Mischke-Reeds","https://bizweb.dktcdn.net/thumb/1024x1024/100/465/223/products/24-4f07600c-0995-4bfa-9c34-785531ddaac0.png?v=1752574705253"),
        Book("22","Luật Nhân Quả - Tự Vấn Nghiệp Duyên, Xoay Chuyển Số Mệnh","James Rondepierre","https://bizweb.dktcdn.net/thumb/1024x1024/100/465/223/products/52-01df7223-c077-446b-b425-02eb1906df93.png?v=1755331057940"),
        Book("23","Để Tâm Trí Là Nơi An Trú","Morgan Harper Nichols","https://minhkhai.com.vn/hinhlon/8935325026768-.jpg"),
        Book("24","Chữa Lành Bản Thân Trong Thế Giới Đầy Tổn Thương","Dr. Ahona Guha","https://product.hstatic.net/200000696663/product/8936225390362_36cd29599252412f84c5647b0aa18f6b_1024x1024.jpg"),
        )
}

fun getDetectiveBooks(): List<Book> {
    return listOf(
        Book("25","Những Cuộc Phiêu Lưu Của Sherlock Holmes","Arthur Conan Doyle","https://product.hstatic.net/1000237375/product/bia_truoc_sh_78b82a0001784b94933be85d99796552_master.jpg"),
        Book("26","GOTH Những Kẻ Hắc Ám","Otsuichi","https://bizweb.dktcdn.net/thumb/1024x1024/100/363/455/products/gothnhungkehacam01.jpg?v=1705552558923"),
        Book("27","Thú Tội","Minato Kanae","https://bizweb.dktcdn.net/thumb/1024x1024/100/363/455/products/thutoi01.jpg?v=1705552105693"),
        Book("28","13.67","Chan Ho Kei","https://product.hstatic.net/200000287623/product/13.67_61e241f0b6d743d883517c6bbf260e44_master.jpg"),
        Book("29","Ghi Chép Pháp Y","Lưu Hiểu Huy","https://minhkhai.com.vn/hinhlon/8935325009433-.jpg"),
        Book("30","Ngôi Nhà Kỳ Quái","Uketsu","https://cdn1.fahasa.com/media/catalog/product/8/9/8935095632763.jpg"),
        Book("31","Sự Im Lặng Của Bầy Cừu","Thomas Harris","https://lh3.googleusercontent.com/pw/ACtC-3dbCNcGCYZntEXQfkARgdQD1yEN0n-pRltjNUTlpshRWAFXo-c6HsNoE7Jw6fzMm-bL1QlIrJdG0a5-_73YenG6e9-a5ayZkRVu7cmnum3aiFjvR95BWFhOoE7U2R7Fja1gNKAfpvoAnXZKSFXiGl3zVQ=w384-h576-no?authuser=0"),
        Book("32","Dữ liệu tử thần","Jeffery Deaver ","https://i.ex-cdn.com/mientay.giadinhonline.vn/files/content/2021/08/19/42-1241.jpg"),
        )
}

