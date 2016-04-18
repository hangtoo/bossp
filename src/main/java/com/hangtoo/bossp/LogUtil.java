package com.hangtoo.bossp;

import org.apache.log4j.Logger;

/**
 * 封装了logger实现自己的日志记录，方便以后扩张
 * 比如后续可以考虑直接存放在kafka，便于系统监控
 * @author hlf
 *
 */
public class LogUtil{
	Logger logger;
	
	private LogUtil(Class<?> clazz){
		this.logger=Logger.getLogger(clazz);
	}
	
	public static LogUtil getLogger(Class<?> clazz){
		LogUtil logUtil=new LogUtil(clazz);
		return logUtil;
	}
	
	public boolean isDebugEnabled() {
	    return logger.isDebugEnabled();
	}
	
    public void trace(String msg) {
    	logger.trace(msg);
    }

    public void trace(Throwable throwable) {
    	logger.trace(throwable);
    }

    public void trace(String msg, Throwable e) {
    	logger.trace(msg, e);
    }

    public void debug(Object msg) {
    	logger.debug(msg);
    }
    
    public void debug(String msg) {
    	logger.debug(msg);
    }

    public void debug(Throwable e) {
    	logger.debug(e);
    }

    public void debug(String msg, Throwable e) {
    	logger.debug(msg, e);
    }

    public void info(String msg) {
    	logger.info(msg);
    }

    public void info(Throwable e) {
    	logger.info(e);
    }

    public void info(String msg, Throwable e) {
    	logger.info(msg, e);
    }

    public void warn(String msg, Throwable e) {
    	logger.warn(msg, e);
    }

    public void warn(String msg) {
    	logger.warn(msg);
    }

    public void warn(Throwable e) {
    	logger.warn(e);
    }

    public void error(Throwable e) {
    	logger.error(e);
    }

    public void error(String msg) {
    	logger.error(msg);
    }

    public void error(String msg, Throwable e) {
    	logger.error(msg, e);
    }

	public void error(Object msg) {
		logger.error(msg);
	}
}
