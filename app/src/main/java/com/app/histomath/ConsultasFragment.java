package com.app.histomath;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ConsultasFragment extends Fragment {
    private SearchView  searchView;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;

    ProgressDialog progress;
    List<Matematicos> matematicos = new ArrayList<>();
    List<String> mDataKey =  new ArrayList<>();
    FormAdapter formAdapter=new FormAdapter(matematicos,getContext());

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_consultas, container, false);
        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v=view;
        init();
        searchView=v.findViewById(R.id.searchView_matematicos);
        progress = new ProgressDialog(getContext());
        progress.setTitle("Cargando...");
        progress.setMessage("Sincronizando");
        progress.setCancelable(false);
        progress.show();
        loadData();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")){
                    loadData();
                }else{
                    firebaseSearch(newText);}
                return false;
            }
        });


        formAdapter.setOnItemClickListener(new FormAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(v.getContext(),DetalleActivity.class);
                intent.putExtra("Matematico", matematicos.get(position));
                startActivity(intent);
            }
        });








    }

    public void loadData(){
        databaseReference= FirebaseDatabase.getInstance().getReference("Matematicos");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                matematicos.clear();
                for (DataSnapshot single: dataSnapshot.getChildren()){
                    matematicos.add(single.getValue(Matematicos.class));
                }
                formAdapter = new FormAdapter(matematicos, getContext());
                recyclerView.setAdapter(formAdapter);

                formAdapter.notifyDataSetChanged();
                progress.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
            }
        });

    }

    public void init(){
            recyclerView = v.findViewById(R.id.myRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    public void firebaseSearch(String searchText){
        Query firebaseQuerySearch = databaseReference.orderByChild("nombre")
                .startAt(searchText.toUpperCase())
                .endAt(searchText.toLowerCase()+ "\uf8ff");

        firebaseQuerySearch.addValueEventListener(new ValueEventListener() {
            @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    matematicos.clear();
                    for (DataSnapshot single: dataSnapshot.getChildren()){
                        matematicos.add(single.getValue(Matematicos.class));
                        mDataKey.add(single.getKey());
                    }
                    formAdapter = new FormAdapter(matematicos, getContext());
                    recyclerView.setAdapter(formAdapter);

                    formAdapter.notifyDataSetChanged();
                    progress.dismiss();
                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}





