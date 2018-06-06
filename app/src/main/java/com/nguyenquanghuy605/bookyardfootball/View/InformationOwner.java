package com.nguyenquanghuy605.bookyardfootball.View;

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
    int flag;
    long idOwner;
    ArrayList<Yard_Owner> ownersArrayList;
    long role;
    EditText eTextNumberYard,eTextStar,eTextName,eTextPhone,eTextIdCard,eTextAddress;
    Button btnDeleteOwner;
    TextView txtyardName;

    DatabaseReference databaseReferenceOwner;
    DatabaseReference databaseReferenceYard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_information_yard_owner);

        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference().child("Owners");
        databaseReferenceYard = FirebaseDatabase.getInstance().getReference().child("Yards");

        AnhXa();

        //role = Container.getInstance().star;
        //idAccount = Container.getInstance().id;
        role=2;
        idAccount=1;
        if(role==2) {

            flag=1;
            //InforYard();
            Initialize();

        }
        else
        {
            if(role==3)
            {
                //InforYard();
                Initialize();
            }

        }


        //databaseReferenceOwner.addValueEventListener(this);

        btnDeleteOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferenceOwner.removeValue();
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
    }

    private void Initialize( ) {



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
                    long id = yard_owner.getAccount();
                    Log.d("idAccount", String.valueOf(yard_owner.getAccount()));
                    if (id == idAccount) {
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
}