package com.app.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.FileRepositoryJDBC;
import com.app.beans.*;

@Service
public class FileService {
	
	private static Logger LOG = LoggerFactory.getLogger(FileService.class);

//	private static final String DELETE_RUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_TO_DELETE";
//	private static final String MOVE_RUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_MOVED";
	
	@Autowired
	private FileRepositoryJDBC repoFileJDBC;
	
	public int getDaysByReference(String reference) {
		return repoFileJDBC.getDaysByReference(reference);
	}
	
	public String getPathByReference(String reference) {
		return repoFileJDBC.getPathByReference(reference);
	}

	public void moveFiles(String reference) throws IOException {
		FBW300PATH action = repoFileJDBC.getActionByReference(reference);
		
		List<File> filesToMove = new ArrayList<>();
		filesToMove = getOnlyFilesToWork(action.getRootpath(), action.getLimitdays());
		moveOldFiles(action.getRootpath(),action.getMovepath(), filesToMove);
	}
	
	
	public void deleteFiles(String reference) throws IOException {
		FBW300PATH action = repoFileJDBC.getActionByReference(reference);
				
		List<File> filesToDelete = new ArrayList<>();
		filesToDelete = getOnlyFilesToWork(action.getRootpath(), action.getLimitdays());
		deleteOldFiles(filesToDelete, action.getRootpath());
	}

	private List<File> getOnlyFilesToWork(String path, int limitdays) {
		List<File> filesInPath = getFilesToWork(path);
		
		long limitDate = getLimitDate(limitdays);
		
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


	private long getLimitDate(int limitdays) {
		//Obtenir data actual
        Date currentDate = new Date();
        
        //Convertir data actual a un objecte Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        //Número díes a restar
        int diasARestar = limitdays;
        
        //Restar els díes a la data
        calendar.add(Calendar.DAY_OF_MONTH, -diasARestar);
        
        //Obtenir la nova data
        Date nuevaFecha = calendar.getTime();
        
		return nuevaFecha.getTime();
	}

	private void deleteOldFiles(List<File> filesToDelete, String rootpath) {
		if(filesToDelete != null && !filesToDelete.isEmpty()) {
			for(File file: filesToDelete) {
				if (file.delete()) {
					LOG.warn("el fitxer eliminat"+ file.getName());
				}
			}
		} else {
			LOG.warn(" Al path "+rootpath+" no hi ha fitxers que compleixin les característiques per ser eliminats");
		}
	}
	
	private void moveOldFiles(String rootpath, String movePath, List<File> filesToMove) throws IOException {
		for (File file : filesToMove) {
			Path temp = Files.move(Paths.get(rootpath + "\\" + file.getName()),
					Paths.get(movePath+ "\\" + file.getName()));
			if (temp != null) {
				LOG.warn("File moved successfully");
			} else {
				LOG.warn("Failed to move the file");
			}
		}
	}

}
