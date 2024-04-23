package app.filemanager;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FileController {
	@Autowired
	FileService fileService;

	public void operateWithFiles(String reference) throws IOException {

		int daysControl = fileService.getDaysByReference(reference); 

		if (daysControl >= 30) {
			if (StringUtils.isNotEmpty(reference) && StringUtils.equalsIgnoreCase("DEL_SARR_FILE", reference)) {
				deleteFilesFromFolder(reference);
			}

			if (StringUtils.isNotEmpty(reference) && StringUtils.equalsIgnoreCase("MOV_SARR_FILE", reference)) {
				moveFilesFromFolder(reference);
			}
		}
	}

	private void moveFilesFromFolder(String reference) throws IOException {
		fileService.moveFiles(reference);
	}

	public void deleteFilesFromFolder(String reference) throws IOException {
		fileService.deleteFiles(reference);
	}

}
