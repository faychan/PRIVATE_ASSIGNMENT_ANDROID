package id.sch.smktelkom_mlg.privateassignment.xirpl113.privateassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl113.privateassignment.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl113.privateassignment.model.Source;
import id.sch.smktelkom_mlg.privateassignment.xirpl113.privateassignment.model.SourcesResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl113.privateassignment.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl113.privateassignment.service.VolleySingleton;

public class MainActivity extends AppCompatActivity implements SourceAdapter.ISourceAdapter
{
    public static final String SOURCEID = "sourceId";
    public static final String SOURCESORTBY = "sourceSortBy";
    public static final String SOURCENAME = "sourceName";
    ArrayList<Source> mList = new ArrayList<>();
    SourceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SourceAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);

        setTitle("News Source");

        downloadDataSources();
    }

    private void downloadDataSources()
    {
        String url="https://newsapi.org/v1/sources?language=en";
        //String url = "https://data.nasa.gov/";
        //String url = "https://api.nasa.gov/planetary/apod?api_key=wQF54yLghZoD7FJHEdMNkJdKgpl4qbeZ3laPtkl3";

        GsonGetRequest<SourcesResponse> myRequest = new GsonGetRequest<SourcesResponse>
                (url, SourcesResponse.class, null, new Response.Listener<SourcesResponse>()
                {

                    @Override
                    public void onResponse(SourcesResponse response)
                    {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        if (response.status.equals("ok"))
                        {
                            fillColor(response.sources);
                            mList.addAll(response.sources);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(myRequest);
    }

    private void fillColor(List<Source> sources)
    {
        for (int i = 0; i < sources.size(); i++)
            sources.get(i).color = ColorUtil.getRandomColor();
    }

    @Override
    public void showArticles(String id, String name, String sortBy)
    {
        Intent intent = new Intent(this, ArticlesActivity.class);
        intent.putExtra(SOURCEID, id);
        intent.putExtra(SOURCENAME, name);
        intent.putExtra(SOURCESORTBY, sortBy);
        startActivity(intent);
    }
}
