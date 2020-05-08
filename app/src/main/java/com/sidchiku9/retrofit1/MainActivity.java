package com.sidchiku9.retrofit1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.postView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rentbaaz.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        //from here

        Call<List<Post>> call = jsonPlaceHolder.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    mTextView.setText("Code : " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                final SharedPreferences sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE);
                Gson gson = new Gson();
                String json = gson.toJson(posts);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Set",json );
                editor.commit();

                for(Post post  : posts){
                    String content = "";
                    content += "NAME : " + post.getName() + "\n"  + "REAL NAME : " + post.getRealName() + "\n" +
                            "TEAM : " + post.getTeam() + "\n" + "FIRST APPEARNACE : "  + post.getFirstAppearance() + "\n" +
                            "CREATED BY : " + post.getCreatedBy() + "\n" + "PUBLISHER : " + post.getPublisher() + "\n" +
                            "IMAGE URL : " + post.getImageURL() + "\n" + "BIO : " + post.getBio() + "\n" + "\n";
                    mTextView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                mTextView.setText(t.getMessage());
            }
        });

    }
}
