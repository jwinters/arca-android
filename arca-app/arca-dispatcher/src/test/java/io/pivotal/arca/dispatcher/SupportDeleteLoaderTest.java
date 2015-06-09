/* 
 * Copyright (C) 2015 Pivotal Software, Inc.
 *
 * Licensed under the Modified BSD License.
 *
 * All rights reserved.
 */
package io.pivotal.arca.dispatcher;

import android.test.AndroidTestCase;

import io.pivotal.arca.dispatcher.DeleteResult;
import io.pivotal.arca.dispatcher.Error;
import io.pivotal.arca.dispatcher.SupportDeleteLoader;

public class SupportDeleteLoaderTest extends AndroidTestCase {

	public void testErrorResult() {
		final io.pivotal.arca.dispatcher.Error error = new Error(100, "message");
		final SupportDeleteLoader loader = new SupportDeleteLoader(getContext(), null, null);
		final DeleteResult result = loader.getErrorResult(error);

		assertTrue(result.hasError());
		assertEquals(error, result.getError());
	}

}
