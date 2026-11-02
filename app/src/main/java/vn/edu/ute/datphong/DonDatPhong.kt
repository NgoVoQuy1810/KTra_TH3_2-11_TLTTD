package vn.edu.ute.datphong

data class DonDatPhong (
    val hoTen: String,
    val sdt: String,
    val email: String,
    val maLoaiPhong: String,
    val ngayNhan: String,
    val soDem: Int,
    val soKhach: Int,
    val ghiChu: String = ""
): java.io.Serializable {
    fun tomTat(): String {
        return "Họ tên: $hoTen, Phòng: $maLoaiPhong, Ngày nhận: $ngayNhan, Số đêm: $soDem"
    }
}