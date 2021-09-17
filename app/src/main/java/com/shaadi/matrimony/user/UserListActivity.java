package com.shaadi.matrimony.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.shaadi.matrimony.R;
import com.shaadi.matrimony.databinding.ActivityUserListBinding;
import com.shaadi.matrimony.holder.Result;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ActivityUserListBinding userListBinding;
    UserDetailAdapter adapter;
    List<Result> resultList = new ArrayList<>();
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list);

        viewModel = new UserViewModel(this);

        userListBinding.executePendingBindings();

        adapter = new UserDetailAdapter(this, resultList);
        userListBinding.setUserAdapter(adapter);

        viewModel.getUserFromDB().observe(this, resultHolders -> {
            resultList.addAll(resultHolders);
            adapter = new UserDetailAdapter(this, resultList);
            userListBinding.setUserAdapter(adapter);
        });

        viewModel.getUserList().observe(this, userListHolder -> {
            if (userListHolder != null) {
                resultList = userListHolder.getResults();
                adapter = new UserDetailAdapter(this, resultList);
                userListBinding.setUserAdapter(adapter);
            }
        });



    }


}


