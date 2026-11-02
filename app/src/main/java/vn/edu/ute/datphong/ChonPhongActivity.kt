package vn.edu.ute.datphong

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import vn.edu.ute.datphong.R
import vn.edu.ute.datphong.DuLieuMau
import vn.edu.ute.datphong.DatPhongHelper
import vn.edu.ute.datphong.dinhDangTien

class ChonPhongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chon_phong)

        val soKhach = intent.getIntExtra("so_khach", 1)
        val rgLoaiPhong = findViewById<RadioGroup>(R.id.rgLoaiPhong)
        val dsPhuHop = DatPhongHelper.locTheoSoKhach(DuLieuMau.dsLoaiPhong, soKhach)

        for (phong in dsPhuHop) {
            val rb = RadioButton(this).apply {
                text = "${phong.ten} (Sức chứa: ${phong.sucChua}) - ${phong.giaMotDem.dinhDangTien()}"
                tag = phong.ma
            }
            rgLoaiPhong.addView(rb)
        }

        findViewById<MaterialButton>(R.id.btnChonXong).setOnClickListener {
            val checkedId = rgLoaiPhong.checkedRadioButtonId
            if (checkedId == -1) {
                Toast.makeText(this, "Vui lòng chọn loại phòng", Toast.LENGTH_SHORT).show()
            } else {
                val rbSelected = findViewById<RadioButton>(checkedId)
                setResult(RESULT_OK, Intent().apply { putExtra("ma_loai_phong", rbSelected.tag.toString()) })
                finish()
            }
        }
    }
}