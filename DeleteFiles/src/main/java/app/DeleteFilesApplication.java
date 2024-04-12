package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.filemanager.DeleteFileController;



@SpringBootApplication
public class DeleteFilesApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(DeleteFilesApplication.class);
	
	@Autowired
	DeleteFileController filecontroller;
	
	public static void main(String[] args) {
		SpringApplication.run(DeleteFilesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("l\'aplicacio de neteja de fitxers s\'està executant");
		filecontroller.deleteFilesFromFolder();
		LOG.warn("Neteja de fitxers executada!!");
	}

}
