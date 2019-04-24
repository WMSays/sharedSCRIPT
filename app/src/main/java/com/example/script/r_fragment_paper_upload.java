//package com.example.script;
//
//import android.Manifest;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.github.ybq.android.spinkit.style.ChasingDots;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//public class r_fragment_paper_upload extends AppCompatActivity {
//    public ImageView select_file;
//    public TextView tv_notif;
//    public Button btn_upload;
//    public Button btn_r_fragment_paper_upload_next;
//    public Uri docUri;
//    public ProgressBar pb_upload;
// public FirebaseStorage storage; //to uploafd files
// public FirebaseDatabase database; //to store urls of uploaded files
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_r_fragment_paper_upload);
//        Init();
//        select_file.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //if(ContextCompat.checkSelfPermission(r_fragment_paper_upload.this, (Manifest.permission.READ_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED)))
//                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED)
//                {
//                    selectDOC();
//                }
//                else
//                {
//                    ActivityCompat.requestPermissions(r_fragment_paper_upload.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 9 );
//                }
//
//            }
//        });
//
//        btn_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(docUri!=null) {
//                    uploadFile(docUri);
//                }
//                else
//                {
//                    Toast.makeText(r_fragment_paper_upload.this, "Select a file.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            private void uploadFile(Uri docUri) {
//                pb_upload.setVisibility(View.VISIBLE);
//
//                StorageReference storageReference= storage.getReference();//gets root path
//                final String fileName= System.currentTimeMillis()+"";
//                //TODO: dynamically get file name.
//                //not sotring file in root.
//                //make subfolder
//                storageReference.child("Uploads").child(fileName).putFile(docUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        //String url= taskSnapshot.getDownloadURL().toString();
//                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
//                        while (!urlTask.isSuccessful());
//                        Uri downloadUrl = urlTask.getResult();
//
//                        final String url = String.valueOf(downloadUrl);
//                        DatabaseReference reference= database.getReference();
//                        reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful())
//                                {                pb_upload.setVisibility(View.GONE);
//
//                                    Toast.makeText(r_fragment_paper_upload.this, "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                { pb_upload.setVisibility(View.GONE);
//                                    Toast.makeText(r_fragment_paper_upload.this, "File Not Uploaded", Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        pb_upload.setVisibility(View.GONE);
//                        Toast.makeText(r_fragment_paper_upload.this, "File Not Uploaded", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                        pb_upload.setVisibility(View.VISIBLE);
//
//                    }
//                });
//
//            }
//        });
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
//        {
//            selectDOC();
//        }
//        else
//        {
//            Toast.makeText(this, "Please provide permission.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void selectDOC() {
//        Intent intent= new Intent();
//        intent.setType("application/doc");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, 89);
//
//
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        //check if file selected
//        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
//        {
//            docUri= data.getData();//return uri of selcted file
//            String msg= "File"+data.getData().getLastPathSegment()+ "Selected";
//            tv_notif.setText(msg);
//        }
//        else
//        {
//            Toast.makeText(this, "Please select a file", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void Init() {
//        storage = FirebaseStorage.getInstance();
//        database = FirebaseDatabase.getInstance();
//        select_file= (ImageView)findViewById(R.id.iv_paper_upload_select_file);
//        tv_notif= (TextView)findViewById(R.id.tv_r_fragment_paper_upload_notif);
//        btn_upload= (Button)findViewById(R.id.btn_r_fragment_paper_upload);
//                btn_r_fragment_paper_upload_next= (Button)findViewById(R.id.btn_r_fragment_paper_upload_next);
//        pb_upload= (ProgressBar) findViewById(R.id.pb_signup);
//        //making custom progress bar object
//        ChasingDots chasingDots= new ChasingDots();
//        pb_upload.setIndeterminateDrawable(chasingDots);
//        pb_upload.setVisibility(View.GONE);
//    }
//
//
//}
