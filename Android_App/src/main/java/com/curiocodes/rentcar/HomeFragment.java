package com.curiocodes.rentcar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.curiocodes.rentcar.Adapters.CarsAdapter;
import com.curiocodes.rentcar.AddOn.General;
import com.curiocodes.rentcar.Models.CarsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private String url = "http://192.168.1.100:8080/api/car/list";
    private RecyclerView carRecyclerView;
    private CarsAdapter adapter;
    private List<CarsModel> list;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        list  = new ArrayList<>();
        adapter = new CarsAdapter(list,getContext());
        carRecyclerView = view.findViewById(R.id.cars);
        carRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        carRecyclerView.setAdapter(adapter);

        getData();

        return view;
    }



    private void getData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        CarsModel carsModel = new CarsModel();
                        carsModel.setName(jsonObject.getString("carMake"));
                        carsModel.setSeats(jsonObject.getString("carSeats"));
                        carsModel.setGear(jsonObject.getString("carTransmission"));
                        carsModel.setPrice(Integer.parseInt(jsonObject.getString("pricePerHour")));
                        //carsModel.setImage(jsonObject.getString(""));
                        list.add(carsModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }


}
