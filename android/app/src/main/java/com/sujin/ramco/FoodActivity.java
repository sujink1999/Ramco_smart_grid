package com.sujin.ramco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodActivity extends AppCompatActivity {

    ArrayList<FoodItem> foodItems = new ArrayList<>();
    RecyclerView recyclerView;
    TextView total;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        getWindow().setStatusBarColor(getResources().getColor(R.color.darkColor1));

        foodItems.add(new FoodItem("Maharaja Mac", 0, 270, R.drawable.burger1));
        foodItems.add(new FoodItem("French Fries", 0, 80, R.drawable.french_fries));
        foodItems.add(new FoodItem("Chicken Burger", 0, 250, R.drawable.burger1));
        foodItems.add(new FoodItem("Paneer Burger", 0, 200, R.drawable.burger1));
        foodItems.add(new FoodItem("Pepsi", 0, 60, R.drawable.pepsi));
        foodItems.add(new FoodItem("Ice Cream", 0, 150, R.drawable.soft_serve));


        total = findViewById(R.id.total);
        recyclerView = findViewById(R.id.recycler_view);
        FoodAdapter foodAdapter = new FoodAdapter(getApplicationContext(), foodItems, total);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(foodAdapter);

        cardView = findViewById(R.id.order_now);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api service = retrofit.create(Api.class);

                LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                Toast.makeText(FoodActivity.this, "Order Sent!", Toast.LENGTH_SHORT).show();

                Call<String> repos = service.sendLatLong(Double.toString(userLocation.latitude),Double.toString(userLocation.longitude));
                repos.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("R" +
                                "epository",t.getMessage());
                    }
                });
            }
        });


    }


}
