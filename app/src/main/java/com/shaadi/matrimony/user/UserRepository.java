package com.shaadi.matrimony.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.shaadi.matrimony.R;
import com.shaadi.matrimony.database.AppDatabase;
import com.shaadi.matrimony.database.dao.AppDao;
import com.shaadi.matrimony.database.dao.InfoDao;
import com.shaadi.matrimony.database.entity.InfoEntity;
import com.shaadi.matrimony.holder.Info;
import com.shaadi.matrimony.holder.Result;
import com.shaadi.matrimony.holder.UserList;
import com.shaadi.matrimony.network.APICall;
import com.shaadi.matrimony.network.RetrofitConfig;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    Context context;
    APICall apiCall;

    public UserRepository(Context context) {
        this.context = context;
        apiCall = RetrofitConfig.getClient(context).create(APICall.class);
    }


    public LiveData<UserList> getUserData() {


        final MutableLiveData<UserList> data = new MutableLiveData<>();
        apiCall.getUsers("10").enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserList holder = response.body();
                    Info info = holder.getInfo();

                    InfoEntity infoEntity = new InfoEntity(info.getSeed(), info.getResults(), info.getPage(), info.getVersion());
                    new insertInfoAsync(AppDatabase.getDatabase(context).infoDao()).execute(infoEntity);
                    new insertLocAsync(AppDatabase.getDatabase(context).appDao()).execute(holder.getResults());

                    data.setValue(holder);
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                data.setValue(null);
                Toast.makeText(context, context.getResources().getString(R.string.fail_data_msg), Toast.LENGTH_SHORT).show();
            }
        });

        return data;
    }

    public LiveData<List<Result>> getUserDataFromDB() {
        try {
            return new getAllUserData().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateHolder(Result holder) {
        new updateAsync().execute(holder);
    }


    private static class insertInfoAsync extends AsyncTask<InfoEntity, Void, Void> {
        private InfoDao infoDao;

        public insertInfoAsync(InfoDao infoDao) {
            this.infoDao = infoDao;
        }


        @Override
        protected Void doInBackground(InfoEntity... infoEntities) {
            infoDao.deleteAll();
            infoDao.insertInfo(infoEntities[0]);
            Log.e("TAG", new Gson().toJson(infoDao.getAll()));
            return null;
        }
    }

    private static class insertLocAsync extends AsyncTask<List<Result>, Void, Void> {
        private AppDao appDao;

        public insertLocAsync(AppDao appDao) {
            this.appDao = appDao;
        }


        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Result>... resultHolders) {
            appDao.deleteAll();
            appDao.insertLocRecord(resultHolders[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class getAllUserData extends AsyncTask<Void, Void, LiveData<List<Result>>> {
        @Override
        protected LiveData<List<Result>> doInBackground(Void... url) {
            return AppDatabase.getDatabase(context).appDao().getAll();
        }
    }

    private class updateAsync extends AsyncTask<Result, Void, Void> {


        @Override
        protected Void doInBackground(Result... results) {
            AppDatabase.getDatabase(context).appDao().UpdateColumnById(results[0].getStatus(),
                    results[0].get_rId());
            return null;
        }
    }
}
