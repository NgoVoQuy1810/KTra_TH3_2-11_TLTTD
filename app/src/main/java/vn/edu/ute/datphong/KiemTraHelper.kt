package vn.edu.ute.datphong

object KiemTraHelper {
    fun kiemTraHoTen(hoTen: String): Boolean {
        return hoTen.matches(Regex("^[A-Za-zÀ-ỹ]+(\\s[A-Za-zÀ-ỹ]+)+$"))
    }

    fun kiemTraSdt(sdt: String): Boolean {
        return sdt.matches(Regex("^(05|09)\\d{8}$"))
    }

    fun kiemTraEmail(email: String): Boolean {
        return email.matches(Regex("^[a-zA-Z][a-zA-Z0-9._]{2,29}@gmail\\\\.com\$"))
    }

    fun kiemTraNgay(ngay: String): Boolean {
        return ngay.matches(Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(202[6-9])\$"))
    }
}