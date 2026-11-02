package vn.edu.ute.datphong

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import vn.edu.ute.datphong.R
import vn.edu.ute.datphong.DuLieuMau
import vn.edu.ute.datphong.DatPhongHelper
import vn.edu.ute.datphong.KiemTraHelper

class DatPhongActivity : AppCompatActivity() {

    private lateinit var layoutTen: TextInputLayout
    private lateinit var edtHoTen: TextInputEditText
    private lateinit var layoutSdt: TextInputLayout
    private lateinit var edtSdt: TextInputEditText
    private lateinit var layoutEmail: TextInputLayout
    private lateinit var edtEmail: TextInputEditText
    private lateinit var layoutNgay: TextInputLayout
    private lateinit var edtNgayNhan: TextInputEditText
    private lateinit var layoutDem: TextInputLayout
    private lateinit var edtSoDem: TextInputEditText
    private lateinit var layoutKhach: TextInputLayout
    private lateinit var edtSoKhach: TextInputEditText
    private lateinit var txtLoaiPhong: TextView
    private lateinit var txtTamTinh: TextView
    private lateinit var btnDatPhong: MaterialButton

    private var loaiPhongHienTai = DuLieuMau.dsLoaiPhong[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dat_phong)

        layoutTen = findViewById(R.id.layoutTen)
        edtHoTen = findViewById(R.id.edtHoTen)
        layoutSdt = findViewById(R.id.layoutSdt)
        edtSdt = findViewById(R.id.edtSdt)
        layoutEmail = findViewById(R.id.layoutEmail)
        edtEmail = findViewById(R.id.edtEmail)
        layoutNgay = findViewById(R.id.layoutNgay)
        edtNgayNhan = findViewById(R.id.edtNgayNhan)
        layoutDem = findViewById(R.id.layoutDem)
        edtSoDem = findViewById(R.id.edtSoDem)
        layoutKhach = findViewById(R.id.layoutKhach)
        edtSoKhach = findViewById(R.id.edtSoKhach)
        txtLoaiPhong = findViewById(R.id.txtLoaiPhong)
        txtTamTinh = findViewById(R.id.txtTamTinh)
        btnDatPhong = findViewById(R.id.btnDatPhong)

        txtLoaiPhong.text = "Loại phòng: ${loaiPhongHienTai.ten} - ${loaiPhongHienTai.giaMotDem}"

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { capNhatTamTinh() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        edtSoDem.addTextChangedListener(textWatcher)
        edtSoKhach.addTextChangedListener(textWatcher)

        btnDatPhong.setOnClickListener {
            if (kiemTraHopLe()) {
                val don = vn.edu.ute.datphong.DonDatPhong(
                    edtHoTen.text.toString().trim(),
                    edtSdt.text.toString().trim(),
                    edtEmail.text.toString().trim(),
                    loaiPhongHienTai.ma,
                    edtNgayNhan.text.toString().trim(),
                    edtSoDem.text.toString().toInt(),
                    edtSoKhach.text.toString().toInt()
                )
                val tien = DatPhongHelper.tinhTienPhong_2415053122236(
                    loaiPhongHienTai, don.soDem, don.soKhach
                )
                val intent = android.content.Intent(this, XacNhanActivity::class.java).apply {
                    putExtra("don_dat_phong", don)
                    putExtra("tong_tien", tien)
                }
                startActivity(intent)
            } else {
                Snackbar.make(it, "Vui lòng kiểm tra lại thông tin", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun capNhatTamTinh() {
        val soDem = edtSoDem.text.toString().toIntOrNull() ?: 0
        val soKhach = edtSoKhach.text.toString().toIntOrNull() ?: 0
        val tien = DatPhongHelper.tinhTienPhong_2415053122236(loaiPhongHienTai, soDem, soKhach)
        txtTamTinh.text = "Tạm tính: ${tien.dinhDangTien()}"
    }

    private fun kiemTraHopLe(): Boolean {
        var hopLe = true
        val ten = edtHoTen.text.toString().trim()
        val sdt = edtSdt.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val ngay = edtNgayNhan.text.toString().trim()
        val soDem = edtSoDem.text.toString().toIntOrNull()
        val soKhach = edtSoKhach.text.toString().toIntOrNull()

        if (!KiemTraHelper.kiemTraHoTen(ten)) { layoutTen.error = "Tên không hợp lệ"; hopLe = false } else layoutTen.error = null
        if (!KiemTraHelper.kiemTraSdt(sdt)) { layoutSdt.error = "SĐT sai định dạng"; hopLe = false } else layoutSdt.error = null
        if (!KiemTraHelper.kiemTraEmail(email)) { layoutEmail.error = "Email sai định dạng"; hopLe = false } else layoutEmail.error = null
        if (!KiemTraHelper.kiemTraNgay(ngay)) { layoutNgay.error = "Ngày sai định dạng"; hopLe = false } else layoutNgay.error = null
        if (soDem == null || soDem !in 1..16) { layoutDem.error = "Số đêm 1-16"; hopLe = false } else layoutDem.error = null
        if (soKhach == null || !DatPhongHelper.hopLeSoKhach(loaiPhongHienTai, soKhach)) { layoutKhach.error = "Số khách sai"; hopLe = false } else layoutKhach.error = null

        return hopLe
    }
}