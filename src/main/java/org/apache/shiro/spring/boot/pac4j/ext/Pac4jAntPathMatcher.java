/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shiro.spring.boot.pac4j.ext;

import org.apache.shiro.util.AntPathMatcher;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.matching.PathMatcher;

public class Pac4jAntPathMatcher extends PathMatcher {
	
	private AntPathMatcher matcher = new AntPathMatcher();
	 
	@Override
	public boolean matches(WebContext context) {
		for (String pattern : getExcludedPaths()) {
			if (matcher.match(pattern, context.getPath())) {
				return true;
			}
		}
		return super.matches(context);
	}

}
