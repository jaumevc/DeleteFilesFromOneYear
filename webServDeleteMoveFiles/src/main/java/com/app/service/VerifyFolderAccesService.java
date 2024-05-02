package com.app.service;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclFileAttributeView;

import java.nio.file.attribute.AclEntryPermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//import jcifs.smb.NtlmPasswordAuthentication;
//import jcifs.smb.SmbFile;
//import jcifs.smb.SmbFileInputStream;
//import jcifs.smb.ACE;
//import jcifs.smb.SID;

@Service
public class VerifyFolderAccesService {

	private static Logger LOG = LoggerFactory.getLogger(VerifyFolderAccesService.class);

//	private static final String DELETE_RUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_TO_DELETE";
//	private static final String MOVE_RUTH_NAME = "\\\\sarroca\\comu-inf$\\Suport\\test\\FILES_MOVED";

	public boolean hasAclFolder(String folderPath) {
		Set<AclEntryPermission> permisos = new HashSet<AclEntryPermission>();
		try {
			Path path = FileSystems.getDefault().getPath(folderPath);
			AclFileAttributeView aclView = Files.getFileAttributeView(path, AclFileAttributeView.class);
			
			List<AclEntry> aclEntries = aclView.getAcl();
			int cont = 0;
			for(AclEntry acl: aclEntries) {
				permisos = acl.permissions();
				if(permisos.contains((AclEntryPermission.WRITE_ACL)))
					cont++;
			}
	        return cont > 0 ? true: false;
	        
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean hasPermissions(String pathRootFiles) {
		File file = new File(pathRootFiles);
		
		if(file.exists() && file.canWrite())
			return true;
		else
			return false;
		
	}

}
