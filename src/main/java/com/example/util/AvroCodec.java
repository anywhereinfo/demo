package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericContainer;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.codec.Codec;

import com.example.domain.AvroCar;

public class AvroCodec implements Codec{

	private Logger logger = LoggerFactory.getLogger(AvroCodec.class);
	
	@Override
	public void encode(Object object, OutputStream outputStream) throws IOException {

		Schema schema = getSchema(object);
		DatumWriter writer = getDatumWriter(object.getClass(),schema);
		BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
		writer.write(object,encoder);
		encoder.flush();	
	}

	@Override
	public byte[] encode(Object object) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		encode(object,baos);
		return baos.toByteArray();
	}

	@Override
	public <T> T decode(InputStream inputStream, Class<T> type) throws IOException {
		return decode(IOUtils.toByteArray(inputStream),type);
	}

	@Override
	public <T> T decode(byte[] bytes, Class<T> type) throws IOException {
		Schema schema = getSchema(new AvroCar());
		DatumReader reader = getDatumReader(type,schema);
		Decoder decoder = DecoderFactory.get().binaryDecoder(bytes,null);
		return (T) reader.read(null,decoder);
	}
	
	private DatumReader getDatumReader(Class<?> type, Schema writer){
		DatumReader reader = null;
		if(SpecificRecord.class.isAssignableFrom(type)){
			reader = new SpecificDatumReader<>(writer);
		}
		return reader;
		}
		
		
	private Schema  getSchema(Object payload){
		Schema schema = null;
		logger.debug("Obtaining schema for class {}", payload.getClass());
		if(GenericContainer.class.isAssignableFrom(payload.getClass())) {
			schema = ((GenericContainer) payload).getSchema();
			logger.debug("Avro type detected, using schema from object");
		}
		return schema;
	}
	
	private DatumWriter<?> getDatumWriter(Class<?> type, Schema schema){
		DatumWriter<?> writer = null;
		logger.debug("Finding correct DatumWriter for type {}",type.getName());
		if(SpecificRecord.class.isAssignableFrom(type)){
			writer = new SpecificDatumWriter<>(schema);
		}
		return writer;
	}
	

}
