package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.filemanager.FileController;



@SpringBootApplication
public class DeleteFilesApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(DeleteFilesApplication.class);
	
	@Autowired
	FileController filecontroller;
	
	public static void main(String[] args) {
		//args = new String[]{"DEL_SARR_FILE", "730"};
		args = new String[]{"DEL_SARR_FILE"};
//		args = new String[]{"MOV_SARR_FILE"};
		SpringApplication.run(DeleteFilesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("l\'aplicacio de neteja de fitxers s\'està executant");
		String reference= args[0];
		
		LOG.info(reference+".\n");
		
		filecontroller.operateWithFiles(reference);
		LOG.warn("Neteja de fitxers executada!!");
	}

}
