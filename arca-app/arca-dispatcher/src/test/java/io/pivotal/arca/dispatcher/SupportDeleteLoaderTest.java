package io.pivotal.arca.dispatcher;

import android.test.AndroidTestCase;

public class SupportDeleteLoaderTest extends AndroidTestCase {

	public void testErrorResult() {
		final io.pivotal.arca.dispatcher.Error error = new Error(100, "message");
		final SupportDeleteLoader loader = new SupportDeleteLoader(getContext(), null, null);
		final DeleteResult result = loader.getErrorResult(error);

		assertTrue(result.hasError());
		assertEquals(error, result.getError());
	}

}
