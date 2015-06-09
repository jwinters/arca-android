/* 
 * Copyright (C) 2015 Pivotal Software, Inc.
 *
 * Licensed under the Modified BSD License.
 *
 * All rights reserved.
 */
package io.pivotal.arca.dispatcher;

public interface InsertListener extends RequestListener<InsertResult> {
	@Override
	public void onRequestComplete(InsertResult result);
}
