package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.AccountAdapter;
import com.nguyenquanghuy605.bookyardfootball.Model.Accounts;
import com.nguyenquanghuy605.bookyardfootball.R;
import java.util.ArrayList;

public class Account extends AppCompatActivity {

    ListView lvUser;
    ArrayList<Accounts> userArrayList;
    AccountAdapter accountAdapter;
    EditText eText_Accoutn_UserName;
    EditText eText_account_pass;
    EditText eText_account_name;
    EditText eText_account_phone;
    EditText eText_accountRole;
    Button addAcconut;
    Button btnAdd;
    Button btnCannel;
    Button btnDeleteAccount;
    long sizeList;

    FirebaseDatabase firebaseDatabase;
     DatabaseReference databaseReferenceAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_yard_owner_account);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceAccount=firebaseDatabase.getReference();
        AnhXa();

        Initialize();

//        addAcconut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callDialog();
//            }
//        });
        /*btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = userArrayList.indexOf(btnDeleteAccount);
                databaseReferenceAccount.child(String.valueOf(index));
                databaseReferenceAccount.setValue(null);
            }
        });*/


    }
    private void AnhXa(){
        lvUser = (ListView) findViewById(R.id.listviewAccount);
        addAcconut = (Button) findViewById(R.id.addAcconut);
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
    }

    private void Initialize() {
        databaseReferenceAccount = FirebaseDatabase.getInstance().getReference().child("Accounts");

        userArrayList = new ArrayList<>();
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

       /* Query query = databaseReferenceOwner.orderByChild("id");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data1 : dataSnapshot.getChildren()) {
                        Owners owners = data1.getValue(Owners.class);

                        Log.d("Owners", data1.getValue().toString());

                        ownerArrayList.add(owners);

                        yardAdapter.notifyDataSetChanged();
                    }
                }
            }*/

            /*@Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error Owner", databaseError.getMessage());
            }
        });*/
    }
//    private void callDialog()
//    {
//        final Dialog myDialog = new Dialog(this);
//        myDialog.setContentView(R.layout.layout_dialog_insert_account);
//        myDialog.setCancelable(false);
//        btnAdd = (Button) myDialog.findViewById(R.id.btnAdd);
//        btnCannel =(Button) myDialog.findViewById(R.id.btnCannel) ;
//
//        eText_account_name = (EditText) myDialog.findViewById(R.id.eText_account_name);
//        eText_account_pass = (EditText) myDialog.findViewById(R.id.eText_account_pass);
//        eText_account_phone = (EditText) myDialog.findViewById(R.id.eText_account_phone);
//        eText_accountRole = (EditText) myDialog.findViewById(R.id.eText_accountRole);
//        eText_Accoutn_UserName = (EditText) myDialog.findViewById(R.id.eText_Accoutn_UserName);
//        myDialog.show();
//
//
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Accounts account = new Accounts(sizeList,eText_account_name.getText().toString(),eText_account_pass.getText().toString(),eText_account_phone.getText().toString(),Long.parseLong(eText_accountRole.getText().toString()),eText_Accoutn_UserName.getText().toString());
//                databaseReferenceAccount.child(String.valueOf(sizeList+1)).setValue(account);
//                Initialize();
//                myDialog.cancel();
//            }
//        });
//        btnCannel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                myDialog.cancel();
//                Initialize();
//            }
//        });
//
//    }
}
