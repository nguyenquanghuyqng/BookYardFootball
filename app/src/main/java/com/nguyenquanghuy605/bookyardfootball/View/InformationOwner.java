package com.nguyenquanghuy605.bookyardfootball.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquanghuy605.bookyardfootball.Adapter.Container;
import com.nguyenquanghuy605.bookyardfootball.Model.Accounts;
import com.nguyenquanghuy605.bookyardfootball.Model.Yard_Owner;
import com.nguyenquanghuy605.bookyardfootball.R;

public class InformationOwner extends AppCompatActivity implements ValueEventListener {

    String idOwner;
    EditText eTextNumberYard,eTextStar,eTextName,eTextPhone,eTextIdCard,eTextAddress;
    Button btnDeleteOwner;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReferenceOwner;
    DatabaseReference databaseReferenceYard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_information_yard_owner);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceOwner=firebaseDatabase.getReference();
        databaseReferenceYard=firebaseDatabase.getReference();

        AnhXa();

        idOwner=String.valueOf(Container.getInstance().idOwner);
        Initialize(idOwner);

        databaseReferenceOwner.addValueEventListener(this);

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
    }

    private void Initialize(String id) {

        databaseReferenceOwner = FirebaseDatabase.getInstance().getReference().child("Owners").child(id);
        databaseReferenceYard = FirebaseDatabase.getInstance().getReference().child("Yards");
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Yard_Owner yard_owner= dataSnapshot.getValue(Yard_Owner.class);

        eTextNumberYard.setText(yard_owner.getNumberyard());
        eTextName.setText(yard_owner.getName());
        eTextIdCard.setText(yard_owner.getIdcard());
        eTextPhone.setText(yard_owner.getPhone());
        eTextAddress.setText(yard_owner.getAddress());
        eTextStar.setText(String.valueOf(Container.getInstance().star));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}