package com.yudi.submission2bbfa;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yudi.submission2bbfa.adapter.UserAdapter;
import com.yudi.submission2bbfa.model.ResponseUser;
import com.yudi.submission2bbfa.model.UserModel;
import com.yudi.submission2bbfa.model.Users;
import com.yudi.submission2bbfa.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

	List<UserModel> dataGithub = new ArrayList<>();
	RecyclerView rvUser;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(getString(R.string.search_user));
		}

		progressBar = findViewById(R.id.progressBar);

		//1. Mengenali Recyclerview
		rvUser = findViewById(R.id.rv_search_user);

		// Layout Manager
		rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

		if (searchManager != null) {
			SearchView searchView = (SearchView) findViewById(R.id.sv_user);
			searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
			searchView.setQueryHint(getResources().getString(R.string.username));
			searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextSubmit(String s) {
					showProgress(true);
					if (s != null) {
						getDataOnline(s);
					} else {
						Toast.makeText(MainActivity.this, "Please insert username", Toast.LENGTH_SHORT).show();
					}
					return true;
				}

				@Override
				public boolean onQueryTextChange(String s) {
					return true;
				}
			});
		}
	}

	private void showProgress(Boolean state) {
		if (state) {
			progressBar.setVisibility(View.VISIBLE);
		} else {
			progressBar.setVisibility(View.GONE);
		}
	}

	private void getDataOnline(final String usernames) {
		Call<ResponseUser> request = ApiClient.getApiService().getSearchUser(usernames);
		request.enqueue(new Callback<ResponseUser>() {
			@Override
			public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
				if (response.isSuccessful()) {
					//Mengambil data dari internet masuk ke Data Github
					dataGithub = response.body().getItems();
					//Set Adapter ke Recycler View
					rvUser.setAdapter(new UserAdapter(MainActivity.this, dataGithub));
					showProgress(false);

				} else {
					Toast.makeText(MainActivity.this, "Request Not Success", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onFailure(Call<ResponseUser> call, Throwable t) {
				Toast.makeText(MainActivity.this, "Request Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.action_change_settings) {
			Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
			startActivity(mIntent);

		}
		return super.onOptionsItemSelected(item);
	}
}