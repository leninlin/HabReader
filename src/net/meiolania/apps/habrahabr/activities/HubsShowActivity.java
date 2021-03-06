/*
Copyright 2012 Andrey Zaytsev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package net.meiolania.apps.habrahabr.activities;

import net.meiolania.apps.habrahabr.fragments.hubs.HubsPostsFragment;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;

public class HubsShowActivity extends AbstractionActivity{
	public final static String EXTRA_URL = "url";
	public final static String EXTRA_TITLE = "title";
	protected String url;
	protected String title;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		loadExtras();
		showActionBar();
		loadHubsPosts();
	}

	private void loadExtras(){
		url = getIntent().getStringExtra(EXTRA_URL);
		title = getIntent().getStringExtra(EXTRA_TITLE);
	}

	private void showActionBar(){
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle(title);
	}

	private void loadHubsPosts(){
		HubsPostsFragment fragment = new HubsPostsFragment(url);
		
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(android.R.id.content, fragment);
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

	@Override
	protected OnClickListener getConnectionDialogListener(){
		return new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				finish();
			}
		};
	}

}