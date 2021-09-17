package com.shaadi.matrimony.user;


import com.shaadi.matrimony.holder.Result;

public interface UserClickListener {
    void accept(Result holder,int position);
    void decline(Result holder,int position);
}
