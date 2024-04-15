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
		//args = new String[]{"\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_TO_DELETE", "730", "D"};
		//args = new String[]{"\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_TO_DELETE", "30", "M"};
		SpringApplication.run(DeleteFilesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("l\'aplicacio de neteja de fitxers s\'està executant");
		//Path for move or delete files 
		String path= args[0];
		//ActualDate - Days to delete or move file
		String days= args[1];
		//MOVE ->"M" or DELETE -> "D"
		String operationType = args[2];
		
		LOG.info(path+"\n"+days+"\n"+operationType);
		
		filecontroller.operateWithFiles(path, days, operationType);
		LOG.warn("Neteja de fitxers executada!!");
	}

}
