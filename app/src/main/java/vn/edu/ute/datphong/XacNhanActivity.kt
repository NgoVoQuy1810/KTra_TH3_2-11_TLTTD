package vn.edu.ute.datphong
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import vn.edu.ute.datphong.R
import vn.edu.ute.datphong.DatPhongHelper
import vn.edu.ute.datphong.dinhDangTien
import vn.edu.ute.datphong.DonDatPhong

class XacNhanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xac_nhan)

        val don = intent.getSerializableExtra("don_dat_phong") as? DonDatPhong
        val tongTien = intent.getLongExtra("tong_tien", 0L)

        if (don != null) {
            val loai = DatPhongHelper.timLoaiPhong(don.maLoaiPhong)
            findViewById<TextView>(R.id.txtHoTen).text = "Họ tên: ${don.hoTen}"
            findViewById<TextView>(R.id.txtSdt).text = "SĐT: ${don.sdt}"
            findViewById<TextView>(R.id.txtEmail).text = "Email: ${don.email}"
            findViewById<TextView>(R.id.txtLoaiPhong).text = "Phòng: ${loai?.ten}"
            findViewById<TextView>(R.id.txtNgayNhan).text = "Ngày nhận: ${don.ngayNhan}"
            findViewById<TextView>(R.id.txtSoDem).text = "Số đêm: ${don.soDem}"
            findViewById<TextView>(R.id.txtSoKhach).text = "Số khách: ${don.soKhach}"
            findViewById<TextView>(R.id.txtTongTien).text = "Tổng tiền: ${tongTien.dinhDangTien()}"
        }

        findViewById<MaterialButton>(R.id.btnSua).setOnClickListener { finish() }
    }
}