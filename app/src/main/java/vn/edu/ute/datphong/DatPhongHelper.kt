package vn.edu.ute.datphong
import vn.edu.ute.datphong.LoaiPhong
import vn.edu.ute.datphong.DuLieuMau

object DatPhongHelper {
    fun timLoaiPhong(ma: String): LoaiPhong? {
        val maTim = ma.trim()
        return DuLieuMau.dsLoaiPhong.find { it.ma.equals(maTim, ignoreCase = true) }
    }

    fun hopLeSoKhach(loai: LoaiPhong, soKhach: Int): Boolean {
        return soKhach in 1..(loai.sucChua + 2)
    }

    // (*) Tên hàm chứa MSSV
    fun tinhTienPhong_2415053122236(loai: LoaiPhong, soDem: Int, soKhach: Int): Long {
        if (soDem < 1 || !hopLeSoKhach(loai, soKhach)) return 0L

        var tienPhong = loai.giaMotDem * soDem
        // (*) Đặt từ 5 đêm trở lên giảm 10%
        if (soDem >= 5) {
            tienPhong -= (tienPhong * 10) / 100
        }

        val khachVuot = if (soKhach > loai.sucChua) soKhach - loai.sucChua else 0
        val phuThu = khachVuot * 150000L * soDem

        return tienPhong + phuThu
    }
}