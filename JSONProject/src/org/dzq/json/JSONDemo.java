package org.dzq.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.dzq.entity.Address;
import org.dzq.entity.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONDemo {
	//1.将Map，JavaBean，字符串，文件->JSON对象      ,生成JSON文件
	//a.map->JSON对象
	public static void mapDemo() {
		Map<String,String> map=new HashMap<>();
		map.put("s01", "zs");
		map.put("s02", "ls");
		map.put("s03", "ww");
		map.put("s04", "zl");
		map.put("s05", "sq");
		JSONObject json=new JSONObject(map);
		System.out.println(json);
	}
	//b.JavaBean->JSON对象
	public static void javaBeanDemo() {
		Person person=new Person();
		person.setName("zs");
		person.setAge(13);
		Address address=new Address("抚州","南昌");
		person.setAddress(address);
		JSONObject json=new JSONObject(person);
		System.out.println(json);
	}
	//c.字符串->JSON对象
	public static void stringDemo() {
		String Str="{\"address\":{\"schoolAddress\":\"南昌\",\"homeAddress\":\"抚州\"},\"name\":\"zs\",\"age\":13}";
		JSONObject json=new JSONObject(Str);
		System.out.println(json);
	}
	//d.文件->JSON对象(文件->字符串->JSON对象)
	public  void fileDemo() throws IOException {
		/*InputStream inputStream = super.getClass().getClassLoader().getResourceAsStream("org/dzq/json/per.json");
		byte[] bs=new byte[10];
		int len=-1;
		StringBuffer sb=new StringBuffer();
		while((len=inputStream.read(bs))!=-1) {
			String str = new String(bs,0,len);
			sb.append(str);
		}
		String result=sb.toString();*/
		String result=FileUtils.readFileToString(new File("C:\\Users\\xxx-d2q\\eclipse-workspace\\JSONProject\\src\\org\\dzq\\json\\per.json"));
		JSONObject json=new JSONObject(result);
		System.out.println(json);
	}
	//e.生成JSON文件
	public static void JSONFileDemo() throws JSONException, IOException {
		Map<String,Person> map=new HashMap<>();
		Person one=new Person("zs",5,new Address("美国","芝加哥"));
		Person two=new Person("ls",6,new Address("伊拉克","洪都"));
		Person three=new Person("ww",7,new Address("苏联","皮尔买"));
		map.put("zs", one);
		map.put("ls", two);
		map.put("ww", three);
		JSONObject json =new JSONObject(map);
		Writer writer = new FileWriter("e:\\p.txt");
		json.write(writer);
		writer.close();
	}
	//String->JSONArray
	public static void JSONArrayDemo() {
		String jArray="[{\"name\":\"zs\",\"age\":13,\"address\":\"抚州\"},{\"name\":\"ls\",\"age\":18,\"address\":\"曹州\"},{\"name\":\"ww\",\"age\":24,\"address\":\"郑州\"}]";
		JSONArray jsonarray=new JSONArray(jArray);
		System.out.println(jsonarray);
	}
	//map->JSONArray    下面的小样全用新jar包
	public static void JSONArrayDemo2() {
		Map<String,String> map=new HashMap<>();
		map.put("s01", "zs");
		map.put("s02", "ls");
		map.put("s03", "ww");
		map.put("s04", "zl");
		map.put("s05", "sq");
		net.sf.json.JSONArray jArray=new net.sf.json.JSONArray();
		jArray=jArray.fromObject(map);
		System.out.println(jArray);
	}
	//JSONArray->map
	public static void JSONArrayDemo3() {
		String jArraystr="[{\"names\":\"zs\",\"age1\":13,\"address1\":\"抚州\"},{\"nameb\":\"ls\",\"age2\":18,\"address2\":\"曹州\"},{\"name\":\"ww\",\"age3\":24,\"address3\":\"郑州\"}]";
		net.sf.json.JSONArray jArray=new net.sf.json.JSONArray();
		jArray=jArray.fromObject(jArraystr);
		System.out.println(jArray);
		Map<String,Object> map=new HashMap<>();
		for(int i=0;i<jArray.size();i++) {
			net.sf.json.JSONObject json = (net.sf.json.JSONObject)jArray.get(i);
			Set<String> keySet = json.keySet();
			for(String key:keySet) {
				 Object value= json.get(key);
				 map.put(key, value);
			}
		}
		System.out.println(map);
	}
	public static void main(String[] args) throws IOException {
		//mapDemo();    				//a
		//javaBeanDemo();				//b
		//stringDemo();					//c
		//JSONDemo jd=new JSONDemo();	//d
		//jd.fileDemo();
		//JSONFileDemo();				//e
		//JSONArrayDemo();   			//arraydemo1
		//JSONArrayDemo2();				//arraydemo2
		JSONArrayDemo3();
	}
}
