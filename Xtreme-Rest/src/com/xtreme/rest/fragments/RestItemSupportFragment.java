package com.xtreme.rest.fragments;

import java.util.Collection;

import android.os.Bundle;
import android.view.View;

import com.xtreme.rest.adapters.ItemCursorAdapter;
import com.xtreme.rest.binders.Binding;
import com.xtreme.rest.dispatcher.QueryResult;
import com.xtreme.rest.dispatcher.Error;

/**
 * A {@link RestQuerySupportFragment} that adds convenient support for 
 * a single item by wrapping a {@link ItemCursorAdapter}.
 */
public abstract class RestItemSupportFragment extends RestQuerySupportFragment {

	private ItemCursorAdapter mAdapter;

	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		setupAdapterView(view);
	}
	
	private void setupAdapterView(final View view) {
		mAdapter = onCreateAdapter(view);
	}

	public ItemCursorAdapter onCreateAdapter(final View view) {
		return new ItemCursorAdapter(view, getBindings());
	}
	
	public Collection<Binding> getBindings() {
		return null;
	}
	
	public ItemCursorAdapter getItemAdapter() {
		return mAdapter;
	}

	@Override
	public final void onRequestComplete(final QueryResult result) {
		if (result.hasError()) {
			onContentError(result.getError());
		} else {
			getItemAdapter().swapCursor(result.getResult());
			onContentChanged(result);
		}
	}
	
	@Override
	public final void onRequestReset() {
		getItemAdapter().swapCursor(null);
		onContentReset();
	}
	
	protected void onContentChanged(final QueryResult result) {}
	protected void onContentError(final Error error) {}
	protected void onContentReset() {}
}
