package br.com.agendaquiro.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope("singleton")
@Transactional(readOnly=true)
public class CopyUtil {

	public void copy(final Object source, final Object target) {

		//Comentado pois estava dando erro e não lembro mais o motivo desse código
		/*if (!source.getClass().equals(target.getClass())) {
			System.out.println(source.getClass().getSimpleName() +" Diferente do Targett "+target.getClass().getSimpleName());
			if(source.getClass().equals((target.getClass()+"TO"))){
				this.copy(source, target);
			}
		}*/
		Method[] methods = source.getClass().getMethods();

		try {
			for (Method method : methods) {
				if (method.getName().indexOf("get") != -1) {
					Object object = method.invoke(source, new Object[] {});

					if (object != null) {
						try {
							Method methodTarget = target.getClass().getMethod(
									"set" + method.getName().substring(3),
									new Class[] { object.getClass() });
							methodTarget
									.invoke(target, new Object[] { object });
						} catch (NoSuchMethodException nsme) {
						}
					}
				}
			}
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}
	}
}