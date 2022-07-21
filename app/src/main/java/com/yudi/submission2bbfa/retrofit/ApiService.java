package com.yudi.submission2bbfa.retrofit;

import com.yudi.submission2bbfa.model.DetailUserModel;
import com.yudi.submission2bbfa.model.FollowerUserModel;
import com.yudi.submission2bbfa.model.FollowingUserModel;
import com.yudi.submission2bbfa.model.ResponseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

	@GET("search/users")
	@Headers("Authorization: token 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")
	Call<ResponseUser> getSearchUser(
			@Query("q") String username
	);

	@GET("users/{username}")
	@Headers("Authorization: token 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")
	Call<DetailUserModel> getDetailUser(
			@Path("username") String username
	);

	@GET("users/{username}/followers")
	@Headers("Authorization: token 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")
	Call<List<FollowerUserModel>> getFollowerUser(
			@Path("username") String username
	);

	@GET("users/{username}/following")
	@Headers("Authorization: token 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")
	Call<List<FollowingUserModel>> getFollowingUser(
			@Path("username") String username
	);
}
