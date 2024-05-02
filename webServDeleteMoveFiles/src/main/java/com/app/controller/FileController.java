package com.app.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.beans.Arguments;
import com.app.beans.FBW300PATH;
import com.app.service.FileService;
import com.app.service.VerifyFolderAccesService;


@RestController
public class FileController {
	@Autowired
	FileService fileService;
	
	@Autowired
	VerifyFolderAccesService verifyAcces;
	
	//@PostMapping("/deletefiles")
	@DeleteMapping("/deletefiles")
	@ResponseBody
	public void deleteFilesFromFolder(@RequestBody Arguments args) throws IOException {
		
		int limitdays = fileService.getDaysByReference(args.getReference());
		String pathRootFiles =  fileService.getPathByReference(args.getReference());
		
		//Verificar Permisos ACL(Access Control List) d'un usuari en una carpeta.
		//En aquest cas l'usuari per defecte
//		boolean testAcces= verifyAcces.hasAclFolder(pathRootFiles);
		boolean hasAccesToFile = verifyAcces.hasPermissions(pathRootFiles);
		
		if (limitdays >= 30 && hasAccesToFile) {
			if (StringUtils.isNotEmpty(args.getReference())
					&& StringUtils.equalsIgnoreCase("DEL_SARR_FILE", args.getReference())) {
				fileService.deleteFiles(args.getReference());
			}
		}
	}
	
	@PostMapping("/movefiles")
	@ResponseBody
	public void moveFilesFromFolder(@RequestBody Arguments args) throws IOException {
		int limitdays = fileService.getDaysByReference(args.getReference());
		
		if (limitdays >= 30) {
			if (StringUtils.isNotEmpty(args.getReference())
					&& StringUtils.equalsIgnoreCase("MOV_SARR_FILE", args.getReference())) {
				fileService.moveFiles(args.getReference());
			}
		}
	}

}
