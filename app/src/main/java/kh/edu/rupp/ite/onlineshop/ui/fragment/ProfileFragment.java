package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadProfile();
    }

    public void loadProfile(){
        //Create Client Object
        Retrofit httpClientProfile = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Create Service Object
        ApiService apiService = httpClientProfile.create(ApiService.class);

        //Make a Call to Service Object
        Call<Profile> taskProfile = apiService.loadProfile();
        taskProfile.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
//                Toast.makeText(getContext(), "Successful.", Toast.LENGTH_SHORT).show();
                if(response.isSuccessful()){
                    showProfile(response.body());
                }else{
                    Toast.makeText(getContext(), "Failed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showProfile(Profile profile){
        //Create Layout Manager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        Picasso.get().load(profile.getImage()).into(binding.imageProfile);
        binding.txtFirstName.setText(profile.getFirstName());
        binding.txtLastName.setText(profile.getLastName());
        binding.txtEmail.setText(profile.getEmail());
        binding.txtBaseEmail.setText(profile.getEmail());
        binding.txtBasePhoneNumber.setText(profile.getPhoneNumber());
        binding.txtBaseGender.setText(profile.getGender());
        binding.txtBaseBirthday.setText(profile.getBirthday());
        binding.txtBaseAddress.setText(profile.getAddress());
    }



}
