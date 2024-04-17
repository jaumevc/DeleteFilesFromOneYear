package com.app.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Arguments;
import com.app.service.FileService;


@RestController
public class FileController {
	@Autowired
	FileService fileService;
	
	@PostMapping("/deletefiles")
	@ResponseBody
	public void deleteFilesFromFolder(@RequestBody Arguments args) throws IOException {
		if (Integer.valueOf(args.getDays()) >= 30) {
			if (StringUtils.isNotEmpty(args.getOperationType())
					&& StringUtils.equalsIgnoreCase("D", args.getOperationType())) {
				fileService.deleteFiles(args.getPath(), args.getDays());
			}
		}
	}
	
	@PostMapping("/movefiles")
	@ResponseBody
	public void moveFilesFromFolder(@RequestBody Arguments args) throws IOException {
		if (Integer.valueOf(args.getDays()) >= 30) {
			if (StringUtils.isNotEmpty(args.getOperationType())
					&& StringUtils.equalsIgnoreCase("M", args.getOperationType())) {
				fileService.moveFiles(args.getPath(), args.getDays());
			}
		}
	}

}
