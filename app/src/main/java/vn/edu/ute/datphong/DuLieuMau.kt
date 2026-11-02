package vn.edu.ute.datphong
import vn.edu.ute.datphong.LoaiPhong

object DuLieuMau {
    val dsLoaiPhong: List<LoaiPhong> = listOf(
        LoaiPhong("P236", "Phòng VIP MSSV", 2500000L, 2),
        LoaiPhong("S101", "Phòng Đơn Tiêu Chuẩn", 500000L, 1),
        LoaiPhong("D202", "Phòng Đôi Gia Đình", 1200000L, 4),
        LoaiPhong("L303", "Phòng Luxury", 3000000L, 2),
        LoaiPhong("F404", "Phòng Family Cỡ Lớn", 4000000L, 6)
    )
}
