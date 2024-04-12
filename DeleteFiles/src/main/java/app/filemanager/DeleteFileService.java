package app.filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import app.DeleteFilesApplication;

@Service
public class DeleteFileService {
	
	private static Logger LOG = LoggerFactory.getLogger(DeleteFileService.class);

	private static final String DELETERUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_TO_DELETE";
	
	private static final String MOVERUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_MOVED";

	private File deleteRuthFilesFolder = new File(DELETERUTH_NAME);
	
//	private List<File> onlyFilesInError = new ArrayList<>();
	
	public void deleteFiles() {
		List<File> filesToDelete = new ArrayList<>();
		filesToDelete = getOnlyFilesToDelete();
		deleteOldFiles(filesToDelete);
	}

	private List<File> getOnlyFilesToDelete() {
		List<File> filesInPath = new ArrayList<>();
		
		filesInPath = Arrays.asList(deleteRuthFilesFolder.listFiles());
		
		// Obtenim la data actual
	    long currentTimeMillis = System.currentTimeMillis();
	    // Calculem la data de fa dos anys (en mil·lisegons)
	    long twoYearsInMillis = 2L * 365 * 24 * 60 * 60 * 1000;
	    // Restem a la data actual els dos anys
	    long twoYearsAgo = currentTimeMillis - twoYearsInMillis;

		List<File> onlyFilesToDelete = new ArrayList<>();

		for (File f : filesInPath) {
			if (!f.isDirectory() && f.lastModified() < twoYearsAgo)
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
}
