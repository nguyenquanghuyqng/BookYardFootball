package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Adapter.optionyard;
import com.nguyenquanghuy605.bookyardfootball.Adapter.YardAdapter;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.Model.Accounts;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import java.security.acl.Owner;
import java.util.ArrayList;

public class Login extends AppCompatActivity implements FirebaseAuth.AuthStateListener,GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{

    EditText  id_username,id_pass,eTextName,eTextUserName,eTextPass,eTextPhoneUser;
    Button btnLogin,btnSignIn,btnAdd,btnCannel;
    int sizeAccount =0;
    String userEmail="";
    int SignInGoogle;

    SignInButton btnSignInGoogle;
    public FirebaseAuth firebaseAuth;
    GoogleApiClient apiClient;
    String username ="";
    String pass="";
    private long role;
    private long id;
    public static  int CODE_SIGNIN_GOOGLE =99;
    public static int CHECK_PROVIDER_SIGNIN=0;
    private DatabaseReference databaseReferenceAccount;
    private DatabaseReference databaseReferenceOwner;
    private DatabaseReference databaseReferenceYard;
    long idown;
    long yardid;
     ArrayList<Yards> yardArrayList=new ArrayList<Yards>();
     ArrayList<Owners> ownerArrayList=new ArrayList<Owners>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        //firebaseAuth.signOut();
        databaseReferenceAccount = FirebaseDatabase.getInstance().getReference().child("Accounts");
        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference().child("Owners");

        firebaseAuth=FirebaseAuth.getInstance();

        AnhXa();

