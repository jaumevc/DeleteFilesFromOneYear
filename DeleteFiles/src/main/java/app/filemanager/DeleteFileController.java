package app.filemanager;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeleteFileController {
	@Autowired
	DeleteFileService deleteService;

	public void operateWithFiles(String path, String days, String operationType) throws IOException {

		Integer daysControl = Integer.valueOf(days);

		if (daysControl >= 30) {
			if (StringUtils.isNotEmpty(operationType) && StringUtils.equalsIgnoreCase("D", operationType)) {
				deleteFilesFromFolder(path, days);
			}

			if (StringUtils.isNotEmpty(operationType) && StringUtils.equalsIgnoreCase("M", operationType)) {
				moveFilesFromFolder(path, days);
			}
		}

	}

	private void moveFilesFromFolder(String path, String days) throws IOException {
		deleteService.moveFiles(path, days);
	}

	public void deleteFilesFromFolder(String path, String days) throws IOException {
		deleteService.deleteFiles(path, days);
	}

}
