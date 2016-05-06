package org.jymf.utils;

import java.math.BigDecimal;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {  
  
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  

    /**
     * 根据追溯模式获取对应的数据库
     * 基础数据库时 workMode <- -1
     * @param workMode
     */
    public static void setDataSource(BigDecimal workMode){
    	String lookupKey="jydb";

    	switch(workMode.intValue()){
    	case 0:
    		lookupKey+="00";
    		break;
    	case 1:
    		lookupKey+="01";
    		break;
    	case 2:
    		lookupKey+="02";
    		break;
    	case 3:
    		lookupKey+="03";
    		break;
    	case 4:
    		lookupKey+="04";
    		break;
    	case 5:
    		lookupKey+="05";
    		break;
    	case 6:
    		lookupKey+="06";
    		break;
    	case 7:
    		lookupKey+="07";
    		break;
    	case 8:
    		lookupKey+="08";
    		break;
    	case 9:
    		lookupKey+="09";
    		break;
    	}
    	
    	//String oldDataSource = (String) contextHolder.get();
    	
    	//if(!lookupKey.equals(oldDataSource)){
    	    contextHolder.set(lookupKey);  
    	//}
    }
    
	/* 
     * (non-Javadoc) 
     *  
     * @see 
     * org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource# 
     * determineCurrentLookupKey() 
     */  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return getDataSource();  
    }  
  
    public static String getDataSource() {
    	return (String) contextHolder.get();
	}
    
    public static void setDataSource(String dataSource){
		contextHolder.set(dataSource);
    }
    
    public static void clearDataSource() {  
        contextHolder.remove();  
    }  
}  