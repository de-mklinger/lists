/*
 * Copyright mklinger GmbH - https://www.mklinger.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mklinger.micro.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for Lists.
 *
 * @author Marc Klinger - mklinger[at]mklinger[dot]de
 */
public class Lists {
	/** No instantiation */
	private Lists() {}

	/**
	 * Create a new immutable list with the contents of the given List.
	 *
	 * @param original The original list
	 * @return A new, immutable list containing all elements of the given list
	 */
	public static <T> List<T> newImmutableList(final List<T> original) {
		if (original == null || original.isEmpty()) {
			return Collections.emptyList();
		}
		if (original.size() == 1) {
			return Collections.singletonList(original.get(0));
		}
		return Collections.unmodifiableList(new ArrayList<>(original));
	}
}
