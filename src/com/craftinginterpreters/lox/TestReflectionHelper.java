package com.craftinginterpreters.lox;

import java.lang.reflect.Method;

public class TestReflectionHelper {
	public static Object invokePrivateMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] args)
			throws Exception {
		Method method = object.getClass().getDeclaredMethod(methodName, parameterTypes);
		method.setAccessible(true);
		return method.invoke(object, args);
	}
}