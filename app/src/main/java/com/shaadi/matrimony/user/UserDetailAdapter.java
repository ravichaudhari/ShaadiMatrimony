package com.shaadi.matrimony.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.shaadi.matrimony.BR;
import com.shaadi.matrimony.R;
import com.shaadi.matrimony.databinding.UserListItemBinding;
import com.shaadi.matrimony.holder.Result;

import java.util.List;

public class UserDetailAdapter extends RecyclerView.Adapter<UserDetailAdapter.ViewHolder> implements UserClickListener {
    List<Result> results;
    Context context;
    UserRepository repository;

    public UserDetailAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.results = resultList;
        repository = new UserRepository(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        UserListItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.user_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(results.get(holder.getAdapterPosition()));
        holder.userListItemBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    @Override
    public void accept(Result holder, int position) {

        holder.setStatus("Accept");
        repository.updateHolder(holder);
        notifyItemChanged(position);
    }

    @Override
    public void decline(Result holder, int position) {

        holder.setStatus("Decline");
        repository.updateHolder(holder);
        notifyItemChanged(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        UserListItemBinding userListItemBinding;

        public ViewHolder(@NonNull UserListItemBinding userListItemBinding) {
            super(userListItemBinding.getRoot());
            this.userListItemBinding = userListItemBinding;

        }

        public void bind(Result result) {
            userListItemBinding.setVariable(BR.userData, result);
            userListItemBinding.setVariable(BR.position, getAdapterPosition());
            userListItemBinding.executePendingBindings();

        }
    }
}
