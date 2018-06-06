package com.nguyenquanghuy605.bookyardfootball.View;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Model.Accounts;
import com.nguyenquanghuy605.bookyardfootball.Model.Owners;
import com.nguyenquanghuy605.bookyardfootball.Model.Yard_Owner;
import com.nguyenquanghuy605.bookyardfootball.Model.Yards;
import com.nguyenquanghuy605.bookyardfootball.R;

import java.security.acl.Owner;
import java.util.ArrayList;

public class InformationOwner extends AppCompatActivity  {

    long idAccount;
    int nodeOwner,nodeYard;
    long nodeAccount;
    int flag;
    long idOwner;
    ArrayList<Yard_Owner> ownersArrayList;
    long role;
    EditText eTextNumberYard,eTextStar,eTextName,eTextPhone,eTextIdCard,eTextAddress,eText_YardOwner_Address;
    EditText eText_YardOwner_NameYard,eText_YardOwner_Name,eText_YardOwner_phone,eText_YardOwner_IdCard,eText_YardOwner_Yards;
    Button btnDeleteOwner;
    TextView txtyardName;
    Button btnEdit,btnAdd,btnCannel;

    DatabaseReference databaseReferenceAccount;
    DatabaseReference databaseReferenceOwner;
    DatabaseReference databaseReferenceYard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_information_yard_owner);

        databaseReferenceAccount = FirebaseDatabase.getInstance().getReference().child("Accounts");
        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference().child("Owners");
        databaseReferenceYard = FirebaseDatabase.getInstance().getReference().child("Yards");

        AnhXa();

        //role = Container.getInstance().star;
        //idAccount = Container.getInstance().id;
        nodeAccount=Container.getInstance().accountid;
        role=Container.getInstance().star;
        idAccount=Container.getInstance().id;
        if(role==2) {

            flag=1;
            btnEdit.setBackgroundResource(R.drawable.ic_edit);
            //InforYard();
            Initialize();

        }
        else
        {
            if(role ==1)
            {
                flag=1;
                btnEdit.setBackgroundResource(R.drawable.ic_delete_account);
                Initialize();
            }
            else {
                if (role == 3) {
                    //InforYard();
                    btnEdit.setBackgroundResource(R.drawable.ic_delete_account);
                    Initialize();
                }
            }

        }


        //databaseReferenceOwner.addValueEventListener(this);

        btnDeleteOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferenceOwner.removeValue();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(role==1 || role == 3) {

                    flag=1;
                    databaseReferenceOwner.child(String.valueOf(nodeOwner)).removeValue();
                    databaseReferenceAccount.child(String.valueOf(nodeAccount)).removeValue();
                    databaseReferenceYard.child(String.valueOf(nodeYard)).removeValue();

                }
                else
                {
                    if(role ==2)
                    {

                    }
                }
            }
        });
    }
    public void AnhXa(){
        eTextNumberYard = (EditText) findViewById(R.id.eTextNumberYard);
        eTextStar = (EditText) findViewById(R.id.eTextStar);
        eTextName = (EditText) findViewById(R.id.eTextName);
        eTextPhone = (EditText) findViewById(R.id.eTextPhone);
        eTextIdCard = (EditText) findViewById(R.id.eTextIdCard);
        eTextAddress = (EditText) findViewById(R.id.eTextAddress);
        btnDeleteOwner=(Button) findViewById(R.id.btnDeleteOwner);
        txtyardName=(TextView) findViewById(R.id.txtyardName);
        btnEdit =(Button) findViewById(R.id.btnEdit);
        eText_YardOwner_Address =(EditText) findViewById(R.id.eText_YardOwner_Address);
        eText_YardOwner_Name =(EditText) findViewById(R.id.eText_YardOwner_Name);
        eText_YardOwner_NameYard=(EditText) findViewById(R.id.eText_YardOwner_NameYard);
        eText_YardOwner_IdCard=(EditText) findViewById(R.id.eText_YardOwner_IdCard);
        eText_YardOwner_phone =(EditText) findViewById(R.id.eText_YardOwner_phone);
        eText_YardOwner_Yards =(EditText) findViewById(R.id.eText_YardOwner_Yards);
    }


    private void Initialize( ) {



        databaseReferenceOwner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                int i=0;
                for (DataSnapshot data : nodeChild) {
                    i++;
                    // Lấy dữ liệu từ firebase xuống đưa vào model
                    Owners yard_owner = data.getValue(Owners.class);

                    Log.d("Yard", yard_owner.toString());
                    Log.d("DataYard", data.getValue().toString());
                    // Add vào List
                    long id = yard_owner.getAccount();
                    Log.d("idAccount", String.valueOf(yard_owner.getAccount()));
                    if (id == idAccount) {
                        nodeOwner=i-1;
                        eTextName.setText(yard_owner.getName());
                        eTextIdCard.setText(yard_owner.getIdcard());
                        eTextPhone.setText(yard_owner.getPhone());
                        eTextAddress.setText(yard_owner.getAddress());
                        eTextNumberYard.setText(yard_owner.getNumberyard());
                        idOwner = yard_owner.getId();
                        Log.d("idOwner123", String.valueOf(idOwner));

                        if( flag==1) {
                            databaseReferenceYard.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();
                                    int j=0;
                                    for (DataSnapshot data : nodeChild) {
                                        Yards yards = data.getValue(Yards.class);
                                        j++;
                                        long idYard= yards.getOwner();
                                        Log.d("idOwner", String.valueOf(idYard));
                                        if(idYard==idOwner)
                                        {
                                            nodeYard=j-1;
                                            txtyardName.setText(yards.getNameyard());
                                            eTextStar.setText(String.valueOf(yards.getStar()));
                                            break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        else
                        {
                            eTextNumberYard.setVisibility(View.GONE);
                            eTextStar.setVisibility(View.GONE);;
                        }

                        break;
                    }

                }

                //Log.d("Size", String.valueOf(sizeList));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void InforYard(){
        if( flag==1) {
            databaseReferenceYard.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> nodeChild = dataSnapshot.getChildren();

                    for (DataSnapshot data : nodeChild) {
                        Yards yards = data.getValue(Yards.class);

                        long idYard= yards.getOwner();
                        Log.d("idOwner", String.valueOf(idYard));
                        if(idYard==idOwner)
                        {
                            txtyardName.setText(yards.getNameyard());
                            eTextStar.setText(String.valueOf(yards.getStar()));
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else
        {
            eTextNumberYard.setVisibility(View.GONE);
            eTextStar.setVisibility(View.GONE);;
        }


    }

    private void callDialog()
    {
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.layout_dialog_update_information_yardowner);
        myDialog.setCancelable(false);
        btnAdd = (Button) myDialog.findViewById(R.id.btnAdd);
        btnCannel =(Button) myDialog.findViewById(R.id.btnCannel) ;

        eText_YardOwner_Name.setText(eTextName.getText().toString());
        eText_YardOwner_Yards.setText(eTextNumberYard.getText().toString());
        eText_YardOwner_Address.setText(eTextAddress.getText().toString());
        eText_YardOwner_phone.setText(eTextPhone.getText().toString());
        eText_YardOwner_IdCard.setText(eTextIdCard.getText().toString());
        eText_YardOwner_NameYard.setText(txtyardName.getText().toString());
        myDialog.show();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //username=eText_Accoutn_UserName.getText().toString();
                //pass=eText_account_pass.getText().toString();
                //Accounts account = new Accounts(sizeList+1,eText_account_name.getText().toString(),eText_account_pass.getText().toString(),eText_account_phone.getText().toString(),Long.parseLong(eText_accountRole.getText().toString()),eText_Accoutn_UserName.getText().toString());
                //databaseReferenceAccount.child(String.valueOf(sizeList)).setValue(account);
                //firebaseAuth.createUserWithEmailAndPassword(username,pass);
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
}