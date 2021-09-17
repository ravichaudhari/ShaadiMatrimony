package com.shaadi.matrimony.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.shaadi.matrimony.R;

import javax.annotation.Generated;


@Entity(tableName = "result_table")
@Generated("jsonschema2pojo")
public class Result {

    @PrimaryKey(autoGenerate = true)
    private int _rId;
    @Embedded(prefix = "name_")
    private Name name;
    @Embedded(prefix = "location_")
    private Location location;
    @Embedded(prefix = "login_")
    private Login login;
    @Embedded(prefix = "dob_")
    private Dob dob;
    @Embedded(prefix = "reg_")
    private Registered registered;
    @Embedded(prefix = "id_")
    private Id id;
    @Embedded(prefix = "pic_")
    private Picture picture;
    private String gender;
    private String email;
    private String phone;
    private String cell;
    private String nat;
    private String status;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public int get_rId() {
        return _rId;
    }

    public void set_rId(int _rId) {
        this._rId = _rId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @BindingAdapter("userAction")
    public static void actionStatus(TextView textView, Result result) {
        if (result.getStatus() == null || result.getStatus().isEmpty())
            textView.setVisibility(View.GONE);
        else {
            if (result.getStatus().equalsIgnoreCase("accept")) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(R.string.accept_msg);
            }
            if (result.getStatus().equalsIgnoreCase("decline")) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(R.string.decline_msg);
            }
        }
    }

    @BindingAdapter("performAction")
    public static void userActionView(LinearLayout v, Result result) {
        if (result.getStatus() == null || result.getStatus().isEmpty())
            v.setVisibility(View.VISIBLE);
        else v.setVisibility(View.GONE);
    }


}
