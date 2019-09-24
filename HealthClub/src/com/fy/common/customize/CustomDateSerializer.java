package com.fy.common.customize;

import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 自定义日期序列化器
 * @author Administrator
 *
 */


public class CustomDateSerializer extends JsonSerializer<Date> {

	 public void serialize(Date value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException{
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
         jsonGenerator.writeString(sdf.format(value));
 }
}
