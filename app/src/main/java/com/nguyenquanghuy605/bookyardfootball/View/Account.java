package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.Dialog;
import android.content.Intent;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.AccountAdapter;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Model.Accounts;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;
import java.util.ArrayList;


public class Account extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    ListView lvUser;
    ArrayList<Accounts> userArrayList= new ArrayList<Accounts>();

    AccountAdapter accountAdapter;
    EditText eText_Accoutn_UserName;
    EditText eText_account_pass;
    EditText eText_account_name;
    EditText eText_account_phone;
    EditText eText_accountRole;
    Button addAcconut;
    Button btnAdd;
    Button btnCannel;
    Button btnBack;
    long sizeList;
    String username , pass;
    long idUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceAccount;
    private  FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_yard_owner_account);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceAccount=firebaseDatabase.getReference();
        AnhXa();

        Initialize();

        firebaseAuth = FirebaseAuth.getInstance();
        addAcconut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialog();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
    }

    private void AnhXa(){
        lvUser = (ListView) findViewById(R.id.listviewAccount);
        addAcconut = (Button) findViewById(R.id.addAcconut);
        btnBack=(Button) findViewById(R.id.btnBack);
    }

    private void Initialize() {
        databaseReferenceAccount = FirebaseDatabase.getInstance().getReference().child("Accounts");

        accountAdapter = new AccountAdapter(this, R.layout.layout_item_yard_owner_account, userArrayList);
        lvUser.setAdapter(accountAdapter);


        databaseReferenceAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                for (DataSnapshot data : nodeChild) {

                    // Lấy dữ liệu từ firebase xuống đưa vào model
                    Accounts user = data.getValue(Accounts.class);

                    Log.d("Yard", user.toString());
                    Log.d("DataYard", data.getValue().toString());
                    // Add vào List
                    userArrayList.add(user);

                    accountAdapter.notifyDataSetChanged();
                }
                sizeList=userArrayList.size();
                Log.d("Size", String.valueOf(sizeList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Yard", databaseError.getMessage());
            }
        });
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.d("Click","Vao");
                Container.getInstance().id=userArrayList.get(position).getId();
                Container.getInstance().star =userArrayList.get(position).getRole();
                Container.getInstance().accountid=position;
                Log.d("Owner",String.valueOf(Container.getInstance().id));
                Intent intent = new Intent(Account.this, InformationOwner.class);

                startActivity(intent);

                //Toast.makeText(MainActivity.this, listValue[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void callDialog()
    {
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.layout_dialog_insert_account);
        myDialog.setCancelable(false);
        btnAdd = (Button) myDialog.findViewById(R.id.btnAdd);
        btnCannel =(Button) myDialog.findViewById(R.id.btnCannel) ;

        eText_account_name = (EditText) myDialog.findViewById(R.id.eText_account_name);
        eText_account_pass = (EditText) myDialog.findViewById(R.id.eText_account_pass);
        eText_account_phone = (EditText) myDialog.findViewById(R.id.eText_account_phone);
        eText_accountRole = (EditText) myDialog.findViewById(R.id.eText_accountRole);
        eText_Accoutn_UserName = (EditText) myDialog.findViewById(R.id.eText_Accoutn_UserName);
        myDialog.show();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=eText_Accoutn_UserName.getText().toString();
                pass=eText_account_pass.getText().toString();
                Accounts account = new Accounts(sizeList+1,eText_account_name.getText().toString(),eText_account_pass.getText().toString(),eText_account_phone.getText().toString(),Long.parseLong(eText_accountRole.getText().toString()),eText_Accoutn_UserName.getText().toString());
                databaseReferenceAccount.child(String.valueOf(sizeList)).setValue(account);
                firebaseAuth.createUserWithEmailAndPassword(username,pass);
                myDialog.cancel();
            }
        });
        btnCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.cancel();
            }
        });

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }
}
