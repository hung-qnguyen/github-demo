package deso2.doandinhhoang.appcaphe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangNhapActivity extends AppCompatActivity {
    TextView txtresetmatkhau;
    Button btn_signup,btnDangnhap;
    EditText edt_email,edt_matkhau;
    FirebaseAuth mAuthencation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        anhxa();
        mAuthencation = FirebaseAuth.getInstance();
    }

    private void DangNhap(){
        if (TextUtils.isEmpty(edt_email.getText().toString())){
            edt_email.setError("This field is required");
            return;
        }
        if (TextUtils.isEmpty(edt_matkhau.getText().toString())){
            edt_matkhau.setError("This field is required");
            return;
        }
        String email = edt_email.getText().toString();
        String password = edt_matkhau.getText().toString();
        mAuthencation.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DangNhapActivity.this, "Đăng Nhập Thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                        }else {
                            Toast.makeText(DangNhapActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }


    private void anhxa() {

        edt_email = findViewById(R.id.DN_email);
        edt_matkhau = findViewById(R.id.DN_matkhauemail);
        btnDangnhap = findViewById(R.id.DN_dangnhap);
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();
            }
        });


        btn_signup = findViewById(R.id.btn_Signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
            }
        });

        txtresetmatkhau = findViewById(R.id.DN_resetmatkhau);
        txtresetmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, KhoiPhucMatKhauActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
            }
        });
    }
}
