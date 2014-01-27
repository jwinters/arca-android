package com.appnet.app.fragments;

import java.util.Arrays;
import java.util.Collection;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appnet.app.R;
import com.appnet.app.datasets.PostTable;
import com.appnet.app.providers.AppNetContentProvider;
import com.xtreme.rest.adapters.ItemCursorAdapter;
import com.xtreme.rest.binders.Binding;
import com.xtreme.rest.binders.ViewBinder;
import com.xtreme.rest.fragments.RestItemSupportFragment;
import com.xtreme.rest.loader.ContentError;
import com.xtreme.rest.loader.ContentRequest;
import com.xtreme.rest.loader.ContentResponse;

public class PostFragment extends RestItemSupportFragment implements ViewBinder {
	
	private static final Collection<Binding> BINDINGS = Arrays.asList(new Binding[] { 
		new Binding(R.id.post_text, PostTable.Columns.TEXT),
	});

	private String mId;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_post, container, false);
	}
	
	@Override
	public ItemCursorAdapter onCreateAdapter(final View view) {
		final ItemCursorAdapter adapter = new ItemCursorAdapter(view, BINDINGS);
		adapter.setViewBinder(this);
		return adapter;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		loadPost(mId);
	}
	
	public void setId(final String id) {
		mId = id;
	}
	
	private void loadPost(final String id) {
		final Uri baseUri = AppNetContentProvider.Uris.POSTS_URI;
		final Uri contentUri = Uri.withAppendedPath(baseUri, id);
		execute(new ContentRequest(contentUri));
	}
	
	@Override
	public void onContentChanged(final ContentResponse response) {
		final ItemCursorAdapter adapter = getItemAdapter();
		if (adapter.hasResults()) {
			showResults();
		} else {
			hideLoading();
		}
	}
	
	private void showResults() {
		getView().findViewById(R.id.post_container).setVisibility(View.VISIBLE);
		getView().findViewById(R.id.loading).setVisibility(View.INVISIBLE);
	}
	
	private void hideLoading() {
		getView().findViewById(R.id.loading).setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onError(final ContentError error) {
		Toast.makeText(getActivity(), "ERROR: " + error.getMessage(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean setViewValue(final View view, final Cursor cursor, final Binding binding) {
		return false;
	}
	
}