package com.shaadi.matrimony.user;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;

import com.shaadi.matrimony.holder.Result;
import com.shaadi.matrimony.holder.UserList;

import java.util.List;

public class UserViewModel extends BaseObservable {
    Context context;
    UserRepository repository;

    public UserViewModel(Context context) {
        this.context = context;
        repository = new UserRepository(context);
    }

    public LiveData<UserList> getUserList() {
        return repository.getUserData();
    }

    public LiveData<List<Result>> getUserFromDB() {
        return repository.getUserDataFromDB();
    }
}
