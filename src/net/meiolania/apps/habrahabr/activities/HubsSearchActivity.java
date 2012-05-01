package net.meiolania.apps.habrahabr.activities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.meiolania.apps.habrahabr.R;
import net.meiolania.apps.habrahabr.fragments.HubsFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class HubsSearchActivity extends SherlockFragmentActivity{
    public final static String URL = "http://habrahabr.ru/search/page%page%/?q=%query%&target_type=hubs";
    public final static String EXTRA_QUERY = "query";
    private String query;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        loadExtras();
        showActionBar();
        loadSearchedHubs();
    }
    
    private void loadExtras(){
        query = getIntent().getStringExtra(EXTRA_QUERY);
    }
    
    private void showActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.hubs_search);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    private void loadSearchedHubs(){
        HubsFragment hubsFragment = new HubsFragment();
        try{
            hubsFragment.setUrl(URL.replace("%query%", URLEncoder.encode(query, "UTF-8")));
        }catch(UnsupportedEncodingException e){
            hubsFragment.setUrl(URL.replace("%query%", query));
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, hubsFragment);
        fragmentTransaction.commit();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, HubsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
}