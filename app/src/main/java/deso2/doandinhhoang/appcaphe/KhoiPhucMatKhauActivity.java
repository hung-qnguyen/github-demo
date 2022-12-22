package deso2.doandinhhoang.appcaphe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class KhoiPhucMatKhauActivity extends AppCompatActivity {
    EditText edtKPMKemail;
    Button bntKPMKxacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoi_phuc_mat_khau);
        anhxa();
    }

    private void anhxa() {
        edtKPMKemail = findViewById(R.id.KPMK_email);
        bntKPMKxacnhan = findViewById(R.id.KPMK_xacnhan);
        bntKPMKxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KhoiPhuc();
            }
        });
    }

    private void KhoiPhuc(){
        if (TextUtils.isEmpty(edtKPMKemail.getText().toString())){
            edtKPMKemail.setError("This field is required");
            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = edtKPMKemail.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(KhoiPhucMatKhauActivity.this, "Please Check Your Mail", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(KhoiPhucMatKhauActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }
}