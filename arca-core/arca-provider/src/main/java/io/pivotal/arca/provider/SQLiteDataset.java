package io.pivotal.arca.provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public abstract class SQLiteDataset extends ContextDataset {

	public abstract void onCreate(final SQLiteDatabase db);

	public abstract void onDrop(final SQLiteDatabase db);

	private SQLiteDatabase mDatabase;

	protected SQLiteDatabase getDatabase() {
		return mDatabase;
	}

	protected void setDatabase(final SQLiteDatabase db) {
		mDatabase = db;
	}

	public String getName() {
		final Class<? extends SQLiteDataset> klass = getClass();
		final DatasetName name = klass.getAnnotation(DatasetName.class);
		if (name != null) {
			return name.value();
		} else {
			return klass.getSimpleName();
		}
	}

	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
		final Class<? extends SQLiteDataset> klass = getClass();
		final PersistOnUpgrade persist = klass.getAnnotation(PersistOnUpgrade.class);
		if (persist == null) {
			onDrop(db);
			onCreate(db);
		}
	}

	public void onDowngrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
		final Class<? extends SQLiteDataset> klass = getClass();
		final PersistOnDowngrade persist = klass.getAnnotation(PersistOnDowngrade.class);
		if (persist == null) {
			onDrop(db);
			onCreate(db);
		}
	}

	@Override
	public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
		final SQLiteDatabase database = getDatabase();
		if (database != null) {
			return database.query(getName(), projection, selection, selectionArgs, null, null, sortOrder);
		} else {
			throw new IllegalStateException("Database is null.");
		}
	}
}
