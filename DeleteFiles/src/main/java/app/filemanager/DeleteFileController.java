package app.filemanager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeleteFileController {
	@Autowired
	DeleteFileService deleteService;

	public void operateWithFiles(String path, String days, String operationType) {

		Integer daysControl = Integer.valueOf(days);

		if (daysControl >= 30) {
			if (StringUtils.isNotEmpty(operationType) && StringUtils.equalsIgnoreCase("D", operationType)) {
				deleteFilesFromFolder(path, days, operationType);
			}

			if (StringUtils.isNotEmpty(operationType) && StringUtils.equalsIgnoreCase("M", operationType)) {
				moveFilesFromFolder(path, days, operationType);
			}

		}

	}

	private void moveFilesFromFolder(String path, String days, String operationType) {
		deleteService.moveFiles(path, days, operationType);
	}

	public void deleteFilesFromFolder(String path, String days, String operationType) {
		deleteService.deleteFiles(path, days, operationType);
	}

}
