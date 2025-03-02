package com.example.fetchapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private final List<Item> itemLst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Adapter and set it to RecyclerView
        adapter = new ItemAdapter(itemLst);
        recyclerView.setAdapter(adapter);

        // Fetch data from the API
        fetchData();
    }

    private void fetchData() {
        APIService apiService = RetrofitClient.getClient();
        Call<List<Item>> call = apiService.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemLst.clear();
                    itemLst.addAll(filterAndSortItems(response.body()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private List<Item> filterAndSortItems(List<Item> items) {
        List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getName() != null && !item.getName().trim().isEmpty()) {
                filteredItems.add(item);
            }
        }

        filteredItems.sort((o1, o2) -> {
            if (o1.getListId() == o2.getListId()) {
                return o1.getName().compareTo(o2.getName());
            }
            return Integer.compare(o1.getListId(), o2.getListId());
        });

        return filteredItems;
    }
}