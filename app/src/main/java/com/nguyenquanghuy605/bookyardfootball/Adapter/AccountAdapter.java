package com.nguyenquanghuy605.bookyardfootball.Adapter;

        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.TextView;

        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;
        import com.nguyenquanghuy605.bookyardfootball.Model.Accounts;
        import com.nguyenquanghuy605.bookyardfootball.R;

        import java.util.List;

public class AccountAdapter  extends BaseAdapter {


    private Context context;
    private int layout;
    private List<Accounts> usersList;

    // Create a storage reference from our app
    //FirebaseStorage storage1 = FirebaseStorage.getInstance();
    //StorageReference storageRef = storage1.getReference();
    // Biến image
    //private StorageReference imageRef;

    public AccountAdapter(Context context, int layout, List<Accounts> usersList ) {
        this.context = context;
        this.layout = layout;
        this.usersList = usersList;

    }

    // Trả về số dòng trong listview
    @Override
    public int getCount() {
        return usersList.size();     // trả về tất cả có trong list
    }

    // Trả về đối tượng trong list
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    // Có bao nhiêu biến ánh xạ thì khai báo trong class này
    // Sử dụng viewHolder để tối ưu listview
    private class ViewHolder{
        TextView txtYardNameAccount, txtUserNameYard, txtEmailYardOwner;
        Button btnAddAcount;
    }

    // Trả về mỗi dòng trên Item
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        // Thường thì lần đầu khởi tạo chạy thì biến view sẽ bằng null
        if(view == null){
            //
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.layout_item_yard_owner_account,null);

            holder = new ViewHolder();

            // Ánh xạ view
            holder.txtYardNameAccount = (TextView) view.findViewById(R.id.txtYardNameAccount);
            holder.txtUserNameYard = (TextView) view.findViewById(R.id.txtUserNameYard);
            holder.txtEmailYardOwner = (TextView) view.findViewById(R.id.txtEmailYardOwner);
            holder.btnAddAcount = (Button) view.findViewById(R.id.btnAddAcount);

            // Set tag cho View
            view.setTag(holder);

        }
        else{
            // Lấy phần ánh xạ lại thôi
            holder = (ViewHolder) view.getTag();
        }
        // gán giá trị
        Accounts user = usersList.get(i);
        Log.d("Gia  tri i ",i+"");

//        StorageReference islandRef = storageRef.child("images/island.jpg");


//        final long ONE_MEGABYTE = 1024 * 1024;
//        imageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Log.d("Error ", exception.getMessage());
//            }
//        });

        /*final long ONE_MEGABYTE = 1024 * 1024;
        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Log.d("OnSuccess ",imageRef.toString());
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.imgYard.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("Error ", exception.getMessage());
            }
        });*/

        holder.txtYardNameAccount.setText(user.getName());
        holder.txtUserNameYard.setText(user.getUsername());
        holder.txtEmailYardOwner.setText(user.getEmail());
//        holder.imgYard.setImageBitmap();

        // Trước khi return thì gán animation cho view
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);

        return view;
    }
}
