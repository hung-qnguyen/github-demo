package deso2.doandinhhoang.appcaphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {
    Button bntxacnhan;
    EditText edtemail, edtmatkhau, edtname, edtphone, edtxnmatkhau;
    FirebaseAuth mAuthencation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        mAuthencation = FirebaseAuth.getInstance();
        anhxa();

//        bntxacnhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DangKY();
//            }
//        });
    }


    private void DangKY() {
        String email = edtemail.getText().toString();
        String password = edtmatkhau.getText().toString();
        mAuthencation.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DangKyActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

                        } else {
                            Toast.makeText(DangKyActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    private void anhxa() {

        bntxacnhan = findViewById(R.id.DK_xacnhan);
        edtemail = findViewById(R.id.DK_email);
        edtname = findViewById(R.id.DK_name);
        edtphone = findViewById(R.id.DK_phone);
        edtxnmatkhau = findViewById(R.id.DK_xnmkemail);
        edtmatkhau = findViewById(R.id.DK_mkemail);

        bntxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtname.getText().toString())){
                    edtname.setError("This field is required");
                    return;
                }
                if (TextUtils.isEmpty(edtemail.getText().toString())){
                    edtemail.setError("This field is required");
                    return;
                }
                if (TextUtils.isEmpty(edtphone.getText().toString())){
                    edtphone.setError("This field is required");
                    return;
                }
                if (TextUtils.isEmpty(edtmatkhau.getText().toString())){
                    edtmatkhau.setError("This field is required");
                    return;
                }
                if (TextUtils.isEmpty(edtxnmatkhau.getText().toString())){
                    edtxnmatkhau.setError("This field is required");
                    return;
                }
                if(edtmatkhau.getText().toString().equals(edtxnmatkhau.getText().toString())){
                    DangKY();
                }else {
                    Toast.makeText(DangKyActivity.this, "Nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}