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

import org.junit.Assert;
import org.junit.Test;

public class ListsTest {
	@Test
	public void newImmutableListTest() {
		newImmutableListTest(100);
	}

	@Test
	public void newImmutableListTestEmpty() {
		newImmutableListTest(0);
	}

	@Test
	public void newImmutableListSingleton() {
		newImmutableListTest(1);
	}

	private void newImmutableListTest(final int size) {
		final List<String> src = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			src.add("entry-" + i);
		}
		final List<String> immutable = Lists.newImmutableList(src);
		Assert.assertEquals(src, immutable);

		assertImmutable(immutable);

		if (size > 1) {
			assertStrictImmutable(immutable);
		}
	}

	private void assertImmutable(final List<String> immutable) {
		assertException(UnsupportedOperationException.class, () -> immutable.set(0, "bla"));
		assertException(UnsupportedOperationException.class, () -> immutable.add("bla"));
		assertException(UnsupportedOperationException.class, () -> immutable.addAll(Collections.singletonList("bla")));
		assertException(UnsupportedOperationException.class, () -> immutable.addAll(0, Collections.singletonList("bla")));
		assertException(UnsupportedOperationException.class, () -> immutable.add(0, "bla"));
		assertException(UnsupportedOperationException.class, () -> immutable.remove(0));
	}

	private void assertStrictImmutable(final List<String> immutable) {
		assertException(UnsupportedOperationException.class, () -> immutable.iterator().remove());
		assertException(UnsupportedOperationException.class, () -> immutable.clear());
		assertException(UnsupportedOperationException.class, () -> immutable.remove("bla"));
		assertException(UnsupportedOperationException.class, () -> immutable.removeAll(Collections.singletonList("bla")));
		assertException(UnsupportedOperationException.class, () -> immutable.removeIf(s -> s.startsWith("entry")));
		assertException(UnsupportedOperationException.class, () -> immutable.replaceAll(s -> s + "x"));
		assertException(UnsupportedOperationException.class, () -> immutable.retainAll(Collections.singletonList("bla")));
		assertException(UnsupportedOperationException.class, () -> immutable.subList(0, 1).clear());
	}

	private void assertException(final Class<? extends Throwable> exceptionType, final Runnable r) {
		try {
			r.run();
			Assert.fail("Expected exception of type " + exceptionType.getName() + ", but no exception was thrown");
		} catch (final Throwable e) {
			if (!exceptionType.isAssignableFrom(e.getClass())) {
				Assert.fail("Expected exception of type " + exceptionType.getName() + ", but was " + e.getClass().getName());
			}
		}
	}
}
