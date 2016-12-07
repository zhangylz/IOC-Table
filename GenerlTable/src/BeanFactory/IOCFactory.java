package BeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;

public class IOCFactory {

	private static Boolean isInstance = false;
	
	private static IOCFactory factory;
	
	//类名 属性名 属性类型
	private static HashMap<String,HashMap<String,String>> beanDefinition = new HashMap<String,HashMap<String,String>>();
	
	private static HashMap<String,Object> beans = new HashMap<String,Object>();
	
	//单例
	private IOCFactory(){}
	
	//单例模式
	public static final IOCFactory newInstance(){
		if(isInstance){
			return factory;
		}else{
			factory = new IOCFactory();
			isInstance = true;
			return factory;
		}
	}
	
	/**
	 * @author LoveTaeyeon<p>
	 * 构造实体模型
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> void initModel(T t) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<? extends Object> model = t.getClass();
		String className = model.getName();
		//System.out.println("className:" + className);
		Field fields[] = model.getDeclaredFields();
		HashMap<String,String> fieldData = new HashMap<String,String>();
		for(Field field : fields){
			//设置对static的权限
			field.setAccessible(true);
			String fieldName = field.getName();
			//System.out.println("fieldName:" + fieldName);
			String fieldType = field.getType().getName();
			//System.out.println("fieldType:" + fieldType);
			field.setAccessible(false);
			fieldData.put(fieldName,fieldType);
		}
		beanDefinition.put(className,fieldData);
	}
	
	/**
	 * @author LoveTaeyeon<p>
	 * 根据类名生成Bean
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<? extends Object> bean) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String beanName = bean.getName();
		Object obj = beans.get(beanName);
		if(obj == null){
			Object newObj = Class.forName(beanName).newInstance();
			beans.put(beanName,obj);
			return (T)newObj;
		}
		return (T) obj;
	}
	
}
