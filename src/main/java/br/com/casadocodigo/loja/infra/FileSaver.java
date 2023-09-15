package br.com.casadocodigo.loja.infra;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import jakarta.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "/home/breno/casadocodigo";

	public String write(Part arquivo, String path) {
		try {
			String relativePath = path + "/" + arquivo.getSubmittedFileName();
			arquivo.write(SERVER_PATH + "/" + relativePath);
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void transfer(Path source, OutputStream outputStream) {
		try {

			InputStream inputStream = new FileInputStream(source.toFile());
			ReadableByteChannel inputChannel = Channels.newChannel(inputStream);
			WritableByteChannel outputChannel = Channels.newChannel(outputStream);
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 10);

			while(inputChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				outputChannel.write(byteBuffer);
				byteBuffer.clear();
			}

			outputChannel.close();
			inputChannel.close();
			inputStream.close();

		} catch (IOException e) { throw new RuntimeException(e); }
	}

}
