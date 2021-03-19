package com.example.googleapilogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    Button logout;
    ImageView iv;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv1=findViewById(R.id.name);
        tv2=findViewById(R.id.email);
        tv3=findViewById(R.id.userId);
        logout=findViewById(R.id.logoutBtn);
        iv=findViewById(R.id.profileImage);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singOut();
            }
        });







        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String pname = acct.getDisplayName();
            String pem = acct.getEmail();
            String pid = acct.getId();
            Uri ppic = acct.getPhotoUrl();
            tv1.setText("Name:"+pname);
            tv2.setText("Email:"+pem);
            tv3.setText("Id:"+pid);
            Glide.with(this).load(String.valueOf(ppic)).into(iv);




        }




    }

    private void singOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Login.this, "SignOut", Toast.LENGTH_SHORT).show();
                        // ...
                        finish();
                    }
                });
    }

}