/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.ClassLoaderPool;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletClassLoaderUtil {

	public static ClassLoader getClassLoader() {
		return ClassLoaderPool.getClassLoader(getServletContextName());
	}

	public static ClassLoader getClassLoader(String portletId) {
		PortalRuntimePermission.checkGetClassLoader(portletId);

		PortletBag portletBag = PortletBagPool.get(portletId);

		if (portletBag == null) {
			return null;
		}

		ServletContext servletContext = portletBag.getServletContext();

		return (ClassLoader)servletContext.getAttribute(
			PluginContextListener.PLUGIN_CLASS_LOADER);
	}

	public static String getServletContextName() {
		String servletContextName = _servletContextName.get();

		if (servletContextName == null) {
			throw new IllegalStateException(
				"No servlet context name specified");
		}

		return servletContextName;
	}

	public static void setServletContextName(String servletContextName) {
		PortalRuntimePermission.checkSetBeanProperty(
			PortletClassLoaderUtil.class);

		_servletContextName.set(servletContextName);
	}

	private static ThreadLocal<String> _servletContextName =
		new AutoResetThreadLocal<String>(
			PortletClassLoaderUtil.class + "._servletContextName");

}