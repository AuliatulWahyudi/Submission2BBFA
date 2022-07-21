package com.yudi.submission2bbfa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.yudi.submission2bbfa.adapter.PageAdapter;
import com.yudi.submission2bbfa.adapter.UserAdapter;
import com.yudi.submission2bbfa.model.DetailUserModel;
import com.yudi.submission2bbfa.model.UserModel;
import com.yudi.submission2bbfa.retrofit.ApiClient;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailListActivity extends AppCompatActivity {

	DetailUserModel dataDetailUser;
	UserModel dataUser;
	TextView tvName, tvUsername, tvLocation;
	ImageView ivAvatar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_list);

		getSupportActionBar().setTitle(getString(R.string.detail_user));

		Bundle bundle = getIntent().getBundleExtra(UserAdapter.DATA_EXTRA);
		dataUser = Parcels.unwrap(bundle.getParcelable(UserAdapter.DATA_USER));

		ivAvatar = findViewById(R.id.iv_avatar_detail);
		tvUsername = findViewById(R.id.tv_username_detail);
		tvName = findViewById(R.id.tv_name_detail);
		tvLocation = findViewById(R.id.tv_location_detail);

		// Menampilkan progress Dialog
		final ProgressDialog progress = new ProgressDialog(DetailListActivity.this);
		progress.setMessage(getString(R.string.progress));
		progress.show();

		//Mengambil data dari Parcelable
		Glide.with(DetailListActivity.this)
				.load(dataUser.getAvatarUrl())
				.into(ivAvatar);
		tvUsername.setText(dataUser.getLogin());

		Call<DetailUserModel> request = ApiClient.getApiService().getDetailUser(dataUser.getLogin());
		request.enqueue(new Callback<DetailUserModel>() {
			@Override
			public void onResponse(Call<DetailUserModel> call, Response<DetailUserModel> response) {
				dataDetailUser = response.body();

				tvName.setText(dataDetailUser.getName());
				tvLocation.setText(dataDetailUser.getLocation());
				progress.dismiss();
			}

			@Override
			public void onFailure(Call<DetailUserModel> call, Throwable t) {

			}
		});

		//Tablayout dan ViewPager
		PageAdapter pageAdapter = new PageAdapter(this, getSupportFragmentManager());
		ViewPager viewPager = findViewById(R.id.view_pager);
		viewPager.setAdapter(pageAdapter);
		TabLayout tabLayout = findViewById(R.id.tab_layout);
		tabLayout.setupWithViewPager(viewPager);

		getSupportActionBar().setElevation(0);
	}
}