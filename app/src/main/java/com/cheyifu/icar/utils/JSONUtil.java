package com.cheyifu.icar.utils;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {
	
	/**
	 * @描述：将对象转换为json
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param object 要转换的对象
	 * @return json字符串
	 */
	public static String parseObject(final Object object) {
		ObjectMapper om=new ObjectMapper();
		//Include.NON_NULL 属性为NULL 不序列化
		om.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		String ret=null;
		try {
			om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
			ret=om.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * @描述：将对象转换为json
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param object 要转换的对象
	 * @return json字符串
	 */
	public static String parseObject(final Object object,boolean isNumberToString) {
		ObjectMapper om=new ObjectMapper();
		String ret=null;
		try {
			om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
			
			if(isNumberToString){
				om.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
			}
			/*//临时解决方案，null转为字符串（数值类型 现在也会转，后期找解决方案）
			om.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>()  
		        {  
					@Override
					public void serialize(Object arg0, JsonGenerator arg1,
							SerializerProvider arg2) throws IOException,
							JsonProcessingException {
						
						arg1.writeString("");
					}  
		        }); */
			ret=om.writeValueAsString(object);
		} catch (Exception e) {
			Logger.i(e,"转换json串异常");
		}
		return ret;
	}
	
	/**
	 * @描述：将对象转换为json
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param object 要转换的对象
	 * @param filterob 过滤器名称
	 * @param specifypro 要转换的字段
	 * @return json字符串
	 */
	public static String parseObject(final Object object, final String filterob,final String[] specifypro){
		ObjectMapper om = new ObjectMapper();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		om.getSerializationConfig().setDateFormat(df);

		SimpleFilterProvider filter = new SimpleFilterProvider().setFailOnUnknownId(false);
		filter.addFilter(filterob, SimpleBeanPropertyFilter.filterOutAllExcept(specifypro));
		om.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
		om.setFilters(filter);
		String ret=null;
		try {
			ret=om.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ret;
	}
	
	/**
	 * @描述：将对象转换为json
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param object 要转换的对象
	 * @param filterMap 过滤器map
	 * @param dateFormat 日期的格式化模式
	 * @return json字符串
	 */
	public static String parseObject(Object object,Map<String, String[]> filterMap,String dateFormat){
		ObjectMapper om = new ObjectMapper();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		om.setDateFormat(format);
		SimpleFilterProvider filter = new SimpleFilterProvider().setFailOnUnknownId(false);
		for (Map.Entry<String, String[]> mapItem : filterMap.entrySet()) {
			filter.addFilter(mapItem.getKey(), SimpleBeanPropertyFilter.filterOutAllExcept(mapItem.getValue()));
		}
		om.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
		om.setFilters(filter);
		String ret=null;
		try {
			ret=om.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ret;
	}
	
	/**
	 * @描述：将对象转换为json
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param object 要转换的对象

	 * @param dateFormat 日期的格式化模式
	 * @return json字符串
	 */
	public static String parseObject(Object object,String dateFormat){
		ObjectMapper om = new ObjectMapper();
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		om.setDateFormat(format);
		String ret=null;
		try {
			ret=om.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ret;
	}
	
	/**
	 * @描述：将对象转换为json
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param object 要转换的对象
	 * @param filterMap 过滤器map
	 * @return json字符串
	 */
	public static String parseObject(Object object,Map<String, String[]> filterMap){
		ObjectMapper om = new ObjectMapper();
		SimpleFilterProvider filter = new SimpleFilterProvider().setFailOnUnknownId(false);
		for (Map.Entry<String, String[]> mapItem : filterMap.entrySet()) {
			filter.addFilter(mapItem.getKey(), SimpleBeanPropertyFilter.filterOutAllExcept(mapItem.getValue()));
		}
		om.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
		om.setFilters(filter);
		String ret=null;
		try {
			ret=om.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ret;
	}
	
	/**
	 * @描述：将json解析为对象
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param jsonstr 要解析的json字符串
	 * @param clazz 目标对象的字节码对象
	 * @return 转换后的对象
	 */
	public static  <T> T readJson2Entity(String jsonstr,Class<T> clazz){
		try {
			ObjectMapper om = new ObjectMapper();

			om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); 
	        T acc = om.readValue(jsonstr, clazz);
	        return acc;
	    } catch (JsonParseException e) {
	       Logger.i(e,"json转对象失败,数据：%s");
	    } catch (JsonMappingException e) {
	    	 Logger.i(e,"json转对象失败,数据：%s");
	    } catch (IOException e) {
	    	 Logger.i(e,"json转对象失败,数据：%s");
	    }
		return null;
	}
	
	/**
	 * @描述：将json解析为对象
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param jsonstr 要解析的json字符串
	 * @param object 目标对象同类型的对象
	 * @param filterob 过滤器名称
	 * @param specifypro 要转换的字段
	 * @return 转换后的对象
	 */
	public static Object readJson2Entity(String jsonstr , final Object object, final String filterob,final String[] specifypro){
		try {
			ObjectMapper om = new ObjectMapper();
			SimpleFilterProvider filter = new SimpleFilterProvider().setFailOnUnknownId(false);
			filter.addFilter(filterob, SimpleBeanPropertyFilter.filterOutAllExcept(specifypro));
			om.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
			om.setFilters(filter);
	        Object acc = om.readValue(jsonstr, object.getClass());
	        return acc;
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	/**
	 * @描述：将json解析为List
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param string 要解析的json字符串
	 * @param x List中元素的字节码文件
	 * @return List
	 */
	public static List<?> parseJson2Collection(String string,Class<?>  x) throws Exception{  
    	ObjectMapper mapper = new ObjectMapper();
		//Include.NON_NULL 属性为NULL 不序列化
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

    	/*mapper.enableDefaultTyping();*/
        JavaType javaType = getCollectionType(List.class, x); 
        List<Class<?>> list = mapper.readValue(string, javaType);
        return list;
    }   
	public static <T> List<T> paseModels(String string,Class<T> x) throws Exception{  
    	ObjectMapper mapper = new ObjectMapper(); 
        JavaType javaType = getCollectionType(ArrayList.class, x); 
        List<T> list = mapper.readValue(string, javaType);
        return list;
    }  
	
	/**
	 * 返回传入类型的对象
	 * @author dongchao
	 * @date Aug 5, 2015 8:21:46 PM
	 * @param json 要转义的字符串
	 * @param type eg:HashMap<Stirng,String>
	 * @return
	 * @throws Exception
	 */
	public static <T> T deserialize(String json,TypeReference<T> type) throws Exception{
    	ObjectMapper mapper = new ObjectMapper(); 
    	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return mapper.readValue(json, type);
    }
	
	/**
	 * @描述：将json解析为Map
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param string 要解析的json字符串
	 * @return Map
	 */
	public static Map parseJson2Map(String string) throws Exception{
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			return mapper.readValue(string, Map.class);
		} catch (Exception e) {
			throw new  Exception(e.getMessage());
		}
	}
	
	/**
	 * @描述：将json解析为Map
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param string 要解析的json字符串
	 * @param filterMap 过滤器的Map
	 * @return Map
	 */
	public static Map parseJson2Map(String string ,Map<String, String[]> filterMap) throws Exception{
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			SimpleFilterProvider filter = new SimpleFilterProvider().setFailOnUnknownId(false);
			for (Map.Entry<String, String[]> mapItem : filterMap.entrySet()) {
				filter.addFilter(mapItem.getKey(), SimpleBeanPropertyFilter.filterOutAllExcept(mapItem.getValue()));
			}
			mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
			mapper.setFilters(filter);
			return mapper.readValue(string, Map.class);
		} catch (Exception e) {
			throw new  Exception(e.getMessage());
		}
	}
	
	/**
	 * 将json转换为map(指定日期类型)
	 * 有缺陷,不能返回Map<List<User>>类型
	 * @param jsonStr
	 * @param clazz map中值的类型(键默认为String)
	 * @param datePattern
	 * @return
	 */
	public static <T> Map<String,T> parseJson2Map(String jsonStr,Class<T> clazz, String datePattern){
		if(datePattern==null || datePattern.length()==0){
			datePattern = "yyyy-MM-dd HH:mm:ss";
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat(datePattern));
			JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, clazz);
			return mapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @描述：获取泛型的Collection Type 
	 * @开发人员：
	 * @开发时间：2015年7月24日 上午08:00:00
	 * @param collectionClass 泛型的Collection   
     * @param elementClasses 元素类   
     * @return JavaType Java类型   
	 */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
    	ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
    }
    
    
 
    
//    public static string Serialize<T>(T obj)//序列化为JSON
//    {
//        System.Runtime.Serialization.Json.DataContractJsonSerializer serializer = new System.Runtime.Serialization.Json.DataContractJsonSerializer(obj.GetType());
//        MemoryStream ms = new MemoryStream();
//        serializer.WriteObject(ms, obj);
//        string retVal = Encoding.Default.GetString(ms.ToArray());
//        return retVal;
//    }
    
}

