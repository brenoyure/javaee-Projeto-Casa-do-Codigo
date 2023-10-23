package br.com.casadocodigo.loja.servlets;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.casadocodigo.loja.infra.FileSaver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] requestURI = request.getRequestURI().split("/file");
		String relativePath = requestURI[1];

		Path source = Paths.get(FileSaver.SERVER_PATH + File.separator + relativePath);

		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor("file:" + source);

		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(Files.size(source)));
		response.setHeader("Content-Disposition", "filename=\"" + source.getFileName() + "\"");

		FileSaver.transfer(source, response.getOutputStream());

	}

}
