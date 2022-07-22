package com.example.dog_app;

import android.net.Uri;

import com.example.dog_app.models.Dog;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class ExternalStore {

    public static String DOGS_PATH = "DOGS";
    private static ListenerRegistration dogsListener;


    public static void getAllDogs(OnSuccessListener<List<Dog>> onSuccessListener) {
        dogsListener = FirebaseFirestore
                .getInstance()
                .collection(DOGS_PATH)
                .addSnapshotListener((value, error) -> {
                    ArrayList<Dog> dogList = new ArrayList<>();
                    for (DocumentSnapshot dogSnapshot : value.getDocuments()) {
                        Dog d = dogSnapshot.toObject(Dog.class);
                        if (d == null) continue;
                        d.setImage(SecurityHelper.Decrypt(d.getImage()));
                        dogList.add(d);
                    }
                    onSuccessListener.onSuccess(dogList);
                });
    }

    public static void removeDogListener() {
        if (dogsListener != null)
            dogsListener.remove();
    }

    public static void removeDogFromStorage(Dog dog, OnSuccessListener<String> onSuccessListener, OnFailureListener onFailureListener) {
        FirebaseFirestore
                .getInstance()
                .collection(DOGS_PATH)
                .document(dog.getId())
                .delete()
                .addOnSuccessListener(unused ->
                        onSuccessListener.onSuccess("the dog " + dog.getName() + " successfully deleted"))
                .addOnFailureListener(onFailureListener);
    }

    public static void addDogToStorage(Dog dog,
                                       Uri image,
                                       OnSuccessListener<Dog> onSuccessListener,
                                       OnFailureListener onFailureListener) {

        StorageReference newImageReference = FirebaseStorage.getInstance().getReference().child("DogImages/" + dog.getId());
        newImageReference.putFile(image)
                .addOnSuccessListener(taskSnapshot -> newImageReference.getDownloadUrl()
                        .addOnSuccessListener(imageUrl -> {
                            dog.setImage(SecurityHelper.Encrypt(imageUrl.toString()));
                            FirebaseFirestore
                                    .getInstance()
                                    .collection(DOGS_PATH)
                                    .document(dog.getId())
                                    .set(dog)
                                    .addOnSuccessListener(documentReference -> onSuccessListener.onSuccess(dog))
                                    .addOnFailureListener(onFailureListener);
                        }).addOnFailureListener(onFailureListener))
                .addOnFailureListener(onFailureListener);
    }

}
