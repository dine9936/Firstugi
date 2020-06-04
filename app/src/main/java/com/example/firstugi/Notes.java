package com.example.firstugi;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstugi.Models.NotesMdll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class Notes extends Fragment {
private RecyclerView recyclerViewNotes;
private DatabaseReference reference;
private FirebaseStorage storage;
private StorageReference storageReference,ref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("Notes");



        recyclerViewNotes = (RecyclerView)view.findViewById(R.id.notes_recycler);
        recyclerViewNotes.setHasFixedSize(true);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<NotesMdll,NotestViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<NotesMdll, NotestViewHolder>(
                NotesMdll.class,
                R.layout.notes_card,
                NotestViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(NotestViewHolder viewHolder, NotesMdll model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(model.getImage(),getContext());
                viewHolder.setInfo(model.getInfo());
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "downloading...", Toast.LENGTH_SHORT).show();

                        download();
                    }
                });

            }
        };
        recyclerViewNotes.setAdapter(firebaseRecyclerAdapter);
    }

    public static class NotestViewHolder extends RecyclerView.ViewHolder{
        View mView;
        Button button;
        public NotestViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            button = (Button)mView.findViewById(R.id.download);
        }


        public void setName (String name){
            TextView post_desc = mView.findViewById(R.id.teacher_name);
            post_desc.setText(name);
        }
        public void setInfo (String info){
            TextView post_desc = mView.findViewById(R.id.teacher_info);
            post_desc.setText(info);
        }
        public void setImage (String image, Context context){
            ImageView post_desc = mView.findViewById(R.id.teacher_image);
            Picasso.with(context).load(image).into(post_desc);
        }


    }
    public void download(){
        storageReference = FirebaseStorage.getInstance().getReference();

        ref = storageReference.child("arduino.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                String urll = uri.toString();
                downloadFile(getContext(),"Mobile",".pdf", ContactsContract.Directory.DIRECTORY_AUTHORITY,urll);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }
    public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

         downloadmanager.enqueue(request);
    }
}
