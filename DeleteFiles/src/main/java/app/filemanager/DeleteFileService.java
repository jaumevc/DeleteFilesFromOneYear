package app.filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteFileService {
	
	private static Logger LOG = LoggerFactory.getLogger(DeleteFileService.class);

//	private static final String DELETE_RUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_TO_DELETE";
	private static final String MOVE_RUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_MOVED";

	public void moveFiles(String path, String days) throws IOException {
		List<File> filesToMove = new ArrayList<>();
		filesToMove = getOnlyFilesToWork(path, days);
		moveOldFiles(path, filesToMove);
	}
	
	
	public void deleteFiles(String path, String days) throws IOException {
		List<File> filesToDelete = new ArrayList<>();
		filesToDelete = getOnlyFilesToWork(path, days);
		deleteOldFiles(filesToDelete);
	}

	private List<File> getOnlyFilesToWork(String path, String days) {
		List<File> filesInPath = getFilesToWork(path);;
		
		long limitDate = getLimitDate(days);
		
		List<File> onlyFilesToDelete = new ArrayList<>();

		for (File f : filesInPath) {
			if (!f.isDirectory() && f.lastModified() < limitDate) {
				onlyFilesToDelete.add(f);
			}
		}

		return onlyFilesToDelete;
	}
	
	private List<File> getFilesToWork(String path) {
		File filesInFolder = new File(path);
		return Arrays.asList(filesInFolder.listFiles());
	}


	private long getLimitDate(String days) {
		//Obtenir data actual
        Date currentDate = new Date();
        
        //Convertir data actual a un objecte Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        //Número díes a restar
        int diasARestar = Integer.parseInt(days);
        
        //Restar els díes a la data
        calendar.add(Calendar.DAY_OF_MONTH, -diasARestar);
        
        //Obtenir la nova data
        Date nuevaFecha = calendar.getTime();
        
		return nuevaFecha.getTime();
	}

	private void deleteOldFiles(List<File> filesToDelete) {
		if(filesToDelete != null) {
			for(File file: filesToDelete) {
				if (file.delete()) {
					LOG.warn("el fitxer eliminat"+ file.getName());
				}
			}
		}
	}
	
	private void moveOldFiles(String path, List<File> filesToMove) throws IOException {
		for (File file : filesToMove) {
			Path temp = Files.move(Paths.get(path + "\\" + file.getName()),
					Paths.get(MOVE_RUTH_NAME + "\\" + file.getName()));
			if (temp != null) {
				LOG.warn("File renamed and moved successfully");
			} else {
				LOG.warn("Failed to move the file");
			}
		}
	}

}
