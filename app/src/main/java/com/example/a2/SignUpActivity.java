package com.example.a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2.util.ToastUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SignUpActivity extends AppCompatActivity {

    EditText mEtFirstName,mEtLastName,mEtEmail,mEtPassword,mEtRePassword;
    Button mBtnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mEtFirstName=findViewById(R.id.firstname_Input);
        mEtLastName=findViewById(R.id.lastname_input);
        mEtEmail=findViewById(R.id.email_Input);
        mEtPassword=findViewById(R.id.password_Input);
        mEtRePassword=findViewById(R.id.re_password_Input);
        mBtnSignUp = findViewById(R.id.btn_signIn);
        mAuth = FirebaseAuth.getInstance();

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();

            }
        });
    }

    private void createAccount() {

        String firstName = mEtFirstName.getText().toString();
        String lastName = mEtLastName.getText().toString();
        String email = mEtEmail.getText().toString();
        String pwd = mEtPassword.getText().toString();
        String rePwd = mEtRePassword.getText().toString();
        if (TextUtils.isEmpty(firstName)){
            Toast.makeText(this, "Empty firstname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lastName)){
            Toast.makeText(this, "Empty lastname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Empty email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "Empty password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(rePwd)){
            Toast.makeText(this, "Please re-enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(rePwd)){
            Toast.makeText(this, "Please re-enter your password correctly", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                            UserModel userModel = new UserModel();
                            userModel.setEmail(email);
                            userModel.setPassword(pwd);
                            userModel.setFirstName(firstName);
                            userModel.setLastName(lastName);
                            userModel.setUid(uid);
                            FirebaseDatabase.getInstance().getReference().child("User").child(uid).setValue(userModel);
                            finish();
                            Toast.makeText(SignUpActivity.this,"Sign up successfully", Toast.LENGTH_LONG);
                            Intent intent =new Intent(SignUpActivity.this,SignInActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(SignUpActivity.this,"Sign up failed! Check Email or Password", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }


}