        btnSignInGoogle.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        CreateClientSignInGoogle();
        btnSignIn.setOnClickListener(this);

    }
    public void AnhXa()
    {
        id_username= (EditText) findViewById(R.id.id_userName);
        id_pass=(EditText) findViewById(R.id.id_pass);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnSignInGoogle=(SignInButton) findViewById(R.id.btnSingInGoogle);
        btnSignIn =(Button) findViewById(R.id.btnSignIn);
    }

    private void CreateClientSignInGoogle()
    {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();
        Log.d("Dang nhap 1","huy");
    }

    private void SignInGoogle(GoogleApiClient apiClient){
        CHECK_PROVIDER_SIGNIN=1;
        Intent signIn = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(signIn,CODE_SIGNIN_GOOGLE);

        Log.d("Dang nhap 3","huy");
    }

    private void CheckSignIn(String tokenID){
        if(CHECK_PROVIDER_SIGNIN==1)
        {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID,null);
            firebaseAuth.signInWithCredential(authCredential);
        }
        Log.d("Dang nhap 5","huy");
    }
    private  void SignInEmail()
    {
        username = id_username.getText().toString();
        userEmail=username;
        pass=id_pass.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(username,pass);
    }
    private void SignIn()
    {
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_signin);
        myDialog.setCancelable(false);
        btnAdd = (Button) myDialog.findViewById(R.id.btnAdd);
        btnCannel =(Button) myDialog.findViewById(R.id.btnCannel) ;

         eTextName= (EditText) myDialog.findViewById(R.id.eTextName);
        eTextPass = (EditText) myDialog.findViewById(R.id.eTextPass);
        eTextPhoneUser = (EditText) myDialog.findViewById(R.id.eTextPhone);
        eTextUserName = (EditText) myDialog.findViewById(R.id.eTextUserName);
        myDialog.show();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferenceAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int i = 0;
                            for (DataSnapshot data1 : dataSnapshot.getChildren()) {

                                i++;
                            }
                            sizeAccount = i;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                try {
                    username = eTextUserName.getText().toString();
                    pass = eTextPass.getText().toString();
                    firebaseAuth.createUserWithEmailAndPassword(username, pass);
                    if (username.indexOf('@') != -1) {

                        if (pass.length() >= 8) {
                            //Accounts account = new Accounts(sizeAccount,eTextName.getText().toString(),eTextPass.getText().toString(),eTextPhoneUser.getText().toString(),3,eTextUserName.getText().toString());
                            //databaseReferenceAccount.child(String.valueOf(sizeAccount-1)).setValue(account);
                            Toast.makeText(Login.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            myDialog.cancel();
                        } else {
                            Toast.makeText(Login.this, "PassWord phải trên 8 ký tự", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch(Exception e){
                        Toast.makeText(Login.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
        });
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.cancel();
//
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODE_SIGNIN_GOOGLE){
            if(resultCode==RESULT_OK)
            {
                GoogleSignInResult signInResult= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account= signInResult.getSignInAccount();

                String tokenID= account.getIdToken();
                CheckSignIn(tokenID);
            }
        }
        Log.d("Dang nhap 4","huy");
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
        firebaseAuth.removeAuthStateListener(this);
    }
    @Override
    public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            String userId = firebaseUser.getUid();
            userEmail = firebaseUser.getEmail().toString();
            Log.d("User dang nhap",userEmail);
            Log.d("Dang nhap 6","huy");
            if(SignInGoogle==1)
            {
                databaseReferenceAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int flag = 0;

                            for (DataSnapshot data1 : dataSnapshot.getChildren()) {
                                Accounts account = data1.getValue(Accounts.class);

                                Log.d("Owners", data1.getValue().toString());

                                String user = account.getUsername().toString();

                                if (user.equals(userEmail)) {
                                    flag=1;
                                }
                            }
                            if(flag==0){
                                databaseReferenceAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            int i = 0;
                                            for (DataSnapshot data1 : dataSnapshot.getChildren()) {

                                                i++;
                                            }
                                            sizeAccount = i;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                try {
                                    pass="";
                                    firebaseAuth.createUserWithEmailAndPassword(username, pass);
                                        Accounts account = new Accounts(sizeAccount, userEmail, "", "", 3, userEmail);
                                        databaseReferenceAccount.child(String.valueOf(sizeAccount - 1)).setValue(account);

                                }
                                    catch (Exception e){
                                        Toast.makeText(Login.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            databaseReferenceAccount.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int i=0;
                        for(DataSnapshot data1 : dataSnapshot.getChildren()){

                            i++;
                            Accounts account = data1.getValue(Accounts.class);

                            Log.d("Owners",data1.getValue().toString());

                            String user= account.getUsername().toString();
                            Log.d("User Name",account.getUsername().toString());
                            Log.d("Email Name",userEmail);
                            Log.d("Email Name",username);
                            Log.d("Email Name",user);
                            if( user.equals(username) || user.equals(userEmail)) {
                                role = account.getRole();
                                id=account.getId();
                                Container.getInstance().star=role;
                                Log.d("role1234",String.valueOf(role));
                                Container.getInstance().id=id;
                                Log.d("idAccount1234",String.valueOf(id));
                            }
                            else{
                                Log.d("check","Không phai tai k này");
                            }
                        }
                        sizeAccount=i-1;
                        //Accounts account = new Accounts(sizeAccount+1,personName,null,null,0,null);
                        //databaseReferenceAccount.child(String.valueOf(sizeAccount)).setValue(account);
                    }else{
                        Log.d("Not have data" ,"Haha");
                    }
                    Log.d(String.valueOf(role),"role");
                    if(role==1){
                        Intent intent= new Intent(Login.this , Account.class);
                        startActivity(intent);
                    }
                    else
                    {
                        if(role ==2)
                        {
                            databaseReferenceYard = FirebaseDatabase.getInstance().getReference().child("Yards");

                            databaseReferenceOwner.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                                    for (DataSnapshot data : nodeChild) {

                                        // Lấy dữ liệu từ firebase xuống đưa vào model
                                        Owners yard_owner = data.getValue(Owners.class);

                                        Log.d("Yard", yard_owner.toString());
                                        Log.d("DataYard", data.getValue().toString());
                                        // Add vào List
                                        long idAccount = yard_owner.getAccount();
                                        Log.d("idAccount", String.valueOf(yard_owner.getAccount()));
                                        if ( idAccount==id) {
                                            Container.getInstance().idOwner=yard_owner.getId()-1;
                                            optionyard.getInstance().idOwner=yard_owner.getId()-1;
                                            idown=yard_owner.getId();
                                            optionyard.getInstance().nameOwnerItem =yard_owner.getName();
                                            optionyard.getInstance().numberYardItem =yard_owner.getNumberyard();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            databaseReferenceYard.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                                    for(DataSnapshot data : nodeChild){

                                        // Lấy dữ liệu từ firebase xuống đưa vào model
                                        Yards yard = data.getValue(Yards.class);

                                        // Add vào List
                                        yardArrayList.add(yard);
                                        if(yard.getOwner()==idown-1)
                                        {
                                            optionyard.getInstance().idyard = yard.getId();
                                            optionyard.getInstance().nameYardItem = yard.getNameyard();
                                            optionyard.getInstance().timestart = yard.getTimestart();
                                            optionyard.getInstance().timeend = yard.getTimeend();
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("Error SignIn",databaseError.getMessage());
                                }
                            });
                            Intent intent = new Intent(Login.this, Myyard.class);
//                Container.getInstance().nameOptionYard = optionYardArrayList.get(position).getName();
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent= new Intent(Login.this , ListAllYard.class);
                            startActivity(intent);
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            Toast.makeText(this,"Dang nhap thanh cong ",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Dang nhap that bai ",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.btnSingInGoogle:
                SignInGoogle=0;
                SignInGoogle(apiClient);
                Log.d("Dang nhap 2 ","huy");
                break;
            case R.id.btnLogin:
                SignInEmail();
                Log.d("Dang nhap 2 ","huy");
                break;

            case R.id.btnSignIn:
                SignIn();
                Log.d("Dang nhap 2 ","huy");
                break;


        }
    }
}
