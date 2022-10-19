package com.example.a2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2.util.ToastUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private Button mBtnSignIn;
    private Button mBtnForgetPassword;
    private Button mBtnSignUp;
    private Button mBtnVisitor;

    private EditText mEtEmail;
    private EditText mEtPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mBtnSignIn = findViewById(R.id.btn_signIn);
        mBtnSignUp = findViewById(R.id.btn_signUp);
        mBtnForgetPassword = findViewById(R.id.btn_forgetPassword);
        mBtnVisitor = findViewById(R.id.btn_visitor);
        mAuth = FirebaseAuth.getInstance();
        mEtEmail = findViewById(R.id.email_Input);
        mEtPassword = findViewById(R.id.password_Input);

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(SignInActivity.this,"test",Toast.LENGTH_LONG).show();
                validateSignIn(view);
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        mBtnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        //1
        mBtnVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    //1
    private void validateSignIn(View v){
        String userEmail = mEtEmail.getText().toString();
        String userPassword = mEtPassword.getText().toString();
        String successSignIn = "Welcome to Travel";
        String failSignIn = "Password or Email is wrong, please re-enter";
        Intent intent = null;

        if(TextUtils.isEmpty(userEmail)||TextUtils.isEmpty(userPassword)){
            Toast.makeText(SignInActivity.this,"Please fill in your email and password correctly", Toast.LENGTH_LONG);
            return;
        }

        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(SignInActivity.this,"Sign in Success. Welcome",Toast.LENGTH_LONG).show();
                            Intent  intent =new Intent(SignInActivity.this,HomeActivity.class);
                            startActivity(intent);
                           finish();
                        } else {
                            Toast.makeText(SignInActivity.this, "Sign in Fail.Please check your Email or password.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}