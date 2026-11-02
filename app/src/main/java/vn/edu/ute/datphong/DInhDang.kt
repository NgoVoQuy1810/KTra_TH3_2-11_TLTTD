package vn.edu.ute.datphong
import java.text.DecimalFormat

fun Long.dinhDangTien(): String {
    val format = DecimalFormat("#,###")
    return format.format(this).replace(",", ".") + " đ"
}