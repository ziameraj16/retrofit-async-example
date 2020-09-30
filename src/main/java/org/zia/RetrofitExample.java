package org.zia;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RetrofitExample {

    public static void main(String[] args) {
        new RetrofitExample().runExample();
    }

    private void runExample() {
        final RestService restService = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(RestService.class);
        restService.createUser(new User("My name", "54321", "99")).enqueue(new Callback<User>() {
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("Response: " + response);
            }

            public void onFailure(Call<User> call, Throwable throwable) {
                System.err.println("ERROR:" + throwable.getMessage());
            }
        });
    }

    private interface RestService {

        @POST("create")
        Call<User> createUser(@Body User user);
    }


}
