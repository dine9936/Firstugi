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
import android.widget.TextView;

import com.example.firstugi.Models.OtherMdll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class Others extends Fragment {
private RecyclerView recyclerViewOther;
private DatabaseReference referenceOther;
private StorageReference storageReference,ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_others, container, false);

        referenceOther = FirebaseDatabase.getInstance().getReference().child("Question");
        recyclerViewOther = (RecyclerView)view.findViewById(R.id.other_recycler);
        recyclerViewOther.setHasFixedSize(true);
        recyclerViewOther.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<OtherMdll,OtherViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OtherMdll, OtherViewHolder>(
                OtherMdll.class,
                R.layout.question_paper_card,
                OtherViewHolder.class,
                referenceOther
        ) {
            @Override
            protected void populateViewHolder(OtherViewHolder viewHolder, OtherMdll model, int position) {

                viewHolder.setSession(model.getSession());
                viewHolder.textViewone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        download();
                    }
                });
                viewHolder.textViewtwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        download();
                    }
                });
                viewHolder.textViewthree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        download();
                    }
                });
                viewHolder.textViewSem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        download();
                    }
                });
            }
        };
        recyclerViewOther.setAdapter(firebaseRecyclerAdapter);
    }
    public static class OtherViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView textViewone,textViewtwo,textViewthree,textViewSem;
        public OtherViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            textViewone = (TextView)mView.findViewById(R.id.sessional_one);
            textViewtwo = (TextView)mView.findViewById(R.id.sessional_two);
            textViewthree = (TextView)mView.findViewById(R.id.sessional_three);
            textViewSem = (TextView)mView.findViewById(R.id.semester);
        }

        public void setSession (String session){
            Button post_desc = mView.findViewById(R.id.session_button);
            post_desc.setText(session);
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
