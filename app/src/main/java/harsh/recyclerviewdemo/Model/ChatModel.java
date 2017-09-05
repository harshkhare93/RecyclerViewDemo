package harsh.recyclerviewdemo.Model;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by ngamacmini17 on 05/09/17.
 */

public class ChatModel {
    String name;
    String phone;
    String photo;


    public ChatModel() {
    }

    public ChatModel(DataSnapshot snapshot) {
        this.name = snapshot.child("Full Name").getValue(String.class);
        this.phone = snapshot.child("Mobile Number").getValue(String.class);
        this.photo = (String) snapshot.child("photoUrl").getValue();

    }

//    public ChatModel(String name, String phone, String photo) {
//        this.name = name;
//        this.phone = phone;
//        this.photo = photo;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
