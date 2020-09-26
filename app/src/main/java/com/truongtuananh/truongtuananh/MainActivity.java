package com.truongtuananh.truongtuananh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database db;

    ListView lvMonHoc;
    ArrayList<MonHoc> arrayMonHoc;
    MonHocAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMonHoc = (ListView) findViewById(R.id.listviewMonHoc);
        arrayMonHoc = new ArrayList<>();

        adapter = new MonHocAdapter(this,R.layout.activity_list_view,arrayMonHoc);
        lvMonHoc.setAdapter(adapter);

        db = new Database(this,"GhiChu.sqlite",null,1);
////, TenSV VARCHAR(50), MaSoSV VARCHAR(50), SoTC VARCHAR(50), LopHocSV VARCHAR(50)
        db.QueryData("CREATE TABLE IF NOT EXISTS MonHoc(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenMH VARCHAR(50), TenSV VARCHAR(50), MaSoSV VARCHAR(50), SoTC VARCHAR(50), LopHocSV VARCHAR(50))");
//
//        db.QueryData("INSERT INTO MonHoc VALUES(null,'Môn ABC','Môn Lý','Môn Hoá','Môn Văn','Môn Anh')");
        //select data lấy dữ liệu ra
        GetDataMonHoc();
    }
//
    public void  DialogXoaMonHoc(final String tenMH, final int id){
        AlertDialog.Builder dialodXoa = new AlertDialog.Builder(this);
        dialodXoa.setMessage("Bạn có muốn xoá  môn học "+ tenMH +" này  không");
        dialodXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.QueryData("DELETE FROM MonHoc WHERE Id = '"+id+"'");
                Toast.makeText(MainActivity.this, "Đã xoá " +tenMH, Toast.LENGTH_SHORT).show();
                GetDataMonHoc();
            }
        });
        dialodXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialodXoa.show();
    }
    public void DialogSuaMonHoc(String tenMH, String tenSV, String MaSoSV, String SoTC, String LopHocSV , final int id){
       final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_mon_hoc);
//
        final EditText edtTenMH = (EditText) dialog.findViewById(R.id.editTextTenMHEdit);
        final EditText edtTenSV = (EditText) dialog.findViewById(R.id.editTextTenSVEdit);
        final EditText edtMaSoSV = (EditText) dialog.findViewById(R.id.editTextMaSoSVEdit);
        final EditText edtSoTC = (EditText) dialog.findViewById(R.id.editTextSoTCEdit);
        final EditText edtLopHocSV = (EditText) dialog.findViewById(R.id.editTextLopHocSVHEdit);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.btnXanNhan);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuyEdit);
//
        edtTenMH.setText(tenMH);
        edtTenSV.setText(tenSV);
        edtMaSoSV.setText(MaSoSV);
        edtSoTC.setText(SoTC);
        edtLopHocSV.setText(LopHocSV);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoiMH = edtTenMH.getText().toString().trim();
                String tenMoiSV = edtTenSV.getText().toString().trim();
                String maSoSVMoi = edtMaSoSV.getText().toString().trim();
                String soTCMoi = edtSoTC.getText().toString().trim();
                String lopHocSVMoi = edtLopHocSV.getText().toString().trim();
                db.QueryData("UPDATE MonHoc SET TenMH  ="+tenMoiMH+"  WHERE Id = "+id+"");
                db.QueryData("UPDATE MonHoc SET TenSV  ="+tenMoiSV+"  WHERE Id = "+id+"");
                db.QueryData("UPDATE MonHoc SET MaSoSV  ="+maSoSVMoi+"  WHERE Id = "+id+"");
                db.QueryData("UPDATE MonHoc SET SoTC  ="+soTCMoi+"  WHERE Id = "+id+"");
                db.QueryData("UPDATE MonHoc SET LopHocSV  ="+lopHocSVMoi+"  WHERE Id = "+id+"");
                Toast.makeText(MainActivity.this, "Đã Cập Nhật", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataMonHoc();
            }
        });
////
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void GetDataMonHoc(){
        Cursor dataMonHoc = db.GetData("SELECT * FROM MonHoc");
        arrayMonHoc.clear();
        while (dataMonHoc.moveToNext()){
            String tenMH = dataMonHoc.getString(1);
            String tenSV = dataMonHoc.getString(2);
            String masoSV = dataMonHoc.getString(3);
            String soTC = dataMonHoc.getString(4);
            String lophocSV = dataMonHoc.getString(5);
            int id = dataMonHoc.getInt(0);
            arrayMonHoc.add(new MonHoc(id,tenMH,tenSV,masoSV,soTC,lophocSV));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_monhoc,menu);
        return super.onCreateOptionsMenu(menu);
    }
//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }
//
    private void DialogThem(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_mon_hoc);

        final EditText edtTenMH = (EditText) dialog.findViewById(R.id.editTextTenMHThem);
        final EditText edtTenSV = (EditText) dialog.findViewById(R.id.editTextTenSVThem);
        final EditText edtMaSoSV = (EditText) dialog.findViewById(R.id.editTextMaSoSVThem);
        final EditText edtSoTC = (EditText) dialog.findViewById(R.id.editTextSoTCThem);
        final EditText edtLopHocSV = (EditText) dialog.findViewById(R.id.editTextLopHocSV);
        Button btnThem = (Button) dialog.findViewById(R.id.btnThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmh = edtTenMH.getText().toString();
                String tensv = edtTenSV.getText().toString();
                String masosinhvien = edtMaSoSV.getText().toString();
                String sotinchi = edtSoTC.getText().toString();
                String lophocsinhvien = edtLopHocSV.getText().toString();
                if (tenmh.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên môn học", Toast.LENGTH_SHORT).show();
                }
                else if(tensv.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên sinh viên", Toast.LENGTH_SHORT).show();
                }
                else if(masosinhvien.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập mã số sinh viên", Toast.LENGTH_SHORT).show();
                }
                else if(sotinchi.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số tín chỉ", Toast.LENGTH_SHORT).show();
                }
                else if(lophocsinhvien.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Lớp học sinh viên", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(MainActivity.this, "thanhcong", Toast.LENGTH_SHORT).show();
                    db.QueryData("INSERT INTO MonHoc VALUES(null,'"+tenmh+"','"+tensv+"','"+masosinhvien+"','"+sotinchi+"','"+lophocsinhvien+"')");
                    Toast.makeText(MainActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataMonHoc();
                }
            }
        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}