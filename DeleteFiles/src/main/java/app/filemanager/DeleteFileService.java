package app.filemanager;

import java.io.File;
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

	private static final String DELETERUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_TO_DELETE";
	
	private static final String MOVERUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_MOVED";

//	private File deleteRuthFilesFolder = new File(DELETERUTH_NAME);
	
//	private List<File> onlyFilesInError = new ArrayList<>();
	
	public void deleteFiles(String path, String days, String operationType) {
		List<File> filesToDelete = new ArrayList<>();
		filesToDelete = getOnlyFilesToDelete(path, days, operationType);
		deleteOldFiles(filesToDelete);
	}

	private List<File> getOnlyFilesToDelete(String path, String days, String operationType) {
		File deleteRuthFilesFolder = new File(path);
		List<File> filesInPath = new ArrayList<>();
		
		//Vaig per aquí!!!!
		filesInPath = Arrays.asList(deleteRuthFilesFolder.listFiles());
		
		// Obtenim la data actual
//	    long currentTimeMillis = System.currentTimeMillis();
//	    Date avui = new Date(currentTimeMillis);
		
		// Obtener la fecha actual
        Date currentDate = new Date();
        
     // Convertir la fecha actual a un objeto Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Número de días que quieres restar
        int diasARestar = Integer.parseInt(days);
        
        // Restar los días
        calendar.add(Calendar.DAY_OF_MONTH, -diasARestar);
        
     // Obtener la nueva fecha
        Date nuevaFecha = calendar.getTime();
        
        long millisDesdeEpoca = currentDate.getTime();
        long millisNuevaFecha = nuevaFecha.getTime();
        
        long dateAgo = millisDesdeEpoca - millisNuevaFecha; 
        
	    //Calculem la data de fa els dies que ens arriben (en mil·lisegons)
//	    long daysInMillis= Integer.parseInt(days)* 24 * 60 * 60 * 1000;
	    //Restem a la data actual els days que ens arriben anys
//	    long dateAgo = currentTimeMillis - daysInMillis; 
	    
		List<File> onlyFilesToDelete = new ArrayList<>();

		for (File f : filesInPath) {
			if (!f.isDirectory() && f.lastModified() < dateAgo)
				onlyFilesToDelete.add(f);
		}

		return onlyFilesToDelete;
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

	public void moveFiles(String path, String days, String operationType) {
		// TODO Auto-generated method stub
		
	}
}
